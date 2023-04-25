package src.Inventory;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Inventory<T> { // T adalah tipe data yang akan digunakan untuk inventory
    private Map<T, Integer> items; // T adalah

    public Inventory() {
        // Inisialisasi HashMap kosong
        this.items = new HashMap<>();
    }

    public void addItem(T item, int amount) {
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

    public void removeItem(T item, int amount) {
        if (items.containsKey(item)) {
            if (items.get(item) > amount) {
                items.put(item, items.get(item) - amount);
            } else {
                items.remove(item);
            }
        } else {
            System.out.println("Item tidak ada di inventory sehingga tidak dapat dihapus");
        }
    }

    public void showInventory() { // show inventory SEMENTARA karena gpt ngelag
        // show what class that item is
        System.out.println("Inventory: ");
        System.out.println("Item\t\tAmount");
        System.out.println("-----------------------");
        for (Map.Entry<T, Integer> entry : items.entrySet()) {
            System.out.printf("%-15s %d%n", entry.getKey(), entry.getValue());
        }
    }

//     // Driver code SEMENTARA
//     public static void main(String[] args) {
//         // Inventory<String> inventory = new Inventory<>();
//         // inventory.addItem("Beras", 10);
//         // inventory.addItem("Beras", 10);
//         // inventory.addItem("Telur", 5);
//         // inventory.addItem("Telur", 5);
//         // inventory.addItem("Telur", 5);

//         // inventory.showInventory();

//         Inventory<Object> inventory = new Inventory<>();
//         inventory.addItem(new Point(1, 2), 10);
//         inventory.addItem(new Point(1, 2), 10);
//         inventory.addItem(5, 5);
//         inventory.addItem(3, 5);
//         inventory.addItem("Anjay", 5);

//         inventory.showInventory();

//         // masih nunggu class Item...
//         // Inventory<Item> inventory = new Inventory<>();
//         // inventory.addItem(new Masakan(blablabla), 10);
//     }
}
