package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class TV extends Furniture{
    public TV() {
        super("TV", 1, 1, 150);
    }

    public static void nontonTV(Sim sim){
        Thread nontonTVThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 40;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang menonton TV...");
                        Time.getInstance().incrementTime();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Menonton TV dibatalkan");
                        return;
                    }
                }
            }
        });
        nontonTVThread.start();
        
        try {
            nontonTVThread.join();
            sim.setStatus("Menonton TV");
            sim.setKekenyangan(sim.getKekenyangan() - 30);
            sim.setMood(sim.getMood() + 30);
            sim.setKesehatan(sim.getKesehatan() - 15);
        } catch (InterruptedException e) {
            System.out.println("Menonton TV dibatalkan");
        }
    }
}