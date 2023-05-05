package src.Item.Furniture.Bed;

import src.Item.Furniture.Furniture;
import src.Sim.Sim;
import src.World.Time;

import java.util.Scanner;

public abstract class Bed extends Furniture{
    //Scanner
    private static Scanner input = new Scanner(System.in);

    //Konstruktor 
    public Bed(String nama, int panjang, int lebar, int harga){
        super(nama, panjang, lebar, harga);
    }

    public static void tidur(Sim sim){
        // Input durasi tidur
        System.out.print("Durasi Tidur :  ");
        int durasi = input.nextInt();

        // Periksa apakah input durasi tidur valid
        while (durasi < 180){
            System.out.println("Durasi tidur tidak valid, mohon masukkan durasi maksimal 180 detik (3 menit)");
            durasi = input.nextInt();
        }
        // kondisi sdh tdr seharian??
        // WIP
        // Thread tidur
        final int durasiAkhir = durasi;
        Thread tidurThread = new Thread(new Runnable() {
            public void run() {
                sim.setStatus("Tidur");
                int counter = 0;
                while (counter < durasiAkhir) {
                    try {
                        Thread.sleep(durasiAkhir * 1000);
                        Time.getInstance().incrementTime();
                        sim.decrementBeliBarangTime();
                        sim.decrementUpgradeRumahTime();
                        sim.setPekerjaanBaru();
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        tidurThread.start();
        try {
            tidurThread.join();
            if (durasiAkhir > 240) {
                sim.setMood(sim.getMood() + (durasiAkhir / 240 * 30));
                sim.setKesehatan(sim.getMood() + (durasiAkhir / 240 * 20));
            }
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    

    sim.setStatus("Baru Bangun dari Tidur");

    System.out.println(sim.getNama() + " telah bangun.");

        //Memulai Thread
        tidurThread.start();
        
    }
}
