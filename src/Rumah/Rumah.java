package src.Rumah;

import java.util.*;
import src.Ruangan.Ruangan;
import src.Sim.Sim;
import src.World.Point;
import src.World.Time;

public class Rumah {
    private String namaRumah;
    private Point lokasi;
    private List<Ruangan> daftarRuangan;

    public Rumah(Sim sim, Point lokasi) {
        this.namaRumah = sim.getNama();
        this.lokasi = lokasi;
        daftarRuangan = new ArrayList<Ruangan>();
        daftarRuangan.add(new Ruangan("Ruang Tamu"));
    }

    public String namaRumah() {
        return namaRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    private void printDaftarRuangan() {
        System.out.println("Daftar ruangan yang tersedia :");
        System.out.println("-------------------------------");
        for (int i = 0; i < daftarRuangan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarRuangan.get(i).getNamaRuangan());
        }
        System.out.println("\n");
    }

    private boolean isNamaRuanganAvailable(String namaRuangan) {
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(namaRuangan)) {
                return false;
            }
        }
        return true;
    }

    public void upgradeRumah(Sim sim) {
        if (sim.getUang() < 1500) {
            System.out.println("Uangmu tidak cukup, kerjalah lebih keras agar bisa upgrade rumah");
            return;
        }

        printDaftarRuangan();
        // minta pilih ruangan yang ingin diupgrade
        Scanner scanner = new Scanner(System.in);
        System.out.println("Masukkan nama ruangan yang ingin diupgrade (input String): ");
        String ruanganUpgrade = scanner.nextLine();

        // iterate daftarRuangan untuk cari ruangan yang ingin ditambah ruangan di sisi
        // atas, bawah, kiri, atau, kanannya
        for (Ruangan ruangan : daftarRuangan) {
            if (!ruangan.getNamaRuangan().equals(ruanganUpgrade)) {
                System.out.println("Ruangan tidak ditemukan");
                return;
            }
        }

        Ruangan ruanganToUpgrade = null;
        // set ruanganToUpgrade dengan ruangan yang ingin diupgrade
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(ruanganUpgrade)) {
                ruanganToUpgrade = ruangan;
                break;
            }
        }

        // Check if the ruanganToUpgrade is null before proceeding
        if (ruanganToUpgrade == null) {
            System.out.println("Ruangan tidak ditemukan");
            scanner.close();
            return;
        }

        // minta arah dari user
        System.out.println("Masukkan arah yang ingin ditambahkan ruangan (up, down, left, right): ");
        String arah = scanner.nextLine();

        while (!arah.equals("up") && !arah.equals("down") && !arah.equals("left") && !arah.equals("right")) {
            System.out.println("\nArah tidak valid, silakan masukkan arah yang valid.");
            System.out.println("Masukkan arah yang ingin ditambahkan ruangan (up, down, left, right): ");
            arah = scanner.nextLine();
        }

        final String arahFinal = arah;

        // cek apakah sudah ada ruangan di arah yang diminta
        if (arah.equals("up") && ruanganToUpgrade.getUp() != null) {
            System.out.println("Tidak bisa menambah ruangan karena sudah ada ruangan di atasnya");
            return;
        } else if (arah.equals("down") && ruanganToUpgrade.getDown() != null) {
            System.out.println("Tidak bisa menambah ruangan karena sudah ada ruangan di bawahnya");
            return;
        } else if (arah.equals("left") && ruanganToUpgrade.getLeft() != null) {
            System.out.println("Tidak bisa menambah ruangan karena sudah ada ruangan di kirinya");
            return;
        } else if (arah.equals("right") && ruanganToUpgrade.getRight() != null) {
            System.out.println("Tidak bisa menambah ruangan karena sudah ada ruangan di kanannya");
            return;
        }

        // minta nama ruangan baru
        System.out.println("Masukkan nama ruangan baru: ");
        String namaRuanganBaru = scanner.nextLine();

        // memastikan nama ruangan baru tidak sama dengan nama ruangan yang sudah ada,
        // jika sama, minta input ulang
        while (!isNamaRuanganAvailable(namaRuanganBaru)) {
            System.out.println("\nNama ruangan sudah ada, silakan masukkan nama ruangan yang lain");
            System.out.println("Masukkan nama ruangan baru: ");
            namaRuanganBaru = scanner.nextLine();
        }

        scanner.close();
        sim.setUang(sim.getUang() - 1500);
        // Thread upgradeThread = new Thread(new Runnable() {
        // public void run() {
        // try {
        // // Thread.sleep(18 * 60 * 1000); // wait for 18 minutes
        // Thread.sleep(3000); // wait for 3 second
        // addRuangan(ruangan, arahFinal, namaRuangan);
        // System.out.printf("Ruang %s berhasil ditambahkan ke rumah %s%n", namaRuangan,
        // namaRumah);
        // } catch (InterruptedException e) {
        // System.out.printf("Pembangunan ruang %s di rumah %s dibatalkan%n",
        // namaRuangan, namaRumah);
        // }
        // }
        // });

        // upgradeThread.start();
        System.out.println("Pembangunan ruang " + namaRuanganBaru + " di rumah " + sim.getNama() + " dimulai");
        Time.getInstance().setTimeMap("Rumah " + sim.getNama(), 1);

        if (Time.getInstance().getTimeMap().containsKey("Rumah " + sim.getNama())
                && Time.getInstance().getTimeMap().get("Rumah " + sim.getNama()) == 0) {
            addRuangan(ruanganToUpgrade, arahFinal, namaRuanganBaru);
            System.out.printf("%s berhasil ditambahkan ke rumah %s%n", namaRuanganBaru, namaRumah);
            Time.getInstance().getTimeMap().remove("Rumah " + sim.getNama());
        }
    }

    public void addRuangan(Ruangan ruangan, String arah, String namaRuangan) {
        Ruangan newRuangan = new Ruangan(namaRuangan);
        if (arah.equals("up")) {
            ruangan.setUp(newRuangan);
            newRuangan.setDown(ruangan);
        } else if (arah.equals("down")) {
            ruangan.setDown(newRuangan);
            newRuangan.setUp(ruangan);
        } else if (arah.equals("left")) {
            ruangan.setLeft(newRuangan);
            newRuangan.setRight(ruangan);
        } else if (arah.equals("right")) {
            ruangan.setRight(newRuangan);
            newRuangan.setLeft(ruangan);
        }
        daftarRuangan.add(newRuangan);
    }

    public static void main(String[] args) {
        Sim sim = new Sim("Budi");
        sim.setUang(999999);
        Rumah rumah = new Rumah(sim, new Point(0, 0));
        rumah.printDaftarRuangan();
        System.out.println("\n");
        rumah.upgradeRumah(sim);
        System.out.println("\n");
        rumah.printDaftarRuangan();
    }
}
