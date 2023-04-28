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

        //nanti ditambah program untuk menambah sim ini ke list sim sudah dibuat
    }

    public void changeSim(){

    }

    public void viewSimInfo(){

    }

    public void viewCurrentLocation(){

    }

    public void help(){
        System.out.println("Simplicity merupakan ");
    }

    public void exit(){
        System.out.println("Terimakasih telah memainkan SImplicity");
        System.exit(0);
    }
}
