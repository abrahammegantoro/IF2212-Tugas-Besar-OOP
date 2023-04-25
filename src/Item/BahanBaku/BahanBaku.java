package src.Item.BahanBaku;
import src.Item.Item;
import src.Item.Buyable;
import src.Item.Edible;
import java.util.Random;

public abstract class BahanBaku extends Item implements Edible, Buyable {
    private String nama;
    private int harga;
    private int kekenyangan;

    public BahanBaku(String nama, int harga, int kekenyangan) {
        this.nama = nama;
        this.harga = harga;
        this.kekenyangan = kekenyangan;
    }

    public BahanBaku(String nama){
        this.nama = nama;
    }


    public String nama(){
        return nama;
    }

    @Override
    public int getHarga(){
        return harga;
    }

    @Override
    public int getDeliveryTime(){
        Random random = new Random();
        return random.nextInt(1, 5)*30;
    }

    @Override
    public int getKekenyangan() {
        return kekenyangan;
    }
}
