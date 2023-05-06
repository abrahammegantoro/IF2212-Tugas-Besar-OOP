package src.Item.Furniture.Stove;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import src.Inventory.Inventory;
import src.Item.Item;
import src.Item.BahanBaku.BahanBaku;
import src.Item.Furniture.Furniture;
import src.Item.Masakan.Masakan;
import src.MainMenu.MainMenu;
import src.Sim.Sim;
import src.World.Time;

public abstract class Stove extends Furniture {
    private static HashMap<Integer, Masakan> resep; // HashMap untuk menyimpan resep masakan

    public Stove(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);

        // Inisialisasi HashMap resep
        resep = new HashMap<>();

        // Instansiasi masakan
        Masakan nasiAyam = new Masakan("Nasi Ayam", 16);
        Masakan nasiKari = new Masakan("Nasi Kari", 30);
        Masakan susuKacang = new Masakan("Susu Kacang", 5);
        Masakan tumisSayur = new Masakan("Tumis Sayur", 5);
        Masakan bistik = new Masakan("Bistik", 22);

        // Menambahkan bahan-bahan baku ke dalam masakan
        nasiAyam.addBahanBaku(new BahanBaku("Nasi", 5, 5));
        nasiAyam.addBahanBaku(new BahanBaku("Ayam", 10, 8));

        nasiKari.addBahanBaku(new BahanBaku("Nasi", 5, 5));
        nasiKari.addBahanBaku(new BahanBaku("Kentang", 3, 4));
        nasiKari.addBahanBaku(new BahanBaku("Wortel", 3, 2));
        nasiKari.addBahanBaku(new BahanBaku("Sapi", 12, 15));

        susuKacang.addBahanBaku(new BahanBaku("Susu", 2, 1));
        susuKacang.addBahanBaku(new BahanBaku("Kacang", 2, 2));

        tumisSayur.addBahanBaku(new BahanBaku("Wortel", 3, 2));
        tumisSayur.addBahanBaku(new BahanBaku("Bayam", 3, 2));

        bistik.addBahanBaku(new BahanBaku("Kentang", 3, 4));
        bistik.addBahanBaku(new BahanBaku("Sapi", 12, 15));

        // Menambahkan resep-resep masakan ke dalam HashMap
        resep.put(1, nasiAyam);
        resep.put(2, nasiKari);
        resep.put(3, susuKacang);
        resep.put(4, tumisSayur);
        resep.put(5, bistik);
    }

    public static void masak(Sim sim, Inventory<Item> inventory) {
        printResep();

        // User memilih nomor masakan yang ingin dimasak dari buku resep
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan nomor masakan yang ingin dimasak: ");
        int noMasakan = scanner.nextInt();

        // Cek apakah bahan-bahan baku tersedia dan validasi apakah nomor masakan yang
        // dimasukkan valid berdasarkan resep
        while (noMasakan < 1 || noMasakan > resep.size()) {
            // inventory.showInventory();
            // System.out.println(cekBahanBaku(inventory, resep.get(noMasakan)));
            System.out.println("\nNomor masakan yang dimasukkan salah, silakan masukan ulang nomor masakan.");
            printResep();
            System.out.print("Masukkan nomor masakan yang ingin dimasak: ");
            noMasakan = scanner.nextInt();
        }

        while (!cekBahanBaku(inventory, resep.get(noMasakan))) {
            System.out.println("\nBahan-bahan baku tidak cukup, silakan pilih masakan lainnya.");
            printResep();
            System.out.print("Masukkan nomor masakan yang ingin dimasak: ");
            noMasakan = scanner.nextInt();
            while (noMasakan < 1 || noMasakan > resep.size()) {
                // inventory.showInventory();
                // System.out.println(cekBahanBaku(inventory, resep.get(noMasakan)));
                System.out.println("\nNomor masakan yang dimasukkan salah, silakan masukan ulang nomor masakan.");
                printResep();
                System.out.print("Masukkan nomor masakan yang ingin dimasak: ");
                noMasakan = scanner.nextInt();
            }
        }

        Masakan masakanTerpilih = resep.get(noMasakan);

        // Memasak masakan
        sim.setStatus("Memasak");
        System.out.println("Memasak " + masakanTerpilih.getNama() + "...");
        float waktuMasak = masakanTerpilih.getWaktuMasak();

        Thread masakThread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Mengurangi bahan-bahan baku yang digunakan
                ArrayList<BahanBaku> bahanBaku = masakanTerpilih.getBahanBaku();
                for (BahanBaku bahan : bahanBaku) {
                    inventory.removeItem(bahan);
                }

                // Menunggu selama waktu masak
                int counter = 0; 
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (counter < waktuMasak) {
                    try {
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        if (sim.getIsMakan()) {
                            sim.incrementWaktuSetelahMakan();
                        }
                        if (tempDay != Time.getInstance().getCurrentDay()) {
                            sim.setIsTidur(false);
                            sim.setLamaTidakTidur(0);
                            sim.setLamaTidur(0);
                            MainMenu.setAddSim(false);
                        }
                        sim.incrementLamaTidakTidur();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter++;
                }
                if (sim.getWaktuSetelahMakan() >= 240) {
                    System.out.println(sim.getNama() + " belum buang air.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
                    sim.setWaktuSetelahMakan(0);
                    sim.setIsMakan(false);
                }
                int tidakTidur = sim.getLamaTidakTidur() / 600;
                if (!sim.getIsTidur() && tidakTidur > lastTidakTidur) {
                    System.out.println(sim.getNama() + " lelah karena tidak tidur.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
                }
                
                // Memberi tahu user bahwa masakan telah selesai dimasak
                System.out.println(masakanTerpilih.getNama() + " telah selesai dimasak!");
                // Menambahkan masakan ke dalam inventory
                inventory.addItem(masakanTerpilih, 1);
                inventory.showInventory();
            }
        });

        masakThread.start();

        try {
            masakThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printResep() {
        // Menampilkan buku resep
        System.out.println("Buku Resep: ");
        System.out.println("No.\tNama Masakan\t\t\t\tBahan\t\t\t\tKekenyangan");
        System.out
                .println("-------------------------------------------------------------------------------------------");

        for (Map.Entry<Integer, Masakan> entry : resep.entrySet()) {
            int no = entry.getKey();
            Masakan food = entry.getValue();
            System.out.printf("%d.\t%-20s\t%-40s\t%d\n", no, food.getNama(), getBahanBakuString(food),
                    food.getKekenyangan());
        }
    }

    // Helper methods
    // public boolean cekBahanBaku(Inventory<Item> inventory, Masakan masakan) { //
    // jgn lupa ubah ke private
    // ArrayList<BahanBaku> bahanBaku = masakan.getBahanBaku();
    // for (BahanBaku bahan : bahanBaku) {
    // if (!inventory.isItemExist(bahan)) {
    // return false;
    // }
    // }
    // return true;
    // }
    private static boolean cekBahanBaku(Inventory<Item> inventory, Masakan masakan) {
        ArrayList<BahanBaku> bahanBaku = masakan.getBahanBaku();
        boolean foundAllIngredients = true;

        for (BahanBaku bahan : bahanBaku) {
            boolean foundIngredient = false;
            for (Map.Entry<Item, Integer> entry : inventory.getItems().entrySet()) {
                Item item = entry.getKey();
                if (item instanceof BahanBaku) {
                    BahanBaku inventoryBahan = (BahanBaku) item;
                    if (inventoryBahan.getNama().equalsIgnoreCase(bahan.getNama()) &&
                            entry.getValue() >= 1) {
                        foundIngredient = true;
                        break;
                    }
                }
            }

            if (!foundIngredient) {
                foundAllIngredients = false;
                break;
            }
        }

        return foundAllIngredients;
    }

    private static String getBahanBakuString(Masakan masakan) {
        ArrayList<BahanBaku> bahanBaku = masakan.getBahanBaku();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bahanBaku.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(bahanBaku.get(i).getNama());
        }
        return sb.toString();
    }

}
