package src.Item.Masakan;


import src.Item.BahanBaku.*;

public class TumisSayur extends Masakan{
    static{
        bahanBaku.add(new Wortel("Wortel"));
        bahanBaku.add(new Bayam("Bayam"));
    }

    // Konstruktor
    public TumisSayur(int porsi){
        super(porsi, 5);
    }

    public String getNama(){
        return "TumisSayur";
    }
}
