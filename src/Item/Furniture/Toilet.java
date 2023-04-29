package src.Item.Furniture;
import src.Sim.Sim;
import src.World.Time;

public class Toilet extends Furniture{
    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public void buangAir(Sim sim) {
        sim.setStatus("Buang Air");
        sim.setKekenyangan(sim.getKekenyangan() - 20);
        sim.setMood(sim.getMood() + 10);
        Time.getInstance().consumeTime(10);
    }
}
