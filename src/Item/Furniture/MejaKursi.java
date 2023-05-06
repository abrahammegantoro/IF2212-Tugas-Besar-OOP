package src.Item.Furniture;

import src.Inventory.Inventory;
import src.Item.Edible;
import src.Item.Item;
import src.MainMenu.MainMenu;
import src.Sim.Sim;
// import src.Item.BahanBaku.BahanBaku;
// import src.Item.Masakan.Masakan;
import src.World.Time;

import java.util.Map;
import java.util.Scanner;

public class MejaKursi extends Furniture {
    public MejaKursi() {
        super("Meja dan Kursi", 3, 3, 50);
    }

    public static void makan(Sim sim) {
        if (!showMakanan(sim.getInventory())) {
            return;
        };

        Scanner input = new Scanner(System.in);

        System.out.print("Pilih Makanan :  ");
        String namaMakanan = input.nextLine();

        Item makanan = null;
        for (Map.Entry<Item, Integer> entry : sim.getInventory().getItems().entrySet()) {
            Item item = entry.getKey();
            if (item instanceof Edible) {
                if (item.getNama().equals(namaMakanan)) {
                    makanan = item;
                    break;
                }
            }
        }

        final Item makananAkhir = makanan;

        Thread makan = new Thread(new Runnable() {
            public void run() {
                try {
                    if (makananAkhir != null) {
                        int durasi = 30;
                        int tempDay = Time.getInstance().getCurrentDay();
                        int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                        while (durasi > 0) {
                            System.out.println("Sisa waktu makan : " + durasi);
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
                            durasi--;
                        }
                        if (sim.getWaktuSetelahMakan() >= 240) {
                            System.out.println(sim.getNama() + " belum buang air.");
                            sim.setKesehatan(sim.getKesehatan() - 5);
                            sim.setMood(sim.getMood() - 5);
                            sim.setWaktuSetelahMakan(0);
                            sim.setIsMakan(false);
                        }
                        int tidakTidur = sim.getLamaTidakTidur() / 600;
                        sim.setIsMakan(true);
                        if (!sim.getIsTidur() && tidakTidur > lastTidakTidur) {
                            System.out.println(sim.getNama() + " lelah karena tidak tidur.");
                            sim.setKesehatan(sim.getKesehatan() - 5);
                            sim.setMood(sim.getMood() - 5);
                        }
                        sim.getInventory().removeItem(makananAkhir);
                        Edible kekenyangan = (Edible) makananAkhir;
                        sim.setKekenyangan(sim.getKekenyangan() + kekenyangan.getKekenyangan());
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        });

        makan.start();

        try {
            makan.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

    }

    public static boolean showMakanan(Inventory<Item> inventory) {
        System.out.println("Inventory: ");
        System.out.printf("| %-20s| %s\n", "Makanan", "Amount");
        System.out.println("---------------------------------");
        
        boolean foundEdible = false;
        
        for (Map.Entry<Item, Integer> entry : inventory.getItems().entrySet()) {
            Item item = (Item) entry.getKey();
            int amount = entry.getValue();
            if (item instanceof Edible) {
                System.out.printf("| %-20s| %d\n", item.getNama(), amount);
                foundEdible = true;
            }
        }

        if (!foundEdible) {
            System.out.println("Tidak ada makanan di inventory");
        }

        return foundEdible;
    }
}
