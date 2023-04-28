package src.Item.Furniture;

public class Clock extends Furniture {
    public Clock(Point lokasiClock){
        super("Jam", lokasiClock, 1, 1, 10);
    }

    public void lihatWaktu(){
        System.out.println(sim.getCurrentWorld().getClock().getRemainingTime());
    }

}

