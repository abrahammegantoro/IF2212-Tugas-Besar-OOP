package src.Item.BahanBaku;
import src.Item.Buyable;
import src.Item.Edible;
import src.Item.Item;
import java.util.Random;

public class BahanBaku extends Item implements Edible, Buyable {
    private int harga;
    private int kekenyangan;

    public BahanBaku(String nama, int harga, int kekenyangan) {
        super(nama);
        this.harga = harga;
        this.kekenyangan = kekenyangan;
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
