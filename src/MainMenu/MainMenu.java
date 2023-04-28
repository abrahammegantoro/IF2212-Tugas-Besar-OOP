package src.MainMenu;
import java.util.*;

import src.Sim.Sim;

public class MainMenu {
 
    public void start(){

    }

    public void load(){

    }

    public void addSim(){
        Scanner in = new Scanner(System.in);
        System.out.print("Silahkan Masukkan nama sim : ");
        String nama = in.next();
        
        Sim simBaru = new Sim(nama);

        //nanti ditambah program untuk menambah sim ini ke daftar sim sudah dibuat
    }

    public void changeSim(){

    }

    public void viewSimInfo(Sim sim){
        System.out.println("Berikut adalah informasi sim yang sedang Anda mainkan");
        System.out.println("Nama        : " + sim.getNama());
        System.out.println("Pekerjaan   : " + sim.getPekerjaan());
        System.out.println("Kesehatan   : " + sim.getKesehatan());
        System.out.println("Kekenyangan : " + sim.getKekenyangan());
        System.out.println("Mood        : " + sim.getMood());
        System.out.println("Uang        : " + sim.getUang());

    }

    public void viewCurrentLocation(Sim sim){
        System.out.println("Sim sedang berada di rumah " + sim.getNamaRumahSaatIni() + ", pada ruangan " + sim.getNamaRuanganSaatIni());
    }

    public void help(){
        System.out.println("Simplicity merupakan permainan berbasis Command line interface.");
        System.out.println("Dalam game ini, Anda akan membuat seorang Sim dan menjalankan kehidupan sebagai Sim.");
        System.out.println("Sim memiliki banyak aksi yang dapat dilakukan oleh dirinya, dan setiap aksi itu dapat");
        System.out.println("mempengaruhi kesejahteraan dari Sim yang Anda mainkan");

        //Nanti ditambah lagi lebih lengkap
    }

    public void exit(){
        System.out.println("Terimakasih telah memainkan SImplicity");
        System.exit(0);
    }
}
