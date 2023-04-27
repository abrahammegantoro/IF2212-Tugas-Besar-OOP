package src.Item.Furniture.Bed;

import src.Item.Furniture.Furniture;
import src.Sim.Sim;
import src.World.Point;

import java.util.Scanner;

public abstract class Bed extends Furniture{
    //Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor 
    public Bed(String nama, Point lokasi, int panjang, int lebar, int harga){
        super("Bed", lokasi, panjang, lebar, harga);
    }

    public void tidur(Sim sim){
        // Input durasi tidur
        System.out.print("Durasi Tidur :  ");
        final int durasi = input.nextInt();

        // Periksa apakah input durasi tidur valid
        if (durasi < 180){
            System.out.println("Durasi tidur tidak valid, mohon masukkan durasi maksimal 180 detik (3 menit)");
        }
        else {
            // kondisi sdh tdr seharian??
            // WIP
            // Thread tidur
            Thread tidurThread = new Thread(() -> {
                sim.setStatus("Tidur");
                System.out.println(sim.getNama() + " sedang tidur selama " + durasi + " detik.");
            try {
                Thread.sleep(durasi * 1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }

            // Menambahkan efek tidur pada status Sim
            // Efek pada status baru ada setelah Sim tidur selama 240 detik (4 menit)
            int fullSleep = durasi / 240;
            sim.setMood(sim.getMood() + (fullSleep * 30));
            sim.setKesehatan(sim.getMood() + (fullSleep * 20));

            sim.setStatus("Baru Bangun dari Tidur");

            System.out.println(sim.getNama() + " telah bangun.");
        });

        //Memulai Thread
        tidurThread.start();
        
    }

    }
}
