package src.Item.Furniture.Stove;

// import src.Item.BahanBaku.BahanBaku;
// import src.Item.Masakan.Masakan;
// import src.Sim.Sim;

public class GasStove extends Stove {
    public GasStove() {
        super("Kompor Gas", 2, 1, 100);
    }

    //driver
    // public static void main(String[] args) {
    //     Sim sim1 = new Sim("Sim1");
    //     BahanBaku susu = new BahanBaku("Susu", 2, 1);
    //     BahanBaku kacang = new BahanBaku("Kacang", 2, 2);
    //     sim1.addItemToInventory(susu, 1);
    //     sim1.addItemToInventory(kacang, 1);

    //     BahanBaku wortel = new BahanBaku("Wortel", 3, 2);
    //     BahanBaku bayam = new BahanBaku("Bayam", 3, 2);
    //     sim1.addItemToInventory(wortel, 1);
    //     sim1.addItemToInventory(bayam, 1);

    //     // print all sim1 inventory items using showInventory()
    //     // sim1.viewInventory();

    //     GasStove gs = new GasStove(new Point(1, 1));
    //     // System.out.println(gs.getNama());
    //     // System.out.println(gs.getPanjang());
    //     // System.out.println(gs.getLebar());
    //     // System.out.println(gs.getHarga());
    //     // System.out.println(gs.getLokasi().getX());
    //     // System.out.println(gs.getLokasi().getY());

    //     Masakan susuKacang = new Masakan("Susu Kacang", 5);
    //     Masakan tumisSayur = new Masakan("Tumis Sayur", 5);
    //     susuKacang.addBahanBaku(new BahanBaku("Susu", 2, 1));
    //     susuKacang.addBahanBaku(new BahanBaku("Kacang", 2, 2));
    //     tumisSayur.addBahanBaku(new BahanBaku("Wortel", 3, 2));
    //     tumisSayur.addBahanBaku(new BahanBaku("Bayam", 3, 2));
        
    //     // System.out.println(gs.cekBahanBaku(sim1.getInventory(), susuKacang));
    //     gs.masak(sim1, sim1.getInventory(), susuKacang);
    //     // System.out.println(sim1.getStatus());
    // }
}
