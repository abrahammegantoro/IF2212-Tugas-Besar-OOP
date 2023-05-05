package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class Shower extends Furniture{
    public Shower() {
        super("Shower", 1, 1, 50);
    }
    
    public static void mandi(Sim sim){
        Thread mandiThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 15;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang mandi...");
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Mandi dibatalkan");
                        return;
                    }
                }
            }
        });
        mandiThread.start();
        
        try {
            mandiThread.join();
            sim.setStatus("Mandi");
            sim.setKekenyangan(sim.getKekenyangan() - 15);
            sim.setMood(sim.getMood() + 10);
        } catch (InterruptedException e) {
            System.out.println("Mandi dibatalkan");
        }
    }
}
