package src.Item.Furniture;

import src.Sim.Sim;
import src.World.Time;

public class Piano extends Furniture{
    public Piano() {
        super("Piano", 2, 1, 100);
    }

    public static void mainPiano(Sim sim){
        Thread mainPianoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int durasi = 25;
                while (durasi > 0) {
                    try {
                        System.out.println("Sedang bermain piano...");
                        Time.getInstance().incrementTime();
                        Thread.sleep(1000);
                        durasi--;
                    } catch (InterruptedException e) {
                        System.out.println("Bermain piano dibatalkan");
                        return;
                    }
                }
            }
        });
        mainPianoThread.start();
        
        try {
            mainPianoThread.join();
            sim.setStatus("Bermain Piano");
            sim.setKekenyangan(sim.getKekenyangan() - 20);
            sim.setMood(sim.getMood() + 30);
        } catch (InterruptedException e) {
            System.out.println("Bermain Piano dibatalkan");
        }
    }
    
}
