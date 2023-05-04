package src;

import src.MainMenu.MainMenu;

public class Main {
    public static void main(String[] args) {
        try {
            MainMenu.showGameMenu();
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}