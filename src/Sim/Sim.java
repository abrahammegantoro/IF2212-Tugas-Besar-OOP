package src.Sim;

import src.Pekerjaan.Pekerjaan;
import src.Inventory.Inventory;
import src.Item.Edible;
import src.Item.Item;
import src.World.Point;

import java.util.Random;

public class Sim {
    private String nama;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Item> inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private Point posisiSim;
    private String namaRuanganSaatIni;
    private String namaRumahSaatIni;
    private String status;
    public Sim(String nama) {
        Random random = new Random();

        this.nama = nama;
        this.pekerjaan = new Pekerjaan(random.nextInt(1, 6)); // random.nextInt(1,5) ngerandom angka integer dari 1 sampai 5
        this.uang = 100;
        this.inventory = new Inventory<>();
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.posisiSim = new Point(0, 0);
        this.namaRuanganSaatIni = "Ruang Tamu";
        this.namaRumahSaatIni = "Rumah" + this.nama;
        this.status = "None";
    }

    public String getNama() {
        return nama;
    }

    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }

    public int getUang() {
        return uang;
    }

    public int getKekenyangan() {
        return kekenyangan;
    }

    public int getMood() {
        return mood;
    }

    public int getKesehatan() {
        return kesehatan;
    }

    public Point getPosisiSim() {
        return posisiSim;
    }

    public String getNamaRuanganSaatIni() {
        return namaRuanganSaatIni;
    }

    public String getNamaRumahSaatIni() {
        return namaRumahSaatIni;
    }

    public String getStatus() {
        return status;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public void setUang(int uang) {
        this.uang = uang;
    }

    public void setKekenyangan(int kekenyangan) {
        if (kekenyangan > 100)
            kekenyangan = 100;
        else if (kekenyangan <= 0)
            kekenyangan = 0;

        this.kekenyangan = kekenyangan;
        if (kekenyangan == 0) {
            // Sim mati karena kelaparan
            System.out.println("Sim mati karena kelaparan");
            System.out.println("Game Over.");
            System.exit(0);
        }
    }

    public void setMood(int mood) {
        if (mood > 100)
            mood = 100;
        else if (mood <= 0)
            mood = 0;

        this.mood = mood;
        if (mood == 0) {
            // Sim mati karena depresi
            System.out.println("Sim mati karena depresi");
            System.out.println("Game Over.");
            System.exit(0);
        }
    }

    public void setKesehatan(int kesehatan) {
        if (kesehatan > 100)
            kesehatan = 100;
        else if (kesehatan <= 0)
            kesehatan = 0;

        this.kesehatan = kesehatan;
        if (kesehatan == 0) {
            // Sim mati karena sakit
            System.out.println("Sim mati karena sakit");
            System.out.println("Game Over.");
            System.exit(0);
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Bagian Danang
    public void kerja(int lamaKerja) {
        /* Bagian Danang */
    }

    // Bagian Abam
    public void olahraga(int lamaOlahraga) {
        if (lamaOlahraga % 20 != 0) {
            System.out.println("Durasi olahraga harus kelipatan 20");
            return;
        }

        kesehatan += (lamaOlahraga / 20) * 5;
        setKesehatan(kesehatan);
        mood += (lamaOlahraga / 20) * 10;
        setMood(mood);
        kekenyangan -= (lamaOlahraga / 20) * 5;
        setKekenyangan(kekenyangan);
    }

    // Bagian Danang
    public void berkunjung(String namaRumah, int lamaBerkunjung) {
        /* Bagian Danang */
    }

    // Bagian Shulhan
    public void beliBarang(Item item) {
        /* Bagian Shulhan */
    }

    public void pindahRuangan(String namaRuangan) {
        this.namaRuanganSaatIni = namaRuangan;
    }

    public void pindahRumah(String namaRumah) {
        this.namaRumahSaatIni = namaRumah;
    }

    // Bagian Shulhan
    public void makan(Edible makanan) {
        /* Bagian Shulhan */
    }

    public void ibadah(int lamaIbadah) {
    // (efek: +3 mood/20 detik, -3 kekenyangan/20 detik)
        if (lamaIbadah % 20 != 0) {
            System.out.println("Durasi ibadah harus kelipatan 15");
            return;
        }

        mood += (lamaIbadah / 20) * 3;
        setMood(mood);
        kekenyangan -= (lamaIbadah / 20) * 3;
        setKekenyangan(kekenyangan);
    }

    public void menulisArtikel(int lamaMenulisArtikel) {
        // (efek: +5 mood/30 detik, -2 kesehatan/30 detik, -3 kekenyangan/30 detik)
        if (lamaMenulisArtikel % 30 != 0) {
            System.out.println("Durasi menulis artikel harus kelipatan 30");
            return;
        }

        mood += (lamaMenulisArtikel / 30) * 5;
        setMood(mood);
        kesehatan -= (lamaMenulisArtikel / 30) * 2;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaMenulisArtikel / 30) * 3;
        setKekenyangan(kekenyangan); 
    }

    public void bacaBuku(int lamaBaca) {
        // (efek : +6 mood / 30 detik, -1 kesehatan/30 detik, -3 kekenyangan/30 detik)
        if (lamaBaca % 30 != 0) {
            System.out.println("Durasi baca buku harus kelipatan 30");
            return;
        }

        mood += (lamaBaca / 30) * 6;
        setMood(mood);
        kesehatan -= (lamaBaca / 30) * 1;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaBaca / 30) * 3;
        setKekenyangan(kekenyangan);
    }

    public void bukaSimtagram(int lamaBuka) {
        // (efek : +7 mood / 30 detik, -2 kesehatan/30 detik, -3 kekenyangan/30 detik)
        if (lamaBuka % 30 != 0) {
            System.out.println("Durasi buka simtagram harus kelipatan 30");
            return;
        }

        mood += (lamaBuka / 30) * 7;
        setMood(mood);
        kesehatan -= (lamaBuka / 30) * 2;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaBuka / 30) * 3;
        setKekenyangan(kekenyangan);
    }

    public void bernyanyi(int lamaBernyanyi) {
        // (efek : +5 mood / 30 detik, -2 kesehatan/30 detik, -5 kekenyangan/30 detik)
        if (lamaBernyanyi % 30 != 0) {
            System.out.println("Durasi bernyanyi harus kelipatan 30");
            return;
        }

        mood += (lamaBernyanyi / 30) * 5;
        setMood(mood);
        kesehatan -= (lamaBernyanyi / 30) * 2;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaBernyanyi / 30) * 5;
        setKekenyangan(kekenyangan);
    }

    public void nontonSimflix(int lamaMenonton) {
        // (efek : +11 mood/30 detik, -2 kesehatan/30 detik, -4 kekenyangan/30 detik)
        if (lamaMenonton % 30 != 0) {
            System.out.println("Durasi nonton simflix harus kelipatan 30");
            return;
        }   

        mood += (lamaMenonton / 30) * 11;
        setMood(mood);
        kesehatan -= (lamaMenonton / 30) * 2;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaMenonton / 30) * 4;
        setKekenyangan(kekenyangan);
    }

    public void belajar(int lamaBelajar) {
        // (efek : -7 mood / 30 detik, -2 kesehatan/30 detik, -3 kekenyangan/30 detik)
        if (lamaBelajar % 30 != 0) {
            System.out.println("Durasi belajar harus kelipatan 30");
            return;
        }

        mood -= (lamaBelajar / 30) * 7;
        setMood(mood);
        kesehatan -= (lamaBelajar / 30) * 2;
        setKesehatan(kesehatan);
        kekenyangan -= (lamaBelajar / 30) * 3;
        setKekenyangan(kekenyangan);
    }
}
