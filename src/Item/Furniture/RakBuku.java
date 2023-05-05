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
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang membaca buku...");
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Membaca buku dibatalkan");
                        return;
                    }
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
