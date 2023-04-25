package src.Item.Masakan;

import src.Item.BahanBaku.*;

public class NasiAyam extends Masakan{
    static{
        bahanBaku.add(new Nasi("Nasi"));
        bahanBaku.add(new Ayam("Ayam"));

    }

    // Konstruktor
    public NasiAyam(int porsi){
        super(porsi, 16);
    }

    public String getNama(){
        return "Nasi Ayam";
    }
}
