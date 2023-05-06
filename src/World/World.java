package src.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import src.MainMenu.MainMenu;
import src.Rumah.Rumah;
import src.Sim.Sim;

// Singleton design pattern
public class World {
    private static World instance = new World();
    private Rumah[][] gridRumah;
    private List<Rumah> daftarRumah;

    private World() {
        this.gridRumah = new Rumah[64][64];
        this.daftarRumah = new ArrayList<Rumah>();
        // this.daftarSim = new ArrayList<Sim>();
    }

    public static World getInstance() {
        return instance;
    }

    // Method yang digunakan untuk set world ketika load file
    public static void setInstance(World world) {
        instance = world;
    }

    // public void addSim(Sim sim) {
    // this.daftarSim.add(sim);
    // }

    // public List<Sim> getDaftarSim() {
    // return daftarSim;
    // }

    public void printWorld() {
        System.out.println("Peta Dunia :");
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < 64; i++) {
            System.out.print("|");
            for (int j = 0; j < 64; j++) {
                if (gridRumah[63 - i][j] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print("R ");
                }
            }
            System.out.println("|");
        }
        System.out.println(
                "----------------------------------------------------------------------------------------------------------------------------------");
    }

    public List<Rumah> getDaftarRumah() {
        return daftarRumah;
    }

    public void addRumah(Sim sim) {
        // Temukan titik yang masih kosong pada gridRumah
        Scanner scanner = new Scanner(System.in);
        List<Point> availablePoints = findAvailablePoints();
        boolean addRumah = true;
        if (availablePoints.isEmpty()) {
            addRumah = false;
        }

        if (addRumah) {// Pilih titik acak dari daftar titik yang tersedia
            Random random = new Random();
            Point randomPoint = availablePoints.get(random.nextInt(availablePoints.size()));

            Rumah rumahBaru = new Rumah(sim, randomPoint);
            this.daftarRumah.add(rumahBaru);
            gridRumah[(int) randomPoint.getX()][(int) randomPoint.getY()] = rumahBaru;
            MainMenu.addSim(sim);
            MainMenu.setCurrentSim(sim);
            sim.setRumahUtama(rumahBaru);
            sim.setRumahSaatIni(rumahBaru);
            sim.setRuanganSaatIni(rumahBaru.getRuangan("Ruang Tamu"));
            sim.setPosisiSim(new Point(0, 0));
            sim.getRuanganSaatIni().putSim(sim, new Point(0, 0));
            System.out.println("Rumah sim berhasil dibuat di titik random, yaitu di ("
                    + sim.getRumahUtama().getLokasi().getX() + ", "
                    + sim.getRumahUtama().getLokasi().getY() + ")");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearTerminal();
            MainMenu.showInGameMenu();
        } else {
            System.out.println("Tidak ada titik yang tersedia untuk menambahkan rumah.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearTerminal();
            MainMenu.showInGameMenu();
        }
    }

    public void removeRumah(Rumah rumah) {
        daftarRumah.remove(rumah);
        gridRumah[(int) rumah.getLokasi().getX()][(int) rumah.getLokasi().getY()] = null;
    }

    private List<Point> findAvailablePoints() {
        List<Point> availablePoints = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            for (int j = 0; j < 64; j++) {
                if (gridRumah[i][j] == null) {
                    availablePoints.add(new Point(i, j));
                }
            }
        }

        return availablePoints;
    }

    public void addRumah(Sim sim, Point location) {
        Scanner scanner = new Scanner(System.in);
        if (isCoordinateAvailable(location)) {
            Rumah rumahBaru = new Rumah(sim, location);
            this.daftarRumah.add(rumahBaru);
            gridRumah[(int) location.getX()][(int) location.getY()] = rumahBaru;
            MainMenu.addSim(sim);
            MainMenu.setCurrentSim(sim);
            sim.setRumahUtama(rumahBaru);
            sim.setRumahSaatIni(rumahBaru);
            sim.setRuanganSaatIni(rumahBaru.getRuangan("Ruang Tamu"));
            sim.setPosisiSim(new Point(0, 0));
            sim.getRuanganSaatIni().putSim(sim, new Point(0, 0));
            System.out.println("Rumah " + sim.getNama() + " berhasil dibuat.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearTerminal();
            MainMenu.showInGameMenu();
        } else {
            System.out.println("Koordinat sudah terisi oleh rumah lain.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearTerminal();
            MainMenu.showInGameMenu();
        }
    }

    public void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            e.printStackTrace();
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
}
