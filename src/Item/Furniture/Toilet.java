package src.Item.Furniture;
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
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang buang air...");
                        Time.getInstance().incrementTime();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Buang air dibatalkan");
                        return;
                    }
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
