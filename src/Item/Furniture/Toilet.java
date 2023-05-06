package src.Item.Furniture;
import src.MainMenu.MainMenu;
import src.Sim.Sim;
import src.World.Time;

public class Toilet extends Furniture{
    public Toilet() {
        super("Toilet", 1, 1, 50);
    }

    public static void buangAir(Sim sim) {
        Thread buangAirThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 10;
                int tempDay = Time.getInstance().getCurrentDay();
                int lastTidakTidur = sim.getLamaTidakTidur() / 600;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang buang air...");
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                        if (tempDay != Time.getInstance().getCurrentDay()) {
                            sim.setIsTidur(false);
                            sim.setLamaTidakTidur(0);
                            sim.setLamaTidur(0);
                            MainMenu.setAddSim(false);
                        }
                        sim.incrementLamaTidakTidur();
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Buang air dibatalkan");
                        return;
                    }
                }
                sim.setIsMakan(false);
                sim.setWaktuSetelahMakan(0);
                int tidakTidur = sim.getLamaTidakTidur() / 600;
                if (!sim.getIsTidur() && tidakTidur > lastTidakTidur) {
                    System.out.println(sim.getNama() + " lelah karena tidak tidur.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
                }
            }
        });
        buangAirThread.start();
        
        try {
            buangAirThread.join();
            sim.setStatus("Buang Air");
            sim.setKekenyangan(sim.getKekenyangan() - 20);
            sim.setMood(sim.getMood() + 10);
        } catch (InterruptedException e) {
            System.out.println("Buang air dibatalkan");
        }
    }
}
