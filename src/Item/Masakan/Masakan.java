package src.Item.Masakan;
import src.Item.Item;
import src.Item.Edible;
import src.Item.BahanBaku.*;

import java.util.ArrayList;

public abstract class Masakan extends Item implements Edible {
    protected static ArrayList<BahanBaku> bahanBaku = new ArrayList<>();
    private int kekenyangan;
    private int porsi;

    //Konstruktor
    public Masakan(int porsi, int kekenyangan){
        this.porsi = porsi;
        this.kekenyangan = kekenyangan; 
    }

    // Getter
    public int getKekenyangan(){
        return kekenyangan;
    }

    public int getPorsi(){
        return porsi;
    }

    public ArrayList<BahanBaku> getBahanBaku(){
        return bahanBaku;
    }

    public float getWaktuMasak(){
        return (float) (1.5* getKekenyangan());
    }

}
