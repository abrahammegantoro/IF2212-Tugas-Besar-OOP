package src.Rumah;

import java.util.*;
import src.Ruangan.Ruangan;
import src.Sim.Sim;
import src.World.Point;

public class Rumah {
    private String namaRumah;
    private Point lokasi;
    private List<Ruangan> daftarRuangan;

    public Rumah(Sim sim, Point lokasi, Ruangan ruangan) {
        this.namaRumah = sim.getNama();
        this.lokasi = lokasi;
        daftarRuangan = new ArrayList<Ruangan>();
        daftarRuangan.add(ruangan);
    }

    public String namaRumah(){
        return namaRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public void upgradeRumah(Sim sim, String namaRuangan, Ruangan ruangan) {
        if (sim.getUang() < 1500) {
            System.out.println("Uang tidak cukup");
            return;
        }

        sim.setUang(sim.getUang() - 100);
        daftarRuangan.add(ruangan);
    }
}
