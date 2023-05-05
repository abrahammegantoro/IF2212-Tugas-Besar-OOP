package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class Teleskop extends Furniture{
    public Teleskop() {
        super("Teleskop", 1, 1, 100);
    }
    
    public static void lihatBintang(Sim sim){
        Thread melihatBintangThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 20;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang melihat bintang...");
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Melihat bintang dibatalkan");
                        return;
                    }
                }
            }
        });
        melihatBintangThread.start();
        
        try {
            melihatBintangThread.join();
            sim.setStatus("Melihat Bintang");
            sim.setKekenyangan(sim.getKekenyangan() - 10);
            sim.setMood(sim.getMood() + 10);
        } catch (InterruptedException e) {
            System.out.println("Melihat bintang dibatalkan");
        }
    }
}
