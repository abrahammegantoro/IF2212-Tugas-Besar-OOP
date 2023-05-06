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
                sim.setStatus("Bermain Game");
                int durasi = 30;
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang bermain game...");
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
                        System.out.println("Bermain Game dibatalkan");
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
        mainGameThread.start();
        
        try {
            mainGameThread.join();
            sim.setKekenyangan(sim.getKekenyangan() - 20);
            sim.setMood(sim.getMood() + 20);
            sim.setKesehatan((sim.getKesehatan() - 10));
        } catch (InterruptedException e) {
            System.out.println("Bermain Game dibatalkan");
        }
    }
    
}
