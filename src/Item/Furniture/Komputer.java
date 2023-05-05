package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class Komputer extends Furniture{
    public Komputer() {
        super("Komputer", 1, 1, 100);
    }

    public static void mainGame(Sim sim){
        Thread mainGameThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 30;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang bermain game...");
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Bermain Game dibatalkan");
                        return;
                    }
                }
            }
        });
        mainGameThread.start();
        
        try {
            mainGameThread.join();
            sim.setStatus("Bermain Game");
            sim.setKekenyangan(sim.getKekenyangan() - 20);
            sim.setMood(sim.getMood() + 20);
            sim.setKesehatan((sim.getKesehatan() - 10));
        } catch (InterruptedException e) {
            System.out.println("Bermain Game dibatalkan");
        }
    }
    
}
