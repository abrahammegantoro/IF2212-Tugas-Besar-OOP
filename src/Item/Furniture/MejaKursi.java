package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Point;
import src.Item.Edible;
import src.Inventory.Inventory;

public class MejaKursi extends Furniture {
    public MejaKursi(Point lokasi){
        super("Meja dan Kursi", lokasi, 3, 3, 50);
    }

    public void makan(Sim sim, Inventory inventory, Edible makanan ) {
        sim.getInventory().removeItem(makanan, 1);
        sim.setKekenyangan(sim.getKekenyangan() + makanan.getKekenyangan());
        
        // Waktu Makan Belom
    }
}
