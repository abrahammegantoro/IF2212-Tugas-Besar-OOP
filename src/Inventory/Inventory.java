package src.Inventory;

import java.util.HashMap;
import java.util.Map;

import src.Item.Item;
import src.Item.Masakan.Masakan; // buat driver

public class Inventory<T> { // T adalah tipe data yang akan digunakan untuk inventory
    private Map<T, Integer> items; // T adalah tipe data yang akan digunakan untuk inventory

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(T item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah item tidak boleh kurang dari 1");
        }

        if (items.containsKey(item)) {
            items.put(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }

    public T getItem(T item) { // ambil item dan kurangi jumlahnya 1
        if (items.containsKey(item)) {
            if (items.get(item) > 1) {
                items.put(item, items.get(item) - 1);
            } else {
                items.remove(item);
            }
            return item;
        } else {
            System.out.println("Item tidak ada di inventory");
            return null;
        }
    }

    public int getAmount(T item) {
        if (items.containsKey(item)) {
            return items.get(item);
        } else {
            return 0;
        }
    }

    public void showInventory() {
        // inventory dipastikan mengandung item dengan tipe data Item
        System.out.println("Inventory: ");
        System.out.println("Item_name\t\tAmount");
        System.out.println("---------------------------------");

        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            Item item = (Item) entry.getKey();
            int amount = entry.getValue();
            System.out.println(item.getNama() + "\t\t" + amount);
        }
    }

    // Driver code
    // public static void main(String[] args) {
    //     Inventory<Item> inventory = new Inventory<>();
    //     Masakan nasiAyam = new Masakan("Nasi Ayam", 16);
    //     Masakan nasiKari = new Masakan("Nasi Kari", 30);
    
    //     try {
    //         inventory.addItem(nasiAyam, 1);
    //         inventory.addItem(nasiKari, 15);
    //         inventory.showInventory();
    
    //         System.out.println("\nKekenyangan Nasi Ayam : " + nasiAyam.getKekenyangan());
    //     } catch (IllegalArgumentException e) {
    //         System.out.println("Terjadi kesalahan: " + e.getMessage());
    //     }
    // }
}
