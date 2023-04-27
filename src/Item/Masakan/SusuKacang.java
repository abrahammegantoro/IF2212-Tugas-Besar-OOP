package src.Item.Masakan;


import src.Item.BahanBaku.*;

public class SusuKacang extends Masakan {
    static{
        bahanBaku.add(new Susu("Susu"));
        bahanBaku.add(new Kacang("Kacang"));
    }

    // Konstruktor
    public SusuKacang(int porsi){
        super(porsi, 5);
    }

    public String getNama(){
        return "Susu Kacang";
    }
}
