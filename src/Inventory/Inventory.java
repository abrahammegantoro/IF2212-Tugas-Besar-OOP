package src.Inventory;

import java.util.HashMap;
import java.util.Map;

import src.Item.Item;
// import src.Item.Masakan.Masakan; // buat driver

//GW BIKIN GENERICS BIAR MEMENUHI SYARAT TUGAS
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

    // remove item by item's name
    public void removeItem(T item) {
        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            if (entry.getKey() instanceof Item) {
                Item inventoryItem = (Item) entry.getKey();
                if (inventoryItem.getNama().equalsIgnoreCase(((Item) item).getNama())) {
                    items.remove(entry.getKey());
                    return;
                }
            }
        }
        System.out.println("Item tidak ada di inventory");
    }

    public int getAmount(T item) {
        if (items.containsKey(item)) {
            return items.get(item);
        } else {
            return 0;
        }
    }

    // getItems
    public Map<T, Integer> getItems() {
        return items;
    }

    public boolean isItemExist(T item) {
        return items.containsKey(item);
    }

    public void showInventory() {
        System.out.println("Inventory: ");
        System.out.println("Item_name\t\tAmount");
        System.out.println("---------------------------------");
    
        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            Item item = (Item) entry.getKey();
            int amount = entry.getValue();
            System.out.printf("%-20s\t%d\n", item.getNama(), amount);
        }
    }

    // Driver code
    // public static void main(String[] args) {
    // Inventory<Item> inventory = new Inventory<>();
    // Masakan nasiAyam = new Masakan("Nasi Ayam", 16);
    // Masakan nasiKari = new Masakan("Nasi Kari", 30);

    // try {
    // inventory.addItem(nasiAyam, 1);
    // inventory.addItem(nasiKari, 15);
    // inventory.showInventory();

    // System.out.println("\nKekenyangan Nasi Ayam : " + nasiAyam.getKekenyangan());
    // } catch (IllegalArgumentException e) {
    // System.out.println("Terjadi kesalahan: " + e.getMessage());
    // }
    // }
}
