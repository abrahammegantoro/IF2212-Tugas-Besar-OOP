package src.Item.Furniture;

import src.Item.Item;
import src.Item.Buyable;
import src.World.Point;
import java.util.Random;

public abstract class Furniture extends Item implements Buyable {
    private int panjang;
    private int lebar;
    private int harga;
    private Point lokasi;

    public Furniture(String nama, Point lokasi, int panjang, int lebar, int harga) {
        super(nama);
        this.lokasi = lokasi;
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

    public Point getLokasi() {
        return lokasi;
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
