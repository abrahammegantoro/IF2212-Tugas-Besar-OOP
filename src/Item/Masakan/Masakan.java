package src.Item.Masakan;
import src.Item.Item;
import src.Item.Edible;
import src.Item.BahanBaku.*;

import java.util.ArrayList;

public class Masakan extends Item implements Edible {
    // Atribut
    private ArrayList<BahanBaku> bahanBaku = new ArrayList<>();
    private int kekenyangan;

    //Konstruktor
    public Masakan(String namaMasakan, int kekenyangan){
        super(namaMasakan);
        this.kekenyangan = kekenyangan; 
    }

    @Override
    public int getKekenyangan(){
        return kekenyangan;
    }

    //addBahanBaku
    public void addBahanBaku(BahanBaku bahan) {
        bahanBaku.add(bahan);
    }

    public ArrayList<BahanBaku> getBahanBaku() {
        return bahanBaku;
    }

    public float getWaktuMasak(){
        return (float) (1.5* getKekenyangan());
    }

}
