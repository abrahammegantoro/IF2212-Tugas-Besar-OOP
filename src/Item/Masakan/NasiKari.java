package src.Item.Masakan;

import src.Item.BahanBaku.*;

public class NasiKari extends Masakan{
    static{
        bahanBaku.add(new Nasi("Nasi"));
        bahanBaku.add(new Ayam("Ayam"));
        bahanBaku.add(new Sapi("Sapi"));
        bahanBaku.add(new Wortel("Wortel"));

    }

    // Konstruktor
    public NasiKari(int porsi){
        super(porsi, 30);
    }

    public String getNama(){
        return "Nasi Kari";
    }
}
