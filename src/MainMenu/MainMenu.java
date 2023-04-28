package src.MainMenu;
import java.util.*;

import src.Sim.Sim;

public class MainMenu {
    Scanner in = new Scanner(System.in);
 
    public void start(){
        //masih dalam proses
    }

    public void load(){
        //masih dalam proses
    }

    public void addSim(){
        System.out.print("Silahkan Masukkan nama sim : ");
        String nama = in.next();
        
        Sim simBaru = new Sim(nama);

        //nanti ditambah program untuk menambah sim ini ke daftar sim sudah dibuat
    }

    public void changeSim(){
        //masih dalam proses
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
        boolean end = false;
        int input;

        while(!end){
            System.out.println("HELP");
            System.out.println("1. Apa itu Simplicity?");
            System.out.println("2. Bagaimana cara memulai game ini?");
            System.out.println("3. Tentang Rumah");
            System.out.println("4. Tentang Pekerjaan");
            System.out.println("5. Tentang Kesejahteraan");
            System.out.println("0. Close\n");
            
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            input = in.nextInt();
            if (input == 1){
                System.out.println("Simplicity merupakan permainan berbasis Command line interface.");
                System.out.println("Dalam game ini, Anda akan membuat seorang Sim dan menjalankan kehidupan sebagai Sim.");
                System.out.println("Sebagai Sim, Anda akan tinggal dalam sebuah rumah dan melakukan berbagai aksi untuk menjalani kehidupan");
                System.out.println("Segera buat Sim dan mulai kehidupan Anda sebagai Sim!!!\n");
            } else if (input == 2){
                System.out.println("Pertama, Anda harus membuat seorang Sim yang dapat dilakukan di opsi addSim.");
                System.out.println("Setelah Sim dibuat, Sim Anda akan dispawn di suatu tempat bersamaan dengan rumah.");
                System.out.println("Di dalam rumah, Anda dapat meletakkan berbagai furniture dan melakukan aksi dengan item tersebut.");
                System.out.println("Item dapat beli dengan uang yang Anda miliki. Untuk mendapatkan uang Anda harus bekerja.");
                System.out.println("Selain itu, Sim Anda memiliki parameter kesejahteraan.");
                System.out.println("Sim Anda akan mati bila salah satu atau beberapa parameter tersebut mencapai nilai 0.");
                System.out.println("Kesejahteraan dapat dipengaruhi oleh aksi yang dilakukan oleh Sim.");
                System.out.println("Oleh karena itu, Anda harus melakukan berbagai macam aksi untuk menjalankan hidup sebagai Sim.");
            } else if (input == 3) {
                System.out.println("Setiap Sim yang Anda buat akan memiliki rumah yang diperoleh saat Sim tersebut dibentuk.");
                System.out.println("Rumah yang baru dibentuk akan terdiri dari 1 ruangan yang berukuran 6 x 6.");
                System.out.println("Di dalam ruangan, Anda dapat meletakkan berbagai macam furniture.");
                System.out.println("Furniture yang berada dalam ruangan dapat membuat Sim melakukan aksi khusus.");
                System.out.println("");

            } else if (input == 4) {
                
            } else if (input == 5) {
                
            } else if (input == 0) {
                end = true;
            }
        }
    }

    public void exit(){
        System.out.println("Terimakasih telah memainkan Simplicity");
        System.exit(0);
    }
}
