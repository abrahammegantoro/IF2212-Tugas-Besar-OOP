package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class Sajadah extends Furniture{
    public Sajadah() {
        super("Sajadah", 2, 1, 10);
    }
    
    public static void sholat(Sim sim){
        Thread sholatThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 15;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang sholat...");
                        Time.getInstance().incrementTime();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Sholat dibatalkan");
                        return;
                    }
                }
            }
        });
        sholatThread.start();
        
        try {
            sholatThread.join();
            sim.setStatus("Sholat");
            sim.setKekenyangan(sim.getKekenyangan() - 5);
            sim.setMood(sim.getMood() + 15);
            sim.setKesehatan((sim.getKesehatan() + 5));
        } catch (InterruptedException e) {
            System.out.println("Sholat dibatalkan");
        }
    }
}
