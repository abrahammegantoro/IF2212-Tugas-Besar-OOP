package src.Item.Furniture;

import src.MainMenu.MainMenu;
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
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang menonton TV...");
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
                        System.out.println("Menonton TV dibatalkan");
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