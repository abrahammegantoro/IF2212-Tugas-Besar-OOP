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
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang mandi...");
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        if (tempDay != Time.getInstance().getCurrentDay()) {
                            sim.setIsTidur(false);
                            sim.setLamaTidakTidur(0);
                            sim.setLamaTidur(0);
                        }
                        sim.incrementLamaTidakTidur();
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Mandi dibatalkan");
                        return;
                    }
                }
                int tidakTidur = sim.getLamaTidakTidur() / 600;
                if (!sim.getIsTidur() && tidakTidur > lastTidakTidur) {
                    System.out.println(sim.getNama() + " lelah karena tidak tidur.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
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
