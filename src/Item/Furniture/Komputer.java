package src.Item.Furniture;

import src.MainMenu.MainMenu;
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
                        if (sim.getIsMakan()) {
                            sim.incrementWaktuSetelahMakan();
                        }
                        if (tempDay != Time.getInstance().getCurrentDay()) {
                            sim.setIsTidur(false);
                            sim.setLamaTidakTidur(0);
                            sim.setLamaTidur(0);
                            MainMenu.setAddSim(false);
                        }
                        sim.incrementLamaTidakTidur();
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Bermain Game dibatalkan");
                        return;
                    }
                }
                if (sim.getWaktuSetelahMakan() >= 240) {
                    System.out.println(sim.getNama() + " belum buang air.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
                    sim.setWaktuSetelahMakan(0);
                    sim.setIsMakan(false);
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
