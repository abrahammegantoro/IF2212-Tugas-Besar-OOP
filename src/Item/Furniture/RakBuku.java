package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class RakBuku extends Furniture{
    public RakBuku() {
        super("Rak Buku", 3, 1, 100);
    }

    public static void bacaBuku(Sim sim){
        Thread bacaBukuThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 20;
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang membaca buku...");
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
                        }
                        sim.incrementLamaTidakTidur();
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Membaca buku dibatalkan");
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
        bacaBukuThread.start();
        
        try {
            bacaBukuThread.join();
            sim.setStatus("Membaca Buku");
            sim.setKekenyangan(sim.getKekenyangan() - 10);
            sim.setMood(sim.getMood() + 5);
        } catch (InterruptedException e) {
            System.out.println("Membaca buku dibatalkan");
        }
    }
    
}
