package src.Item.Furniture.Bed;

import src.Item.Furniture.Furniture;
import src.MainMenu.MainMenu;
import src.Sim.Sim;
import src.World.Time;

import java.util.Scanner;

public abstract class Bed extends Furniture {
    // Scanner
    private static Scanner input = new Scanner(System.in);

    // Konstruktor
    public Bed(String nama, int panjang, int lebar, int harga) {
        super(nama, panjang, lebar, harga);
    }

    public static void tidur(Sim sim) {
        // Input durasi tidur
        System.out.print("Durasi Tidur :  ");
        int durasi = input.nextInt();

        // Periksa apakah input durasi tidur valid
        while (durasi < 180) {
            System.out.println("Durasi tidur tidak valid, mohon masukkan durasi minimal 180 detik (3 menit)");
            durasi = input.nextInt();
        }
        final int durasiAkhir = durasi;
        Thread tidurThread = new Thread(new Runnable() {
            public void run() {
                sim.setStatus("Tidur");
                int counter = 0;
                int tempDay = Time.getInstance().getCurrentDay();
                while (counter < durasiAkhir) {
                    try {
                        System.out.println(counter);
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
                        sim.incrementLamaTidur();
                        counter++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (sim.getWaktuSetelahMakan() >= 240) {
                    System.out.println(sim.getNama() + " belum buang air.");
                    sim.setKesehatan(sim.getKesehatan() - 5);
                    sim.setMood(sim.getMood() - 5);
                    sim.setWaktuSetelahMakan(0);
                    sim.setIsMakan(false);
                }
            }
        });

        tidurThread.start();
        try {
            tidurThread.join();
            if (sim.getLamaTidur() >= 180) {
                sim.setIsTidur(true);
            } 
            if (durasiAkhir > 240) {
                sim.setMood(sim.getMood() + (durasiAkhir / 240 * 30));
                sim.setKesehatan(sim.getMood() + (durasiAkhir / 240 * 20));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        sim.setStatus("Baru Bangun dari Tidur");

        System.out.println(sim.getNama() + " telah bangun.");
    }
}
