package src.Rumah;

import java.util.*;
import src.Ruangan.Ruangan;
import src.Sim.Sim;
import src.World.Point;
// import src.World.Time;
import src.World.Time;

public class Rumah {
    private String namaRumah;
    private Point lokasi;
    private Ruangan ruanganToUpgrade;
    private String arahFinal;
    private String namaRuanganBaru;
    private List<Ruangan> daftarRuangan;
    private Map<Point, Ruangan> mapRuangan;

    public Rumah(Sim sim, Point lokasi) {
        this.namaRumah = "Rumah " + sim.getNama();
        this.lokasi = lokasi;
        daftarRuangan = new ArrayList<Ruangan>();
        Ruangan ruangTamu = new Ruangan("Ruang Tamu");
        daftarRuangan.add(ruangTamu);
        mapRuangan = new HashMap<Point, Ruangan>();
        mapRuangan.put(new Point(0, 0), ruangTamu);
    }

    public String getNamaRumah() {
        return namaRumah;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public List<Ruangan> getDaftarRuangan() {
        return daftarRuangan;
    }

    public Point getLokasiRuangan(Ruangan ruangan) {
        for (Map.Entry<Point, Ruangan> entry : mapRuangan.entrySet()) {
            if (entry.getValue().equals(ruangan)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public Ruangan getRuangan(String namaRuangan) { // With name
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(namaRuangan)) {
                return ruangan;
            }
        }
        return null;
    }

    public Ruangan getRuanganWithPoint(Point point) {
        for (Map.Entry<Point, Ruangan> entry : mapRuangan.entrySet()) {
            if (entry.getKey().getX() == point.getX() && entry.getKey().getY() == point.getY()) {
                return entry.getValue();
            }
        }
        return null;
    }

    // Ngeprint daftar ruangan
    public void printDaftarRuangan() {
        System.out.println("Daftar ruangan yang tersedia :");
        System.out.println("-------------------------------");
        for (int i = 0; i < daftarRuangan.size(); i++) {
            System.out.println((i + 1) + ". " + daftarRuangan.get(i).getNamaRuangan());
        }
        System.out.println("0. Back");
        System.out.println("\n");
    }

    // Ngeprint daftar ruangan kecuali ruangan saat ini
    public void printDaftarRuanganExceptSim(String namaRuanganSaatIni) {
        System.out.println("Daftar ruangan yang tersedia :");
        System.out.println("-------------------------------");
        int counter = 1;
        for (int i = 0; i < daftarRuangan.size(); i++) {
            if (!daftarRuangan.get(i).getNamaRuangan().equals(namaRuanganSaatIni)) {
                System.out.println(counter + ". " + daftarRuangan.get(i).getNamaRuangan());
                counter++;
            }
        }
        System.out.println("0. Back");
        System.out.println("\n");
    }

    public boolean isNamaRuanganAvailable(String namaRuangan) {
        for (Ruangan ruangan : daftarRuangan) {
            if (ruangan.getNamaRuangan().equals(namaRuangan)) {
                return true;
            }
        }
        return false;
    }

    public Ruangan getRuanganToUpgrade() {
        return ruanganToUpgrade;
    }

    public String getArahFinal() {
        return arahFinal;
    }

    public String getNamaRuanganBaru() {
        return namaRuanganBaru;
    }

    public void setRuanganToUpgrade(Ruangan ruangan) {
        this.ruanganToUpgrade = ruangan;
    }

    public void setArahFinal(String arah) {
        this.arahFinal = arah;
    }

    public void setNamaRuanganBaru(String ruangan) {
        this.namaRuanganBaru = ruangan;
    }

    public void upgradeRumah(Sim sim) {
        if (sim.getUang() < 1500) {
            System.out.println("Uangmu tidak cukup, kerjalah lebih keras agar bisa upgrade rumah");
            return;
        }

        printDaftarRuangan();
        // minta pilih ruangan yang ingin diupgrade
        Scanner scanner = new Scanner(System.in);
        System.out.print(
                "Masukkan nama ruangan yang ingin ditambahi ruangan di sisi atas, bawah, kiri, atau kanannya (input String): ");
        String ruanganUpgrade = scanner.nextLine();

        if (ruanganUpgrade.equals("Back")) {
            return;
        }

        // cek apakah ruanganUpgrade ada di daftarRuangan dan terus minta input
        while (!isNamaRuanganAvailable(ruanganUpgrade)) {
            System.out.println("\nRuangan tidak ditemukan, silakan masukkan nama ruangan yang valid.");
            System.out.print(
                    "Masukkan nama ruangan yang ingin ditambahi ruangan di sisi atas, bawah, kiri, atau kanannya (input String): ");
            ruanganUpgrade = scanner.nextLine();
            if (ruanganUpgrade.equals("Back")) {
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
            return;
        }

        // minta arah dari user
        System.out.print("Masukkan arah yang ingin ditambahkan ruangan (up, down, left, right): ");
        String arah = scanner.nextLine();

        while (!arah.equals("up") && !arah.equals("down") && !arah.equals("left") && !arah.equals("right")) {
            System.out.println("\nArah tidak valid, silakan masukkan arah yang valid.");
            System.out.print("Masukkan arah yang ingin ditambahkan ruangan (up, down, left, right): ");
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
        System.out.print("Masukkan nama ruangan baru: ");
        String namaRuanganBaru = scanner.nextLine();

        // memastikan nama ruangan baru tidak sama dengan nama ruangan yang sudah ada,
        // jika sama, minta input ulang
        while (isNamaRuanganAvailable(namaRuanganBaru)) {
            System.out.println("\nNama ruangan sudah ada, silakan masukkan nama ruangan yang lain");
            System.out.print("Masukkan nama ruangan baru: ");
            namaRuanganBaru = scanner.nextLine();
        }
        System.out.println("Pembangunan ruang " + namaRuanganBaru + " di rumah " + sim.getNama() + " dimulai");
        sim.setUang(sim.getUang() - 1500);
        Time.getInstance().setTimeMapUpgradeRumah(sim.getRumahUtama(), 18*60); // 18*60
        setRuanganToUpgrade(ruanganToUpgrade);
        setArahFinal(arahFinal);
        setNamaRuanganBaru(namaRuanganBaru);
    }

    public void addRuangan(Ruangan ruangan, Point lokasi) {
        daftarRuangan.add(ruangan);
        mapRuangan.put(lokasi, ruangan);
    }

    public boolean isThereRuanganUp(Ruangan ruangan) {
        if (getRuanganWithPoint(
                new Point(getLokasiRuangan(ruangan).getX(), getLokasiRuangan(ruangan).getY() + 1)) != null) {
            return true;
        }
        return false;
    }

    public boolean isThereRuanganDown(Ruangan ruangan) {
        if (getRuanganWithPoint(
                new Point(getLokasiRuangan(ruangan).getX(), getLokasiRuangan(ruangan).getY() - 1)) != null) {
            return true;
        }
        return false;
    }

    public boolean isThereRuanganLeft(Ruangan ruangan) {
        if (getRuanganWithPoint(
                new Point(getLokasiRuangan(ruangan).getX() - 1, getLokasiRuangan(ruangan).getY())) != null) {
            return true;
        }
        return false;
    }

    public boolean isThereRuanganRight(Ruangan ruangan) {
        if (getRuanganWithPoint(
                new Point(getLokasiRuangan(ruangan).getX() + 1, getLokasiRuangan(ruangan).getY())) != null) {
            return true;
        }
        return false;
    }

    public void addRuangan(Ruangan ruangan, String arah, String namaRuangan) {
        Ruangan newRuangan = new Ruangan(namaRuangan);
        if (arah.equals("up")) {
            mapRuangan.put(new Point(getLokasiRuangan(ruangan).getX(), getLokasiRuangan(ruangan).getY() + 1),
                    newRuangan);
            ruangan.setUp(newRuangan);
            newRuangan.setDown(ruangan);

            // cek sekeliling newRuangan
            if (isThereRuanganLeft(newRuangan)) {
                newRuangan.setLeft(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY()))
                        .setRight(newRuangan);
            }

            if (isThereRuanganRight(newRuangan)) {
                newRuangan.setRight(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY()))
                        .setLeft(newRuangan);
            }

            if (isThereRuanganUp(newRuangan)) {
                newRuangan.setUp(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1))
                        .setDown(newRuangan);
            }
        } else if (arah.equals("down")) {
            mapRuangan.put(new Point(getLokasiRuangan(ruangan).getX(), getLokasiRuangan(ruangan).getY() - 1),
                    newRuangan);
            ruangan.setDown(newRuangan);
            newRuangan.setUp(ruangan);

            // cek sekeliling newRuangan
            if (isThereRuanganLeft(newRuangan)) {
                newRuangan.setLeft(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY()))
                        .setRight(newRuangan);
            }

            if (isThereRuanganRight(newRuangan)) {
                newRuangan.setRight(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY()))
                        .setLeft(newRuangan);
            }

            if (isThereRuanganDown(newRuangan)) {
                newRuangan.setDown(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1))
                        .setUp(newRuangan);
            }
        } else if (arah.equals("left")) {
            mapRuangan.put(new Point(getLokasiRuangan(ruangan).getX() - 1, getLokasiRuangan(ruangan).getY()),
                    newRuangan);
            ruangan.setLeft(newRuangan);
            newRuangan.setRight(ruangan);

            // cek sekeliling newRuangan
            if (isThereRuanganLeft(newRuangan)) {
                newRuangan.setLeft(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() - 1, getLokasiRuangan(newRuangan).getY()))
                        .setRight(newRuangan);
            }

            if (isThereRuanganUp(newRuangan)) {
                newRuangan.setUp(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1))
                        .setDown(newRuangan);
            }

            if (isThereRuanganDown(newRuangan)) {
                newRuangan.setDown(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1))
                        .setUp(newRuangan);
            }
        } else if (arah.equals("right")) {
            mapRuangan.put(new Point(getLokasiRuangan(ruangan).getX() + 1, getLokasiRuangan(ruangan).getY()),
                    newRuangan);
            ruangan.setRight(newRuangan);
            newRuangan.setLeft(ruangan);

            // cek sekeliling newRuangan
            if (isThereRuanganRight(newRuangan)) {
                newRuangan.setRight(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY())));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX() + 1, getLokasiRuangan(newRuangan).getY()))
                        .setLeft(newRuangan);
            }

            if (isThereRuanganUp(newRuangan)) {
                newRuangan.setUp(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() + 1))
                        .setDown(newRuangan);
            }

            if (isThereRuanganDown(newRuangan)) {
                newRuangan.setDown(getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1)));
                getRuanganWithPoint(
                        new Point(getLokasiRuangan(newRuangan).getX(), getLokasiRuangan(newRuangan).getY() - 1))
                        .setUp(newRuangan);
            }
        }
        daftarRuangan.add(newRuangan);
        // print daftar ruangan
        // System.out.println("Daftar ruangan:");
        // for (Ruangan ruangan1 : daftarRuangan) {
        // System.out.println(ruangan1.getNamaRuangan());
        // }
    }

    // Pindahkan semua sim-sim yang ada di seluruh ruangan ke rumahUtama mereka masing-masing
    // public void pindahkanSemuaSim() {
    //     List<Ruangan> daftarRuanganz = getDaftarRuangan();
    //     for (Ruangan ruangan : daftarRuanganz) {
    //         Map<Sim, Point> daftarSim = ruangan.getDaftarSim();
    //         Iterator<Sim> iterator = daftarSim.keySet().iterator();
    //         while (iterator.hasNext()) {
    //             Sim sim = iterator.next();
    //             sim.getRuanganSaatIni().removeSim(sim);
    //             sim.setRumahSaatIni(sim.getRumahUtama());
    //             sim.setRuanganSaatIni(sim.getRumahUtama().getRuangan("Ruang Tamu"));
    //             sim.getRumahUtama().getRuangan("Ruang Tamu").putSim(sim, new Point(0, 0));
    //             iterator.remove();
    //         }
    //     }
    // }

    public void pindahkanSemuaSim() {
        for (Ruangan ruangan : daftarRuangan) {
            Map<Sim, Point> daftarSim = new HashMap<>(ruangan.getDaftarSim());
            for (Sim sim : daftarSim.keySet()) {
                sim.getRuanganSaatIni().removeSim(sim);
                sim.setRumahSaatIni(sim.getRumahUtama());
                sim.setRuanganSaatIni(sim.getRumahUtama().getRuangan("Ruang Tamu"));
                sim.getRumahUtama().getRuangan("Ruang Tamu").putSim(sim, new Point(0, 0));
            }
        }
    }

    public static void main(String[] args) {
        // Sim sim = new Sim("Budi");
        // sim.setUang(999999);
        // Rumah rumah = new Rumah(sim, new Point(0, 0));

        // rumah.printDaftarRuangan();
        // System.out.println("\n");
        // rumah.upgradeRumah(sim);
        // System.out.println("\n");
        // rumah.printDaftarRuangan();
        // System.out.println("\n");
        // rumah.upgradeRumah(sim);
        // System.out.println("\n");
        // rumah.printDaftarRuangan();
        Point point = new Point(0, 0);
        Sim sim1 = new Sim("Budi");
        sim1.setUang(99999999);
        Rumah rumah = new Rumah(sim1, new Point(0, 0));
        rumah.upgradeRumah(sim1);
        sim1.kerja();
        // print daftar ruangan
        rumah.printDaftarRuangan();
        rumah.upgradeRumah(sim1);
        sim1.kerja();
        // print daftar ruangan
        rumah.printDaftarRuangan();
        rumah.upgradeRumah(sim1);
        sim1.kerja();
        // print daftar ruangan
        rumah.printDaftarRuangan();
        rumah.upgradeRumah(sim1);
        sim1.kerja();
        // print daftar ruangan
        rumah.printDaftarRuangan();
        rumah.upgradeRumah(sim1);
        sim1.kerja();
        // print daftar ruangan
        rumah.printDaftarRuangan();

        // get up, down, left, right ruangan M
        try {
            System.out.println(rumah.getRuangan("M").getUp().getNamaRuangan());
        } catch (Exception e) {
            System.out.println("Tidak ada ruangan di atas M");
        }
        System.out.println(rumah.getRuangan("M").getDown().getNamaRuangan());
        System.out.println(rumah.getRuangan("M").getLeft().getNamaRuangan());
        System.out.println(rumah.getRuangan("M").getRight().getNamaRuangan());

        // rumah.addRuangan(rumah.getRuangan("Ruang Tamu"), "down", "K");
        // rumah.addRuangan(rumah.getRuangan("K"), "right", "G");
        // rumah.addRuangan(rumah.getRuangan("G"), "right", "J");
        // rumah.addRuangan(rumah.getRuangan("J"), "up", "B");
        // rumah.addRuangan(rumah.getRuangan("B"), "left", "M");
        // addRuangan(Ruangan ruangan, Point point)

        // // addRuangan(Ruangan ruangan, String arah, String namaRuangan)
        // rumah.addRuangan(rumah.getRuangan("Ruang Tamu"), "down", "K");
        // rumah.addRuangan(rumah.getRuangan("K"), "right", "G");
        // rumah.addRuangan(rumah.getRuangan("G"), "right", "J");
        // rumah.addRuangan(rumah.getRuangan("J"), "up", "B");
        // rumah.addRuangan(rumah.getRuangan("B"), "left", "M");

        // // get up, down, left, right ruangan M
        // try {
        // System.out.println(rumah.getRuangan("M").getUp().getNamaRuangan());
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // }
        // System.out.println(rumah.getRuangan("M").getDown().getNamaRuangan());
        // System.out.println(rumah.getRuangan("M").getLeft().getNamaRuangan());
        // System.out.println(rumah.getRuangan("M").getRight().getNamaRuangan());

        // //print daftar ruangan
        // rumah.printDaftarRuangan();
    }
}
