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
        showMakanan(sim.getInventory());

        Scanner input = new Scanner(System.in);

        System.out.print("Pilih Makanan :  ");
        int nomorMakanan = input.nextInt();

        Item makanan = null;
        int counter = 1;
        for (Map.Entry<Item, Integer> entry : sim.getInventory().getItems().entrySet()) {
            Item item = entry.getKey();
            if (item instanceof Edible) {
                if (counter == nomorMakanan) {
                    makanan = item;
                    break;
                }
                counter++;
            }
        }

        final Item makananAkhir = makanan;

        Thread makan = new Thread(new Runnable() {
            public void run() {
                try {
                    if (makananAkhir != null) {
                        sim.getInventory().removeItem(makananAkhir);
                        Edible kekenyangan = (Edible) makananAkhir;
                        sim.setKekenyangan(sim.getKekenyangan() + kekenyangan.getKekenyangan());
                        Thread.sleep(30000);
                    }
                } catch (InterruptedException e) {
                    System.out.println("Thread interrupted");
                }
            }
        });

        makan.start();
        input.close();

        // note : waktu makan belum diurus
    }

    public static void showMakanan(Inventory<Item> inventory) {
        System.out.println("Inventory: ");
        System.out.println("Makanan\t\tAmount");
        System.out.println("---------------------------------");

        for (Map.Entry<Item, Integer> entry : inventory.getItems().entrySet()) {
            Item item = (Item) entry.getKey();
            int amount = entry.getValue();
            if (item instanceof Edible) {
                System.out.printf("%-20s\t%d\n", item.getNama(), amount);
            }
        }
    }
}
