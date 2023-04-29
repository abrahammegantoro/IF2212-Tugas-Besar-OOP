package src.World;

import java.util.ArrayList;
import java.util.List;

import src.Rumah.Rumah;
import src.Sim.Sim;

public class World {
    private Rumah[][] gridRumah;
    private List<Rumah> daftarRumah;
    private List<Sim> daftarSim;
    // waktu?

    public World() {
        this.gridRumah = new Rumah[64][64];
        this.daftarRumah = new ArrayList<Rumah>();
        this.daftarSim = new ArrayList<Sim>();
    }

    public void addSim(Sim sim) {
        this.daftarSim.add(sim);
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
        } else {
            System.out.println("Koordinat sudah terisi oleh rumah lain.");
        }
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


    // driver world
    public static void main(String[] args) {
        World world = new World();
        Point point = new Point(0, 0);
        Sim sim = new Sim("Sim1");
        world.addSim(sim);
        world.addRumah(sim, new Point(0, 0));

        Sim sim2 = new Sim("Sim2");
        world.addSim(sim2);
        world.addRumah(sim2, new Point(0, 0));
    }
}
