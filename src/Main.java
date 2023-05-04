package src;
import java.util.*;

import src.MainMenu.MainMenu;
import src.Sim.Sim;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenu.showGameMenu();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}