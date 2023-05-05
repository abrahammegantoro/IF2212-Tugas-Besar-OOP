package src.Item.Furniture;

import src.Inventory.Inventory;
import src.Item.Edible;
import src.Item.Item;
import src.Sim.Sim;
// import src.Item.BahanBaku.BahanBaku;
// import src.Item.Masakan.Masakan;

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
                        while (durasi > 0) {
                            System.out.println("Sisa waktu makan : " + durasi);
                            sim.decrementBeliBarangTime();
                            sim.decrementUpgradeRumahTime();
                            sim.setPekerjaanBaru();
                            Thread.sleep(1000);
                            durasi--;
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
        input.close();

        // note : waktu makan belum diurus
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
