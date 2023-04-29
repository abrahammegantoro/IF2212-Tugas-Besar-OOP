package src.Item.Furniture;

import src.Item.Item;
import src.Item.Buyable;
import java.util.Random;

public abstract class Furniture extends Item implements Buyable {
    private int panjang;
    private int lebar;
    private int harga;

    public Furniture(String nama, int panjang, int lebar, int harga) {
        super(nama);
        this.panjang = panjang;
        this.lebar = lebar;
        this.harga = harga;
    }

    public int getPanjang() {
        return panjang;
    }

    public int getLebar() {
        return lebar;
    }

    @Override
    public int getHarga() {
        return harga;
    }

    @Override
    public int getDeliveryTime() {
        Random random = new Random();
        return random.nextInt(1, 5) * 30;
    }
}
