package src.World;

import java.util.ArrayList;
import java.util.List;

import src.Rumah.Rumah;

public class World {
    private Rumah[][] gridRumah;
    private List<Rumah> daftarRumah;
    // waktu?

    public World() {
        this.gridRumah = new Rumah[64][64];
        this.daftarRumah = new ArrayList<Rumah>();
    }

    public List<Rumah> getDaftarRumah() {
        return daftarRumah;
    }

    public void addRumah() {
        // Temukan titik yang masih kosong pada gridRumah
        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (gridRumah[i][j] == null) {
                    Point temp = new Point(i, j);
                    Rumah rumahBaru = new Rumah(temp);
                    this.daftarRumah.add(rumahBaru);
                    gridRumah[i][j] = rumahBaru;
                    return;
                }
            }
        }
    }

    public void addRumah(Point location) {
        if (isCoordinateAvailable(location)) {
            Rumah rumahBaru = new Rumah(location);
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
            if (rumah.getLocation().equals(location)) {
                return false;
            }
        }
        return true;
    }

}
