package src;
import java.util.*;

import src.MainMenu.MainMenu;
import src.Sim.Sim;

public class Main {
    // ArrayList<Sim> listSim = new ArrayList<Sim>();
    // public static long timeNow = 0;

    // public static void runTheTime() {
    //     Thread temp = new Thread() {
    //         public void run() {
    //             while (true) {
    //                 try {
    //                     if (ada sim yang bekerja dan memakan waktu)
    //                     Thread.sleep(1000);
    //                     timeNow++;
    //                 } catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //             }
    //         }
    //     };
    //     temp.start();
    // }
    public static void main(String[] args) {
        MainMenu.showGameMenu();
    }
}