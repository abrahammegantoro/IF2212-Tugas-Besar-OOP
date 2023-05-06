package src.Item.Furniture;

import src.MainMenu.MainMenu;
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
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang sholat...");
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
                        System.out.println("Sholat dibatalkan");
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
