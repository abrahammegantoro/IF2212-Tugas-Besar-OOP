package src.World;

import java.util.ArrayList;
import java.util.List;

import src.Rumah.Rumah;
import src.Sim.Sim;

// Singleton design pattern
public class World {
    private static World instance = new World();
    private Rumah[][] gridRumah;
    private List<Rumah> daftarRumah;
    // private List<Sim> daftarSim;
    // waktu?

    private World() {
        this.gridRumah = new Rumah[64][64];
        this.daftarRumah = new ArrayList<Rumah>();
        // this.daftarSim = new ArrayList<Sim>();
    }

    public static World getInstance() {
        return instance;
    }

    // public void addSim(Sim sim) {
    //     this.daftarSim.add(sim);
    // }

    // public List<Sim> getDaftarSim() {
    //     return daftarSim;
    // }

    public void printWorld() {
        System.out.println("World :");
        System.out.println("-------");
        for (int i = 0; i < 64; i++) {
            System.out.print("|");
            for (int j = 0; j < 64; j++) {
                if (gridRumah[i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print("R ");
                }
            }
            System.out.println("|");
        }
        System.out.println("-------");
    }

    public List<Rumah> getDaftarRumah() {
        return daftarRumah;
    }

    public void addRumah(Sim sim) {
        // Temukan titik yang masih kosong pada gridRumah
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (gridRumah[i][j] == null) {
                    Point temp = new Point(i, j);
                    Rumah rumahBaru = new Rumah(sim, temp);
                    this.daftarRumah.add(rumahBaru);
                    gridRumah[i][j] = rumahBaru;
                    sim.setRumahUtama(rumahBaru);
                    sim.setRumahSaatIni(rumahBaru);
                    sim.setRuanganSaatIni(rumahBaru.getRuangan("Ruang Tamu"));
                    System.out.println("Rumah " + sim.getNama() + " berhasil dibuat.");
                    return;
                }
            }
        }
    }

    public void addRumah(Sim sim, Point location) {
        if (isCoordinateAvailable(location)) {
            Rumah rumahBaru = new Rumah(sim, location);
            this.daftarRumah.add(rumahBaru);
            gridRumah[(int) location.getX()][(int) location.getY()] = rumahBaru;
            sim.setRumahUtama(rumahBaru);
            sim.setRumahSaatIni(rumahBaru);
            sim.setRuanganSaatIni(rumahBaru.getRuangan("Ruang Tamu"));
            System.out.println("Rumah " + sim.getNama() + " berhasil dibuat.");
        } else {
            System.out.println("Koordinat sudah terisi oleh rumah lain.");
        }
    }
    
    public Rumah getRumah(String namaRumah) {
        for (Rumah rumah : daftarRumah) {
            if (rumah.getNamaRumah().equals(namaRumah)) {
                return rumah;
            }
        }
        return null;
    }

    public void printDaftarRumahExceptSim(String namaRumahSaatIni) {
        System.out.println("Daftar rumah yang dapat dikunjungi:");
        System.out.println("-------------------------------");
        int counter = 1;
        for (int i = 0; i < daftarRumah.size(); i++) {
            if (!daftarRumah.get(i).getNamaRumah().equals(namaRumahSaatIni)) {
                System.out.println(counter + ". " + daftarRumah.get(i).getNamaRumah());
                counter++;
            }
        }
        System.out.println("0. Exit");
        System.out.println("\n");
    }

    public boolean isCoordinateAvailable(Point location) {
        if (location.getX() < 0 || location.getY() < 0 || location.getX() > 64 || location.getY() > 64) {
            return false;
        }
        for (Rumah rumah : daftarRumah) {
            if (rumah.getLokasi().getX() == location.getX() && rumah.getLokasi().getY() == location.getY()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNamaRumahAvailable(String namaRumah) {
        for (Rumah rumah : daftarRumah) {
            if (rumah.getNamaRumah().equals(namaRumah)) {
                return true;
            }
        }
        return false;
    }

    // driver world
    // public static void main(String[] args) {
    //     World world = new World();
    //     Point point = new Point(0, 0);
    //     Sim sim = new Sim("Sim1");
    //     world.addSim(sim);
    //     world.addRumah(sim, new Point(0, 0));

    //     Sim sim2 = new Sim("Sim2");
    //     world.addSim(sim2);
    //     world.addRumah(sim2, new Point(0, 0));

    //     // print daftar sim
    //     for (Sim s : world.getDaftarSim()) {
    //         System.out.println(s.getNama());
    //     }
    // }
}
