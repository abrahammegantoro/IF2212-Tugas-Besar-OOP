package src.Item.Furniture;
import src.Sim.Sim;
import src.World.Point;

public class Toilet extends Furniture{
    public Toilet(Point lokasi) {
        super("Buang air", lokasi, 1, 1, 50);
    }

    public void buangAir(Sim sim) {
        sim.setKekenyangan(sim.getKekenyangan() - 20);
        sim.setMood(sim.getMood() + 10);
    }
}
