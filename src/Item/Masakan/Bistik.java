package src.Item.Masakan;

import src.Item.BahanBaku.*;

public class Bistik extends Masakan{
    static{
        bahanBaku.add(new Kentang("Kentang"));
        bahanBaku.add(new Sapi("Sapi"));

    }

    // Konstruktor
    public Bistik(int porsi){
        super(porsi, 22);
    }

    public String getNama(){
        return "Bistik";
    }
}
