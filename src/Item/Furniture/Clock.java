package src.Item.Furniture;

import src.World.Time;

public class Clock extends Furniture {
    public Clock(){
        super("Jam", 1, 1, 10);
    }

    public void lihatWaktu(){
        System.out.println(Time.getInstance().getTimeRemaining());
    }

}

