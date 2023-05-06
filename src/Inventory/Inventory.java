package src.Inventory;

import java.util.HashMap;
import java.util.Map;

import src.Item.Item;
import src.Item.Furniture.Furniture;
import src.Item.Masakan.Masakan; // buat driver

//GW BIKIN GENERICS BIAR MEMENUHI SYARAT TUGAS
public class Inventory<T extends Item> { // T adalah tipe data yang akan digunakan untuk inventory
    private Map<T, Integer> items; // T adalah tipe data yang akan digunakan untuk inventory

    public Inventory() {
        this.items = new HashMap<>();
    }

    public void addItem(T item, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah item tidak boleh kurang dari 1");
        }

        // add item to the inventory, if item already exist (item with same name) then
        // increase the quantity
        for (T objek : items.keySet()) {
            if (objek.getNama().equalsIgnoreCase(item.getNama())) {
                items.put(objek, items.get(objek) + amount);
                return;
            }
        }

        // if item doesn't exist in the inventory, then add it
        items.put(item, amount);
    }

    // remove item by item's name
    public void removeItem(T item) {
        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            if (entry.getKey() instanceof Item) {
                Item inventoryItem = (Item) entry.getKey();
                // Kurang 1, kalau cuman 1 berarti remove
                if (inventoryItem.getNama().equalsIgnoreCase(item.getNama())) {
                    if (entry.getValue() == 1) {
                        items.remove(entry.getKey());
                        return;
                    } else {
                        items.put(entry.getKey(), entry.getValue() - 1);
                        return;
                    }
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

    // check if item exist in the inventory
    public Furniture getFurniture(String namaFurniture) {
        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            if (entry.getKey() instanceof Furniture) {
                Furniture furniture = (Furniture) entry.getKey();
                if (furniture.getNama().equalsIgnoreCase(namaFurniture)) {
                    return furniture;
                }
            }
        }
        return null;
    }

    public boolean isItemExist(T item) {
        return items.containsKey(item);
    }

    public void showInventory() {
        System.out.println("Inventory: ");
        System.out.println("Item     \t\tAmount");
        System.out.println("---------------------------------");

        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            Item item = (Item) entry.getKey();
            int amount = entry.getValue();
            System.out.printf("%-20s\t%d\n", item.getNama(), amount);
        }
    }

    public void showFurnitureInventory() {
        System.out.println("Inventory: ");
        System.out.println("Furniture\t\tAmount");
        System.out.println("---------------------------------");

        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            if (entry.getKey() instanceof Furniture) {
                Item item = (Item) entry.getKey();
                int amount = entry.getValue();
                System.out.printf("%-20s\t%d\n", item.getNama(), amount);
            }
        }
    }

    // Driver code
    public static void main(String[] args) {
        Inventory<Item> inventory = new Inventory<>();
        Masakan nasiAyam = new Masakan("Nasi Ayam", 16);
        Masakan nasiKari = new Masakan("Nasi Kari", 30);
        Masakan nasiKari2 = new Masakan("Nasi Kari", 30);

        try {
            inventory.addItem(nasiAyam, 1);
            inventory.addItem(nasiKari, 15);
            inventory.addItem(nasiKari2, 21);
            inventory.showInventory();

            System.out.println("\nKekenyangan Nasi Ayam : " + nasiAyam.getKekenyangan());
        } catch (IllegalArgumentException e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }
}
