package src.Sim;
import src.Pekerjaan.Pekerjaan;
import src.Inventory.Inventory;


public class Sim {
    private String nama;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private boolean isIdle;
    private Point lokasi;
    private Point posisiRuangan;
    private Point posisiRumah;
    private String status;

    public void olahraga(int lamaOlahraga) {
        if (lamaOlahraga % 20 != 0) {
            System.out.println("Durasi olahraga harus kelipatan 20");
            return;
        }

        kesehatan += (lamaOlahraga / 20) * 5;
        mood += (lamaOlahraga / 20) * 10;
        kekenyangan -= (lamaOlahraga / 20) * 5;
    }
}
