package src.Sim;

import src.Pekerjaan.Pekerjaan;
import src.Ruangan.Ruangan;
import src.Rumah.Rumah;
import src.Inventory.Inventory;
import src.World.Time;
import src.World.World;
// import src.Item.Edible;
import src.Item.Item;
import src.Item.BahanBaku.BahanBaku;
import src.Item.Furniture.*;
import src.Item.Furniture.Bed.KingBed;
import src.Item.Furniture.Bed.QueenBed;
import src.Item.Furniture.Bed.SingleBed;
import src.Item.Furniture.Stove.EStove;
import src.Item.Furniture.Stove.GasStove;
import src.MainMenu.MainMenu;
import src.World.Point;
import src.Item.Buyable;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Sim {
    private String nama;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Item> inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private Point posisiSim;
    private Rumah rumahUtama;
    private Rumah rumahSaatIni;
    private Ruangan ruanganSaatIni;
    private String status;
    private int waktuGantiPekerjaan = -1; // atribut ini digunakan untuk menentukan kapan bisa melakukan
                                                // penggantian pekerjaan
    private Pekerjaan pekerjaanBaru = null;
    private boolean isGantiPekerjaan = false; // atribut ini digunakan untuk validasi apakah bisa melakukan penggantian
                                              // pekerjaan

    public Sim(String nama) {
        Random random = new Random();
        this.nama = nama;
        this.pekerjaan = new Pekerjaan(random.nextInt(1, 6));
        this.uang = 100; // GANTI JADI 100
        this.inventory = new Inventory<>();
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.posisiSim = new Point(0, 0);
        this.status = "None";

        // Bikin furniture kasur, toilet, kompor, kursi, meja, dan jam, lalu
        // menambahinya ke inventory
        SingleBed kasurSingle = new SingleBed();
        Toilet toilet = new Toilet();
        GasStove komporGas = new GasStove();
        MejaKursi mejaKursi = new MejaKursi();
        Clock jam = new Clock();

        inventory.addItem(kasurSingle, 1);
        inventory.addItem(toilet, 1);
        inventory.addItem(komporGas, 1);
        inventory.addItem(mejaKursi, 1);
        inventory.addItem(jam, 1);

        // Atribut rumahUtama, rumahSaatIni, dan ruanganSaatIni belum diinisialisasi
        // Akan diinisialisasi di World.java setelah melakukan addRumah(Sim sim, Point
        // location)
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

    public Inventory<Item> getInventory() {
        return inventory;
    }

    public void viewInventory() {
        inventory.showInventory();
    }

    public void addItemToInventory(Item item, int amount) {
        inventory.addItem(item, amount);
    }

    public boolean isItemInInventory(Item item) {
        return inventory.isItemExist(item);
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

    public String getStatus() {
        return status;
    }

    public Rumah getRumahUtama() {
        return rumahUtama;
    }

    public Rumah getRumahSaatIni() {
        return rumahSaatIni;
    }

    public Ruangan getRuanganSaatIni() {
        return ruanganSaatIni;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setPekerjaan(Pekerjaan pekerjaan) {
        // Pekerjaan pekerjaanDefault = this.pekerjaan;
        // int hariGanti = Time.getHari();
        // this.pekerjaan = pekerjaan;
        // this.uang -= pekerjaan.getGaji() / 2;
        // while (hariGanti = Time.getHari()) {
        // // do nothing
        // }
        // this.pekerjaan = pekerjaanDefault;

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
            System.out.println("Sim dengan nama " + this.nama + " mati karena kelaparan.");
            MainMenu.removeSimAndChangeSim(this);
            clearTerminal();
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
            System.out.println("Sim dengan nama " + this.nama + " mati karena depresi.");
            MainMenu.removeSimAndChangeSim(this);
            clearTerminal();
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
            System.out.println("Sim dengan nama " + this.nama + " mati karena sakit.");
            MainMenu.removeSimAndChangeSim(this);
            clearTerminal();
        }
    }

    public void setPosisiSim(Point posisiSim) {
        this.posisiSim = posisiSim;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRumahUtama(Rumah rumahUtama) {
        this.rumahUtama = rumahUtama;
    }

    public void setRumahSaatIni(Rumah rumahSaatIni) {
        this.rumahSaatIni = rumahSaatIni;
    }

    public void setRuanganSaatIni(Ruangan ruanganSaatIni) {
        this.ruanganSaatIni = ruanganSaatIni;
    }

    // Bagian Danang
    public void kerja() {
        Scanner input = new Scanner(System.in);

        System.out.print("Durasi Kerja :  ");
        int durasi = input.nextInt();

        while (durasi % 1 != 0 || durasi <= 0) {
            System.out.println("Durasi kerja harus kelipatan 120 dan juga lebih dari 0");
            System.out.print("Durasi Kerja :  ");
            durasi = input.nextInt();
        }
        final int durasiAkhir = durasi;
        setStatus("Kerja");
        System.out.println("Kerja dimulai selama " + durasi + " detik.");
        Thread kerjaThread = new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                while (counter < durasiAkhir) {
                    try {
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        decrementBeliBarangTime();
                        decrementUpgradeRumahTime();
                        setPekerjaanBaru();
                        counter++;
                        System.out.println(counter);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
                System.out.println("Sim telah selesai bekerja.");
                System.out.println("Tekan Enter untuk melanjutkan.");
                input.nextLine();
                input.nextLine();
                clearTerminal();
                setKekenyangan(0); // kekenyangan - ((durasiAkhir / 30) * 10
                setMood(mood - ((durasiAkhir / 30) * 10));
                uang += ((durasiAkhir / 240) * pekerjaan.getGaji());

                pekerjaan.addTimesWorked(durasiAkhir);
                setStatus("None");
            }
        });
        kerjaThread.start();

        try {
            kerjaThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Bagian Abam
    public void olahraga() {
        Scanner input = new Scanner(System.in);

        System.out.print("Durasi Olahraga :  ");
        int durasi = input.nextInt();

        while (durasi % 20 != 0 || durasi <= 0) {
            System.out.println("Durasi olahraga harus kelipatan 20 dan juga lebih dari 0");
            System.out.print("Durasi Olahraga :  ");
            durasi = input.nextInt();
        }

        setStatus("Olahraga");
        // Time.getInstance().consumeTime(durasi);

        final int durasiFinal = durasi;
        Thread olahragaThread = new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                while (counter < durasiFinal) {
                    try {
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        decrementBeliBarangTime();
                        decrementUpgradeRumahTime();
                        setPekerjaanBaru();
                        counter++;
                        System.out.println(counter);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
                setKesehatan(kesehatan + ((durasiFinal / 20) * 5));
                setMood(mood + ((durasiFinal / 20) * 10));
                setKekenyangan(kekenyangan - ((durasiFinal / 20) * 5));
                System.out.println("Sim telah selesai berolahraga");
                setStatus("None");
            }
        });

        olahragaThread.start();

        try {
            olahragaThread.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    public void berkunjung() {
        World world = World.getInstance();
        Scanner input = new Scanner(System.in);
        world.printDaftarRumahExceptSim(getRumahSaatIni().getNamaRumah());
        System.out.print("Pilihan (input String): ");
        String pilihan = input.nextLine();
        if (pilihan.equals("Exit")) {
            return;
        }

        while (getRumahSaatIni() == world.getRumah(pilihan)) {
            System.out.println("Anda sudah berada di rumah tersebut.");
            System.out.print("Silakan pilih ulang (input String): ");
            pilihan = input.nextLine();
            if (pilihan.equals("Exit")) {
                return;
            }
        }

        while (!world.isNamaRumahAvailable(pilihan)) {
            System.out.println("Rumah tidak ditemukan");
            System.out.print("Silakan pilih ulang (input String): ");
            pilihan = input.nextLine();
            if (pilihan.equals("Exit")) {
                return;
            }
        }

        final int lamaBerkunjung = getWaktuFromJarakRumah(rumahSaatIni, world.getRumah(pilihan));

        setStatus("Berkunjung");
        Thread berkunjungThread = new Thread(new Runnable() {
            public void run() {
                int counter = 0;
                System.out.println("Perjalanan dimulai!, waktu yang diperlukan adalah " + lamaBerkunjung + " detik.");
                while (counter < lamaBerkunjung) {
                    try {
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        decrementBeliBarangTime();
                        decrementUpgradeRumahTime();
                        setPekerjaanBaru();
                        counter++;
                        System.out.println(counter);
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
            }
        });

        berkunjungThread.start();

        try {
            berkunjungThread.join();
            setMood(mood + ((lamaBerkunjung / 30) * 10));
            setKekenyangan(kekenyangan - ((lamaBerkunjung / 30) * 10));
            setRumahSaatIni(world.getRumah(pilihan));
            setRuanganSaatIni(world.getRumah(pilihan).getRuangan("Ruang Tamu"));

            if (getRumahSaatIni().getNamaRumah() == getRumahUtama().getNamaRumah())
                System.out.println("Berhasil pindah rumah. Sekarang Anda berada di " + getRumahSaatIni().getNamaRumah()
                        + ", yaitu rumah Anda sendiri.");
            else
                System.out
                        .println("Berhasil pindah rumah. Sekarang Anda berada di " + getRumahSaatIni().getNamaRumah());
            setStatus("None");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    private int getWaktuFromJarakRumah(Rumah rumah1, Rumah rumah2) {
        double jarak = Math.sqrt(Math.pow(rumah1.getLokasi().getX() - rumah2.getLokasi().getX(), 2)
                + Math.pow(rumah1.getLokasi().getY() - rumah2.getLokasi().getY(), 2));

        return (int) Math.round(jarak); // 1 detik = 1 satuan jarak
    }

    public void pindahRuangan() {
        Scanner input = new Scanner(System.in);
        getRumahSaatIni().printDaftarRuanganExceptSim(getRuanganSaatIni().getNamaRuangan());
        System.out.print("Pilihan (input String): ");
        String pilihan = input.nextLine();
        if (pilihan.equals("Exit")) {
            return;
        }

        while (getRuanganSaatIni() == getRumahSaatIni().getRuangan(pilihan)) {
            System.out.println("Anda sudah berada di ruangan tersebut.");
            System.out.print("Silakan pilih ulang (input String): ");
            pilihan = input.nextLine();
            if (pilihan.equals("Exit")) {
                return;
            }
        }

        while (!getRumahSaatIni().isNamaRuanganAvailable(pilihan)) {
            System.out.println("Ruangan tidak ditemukan");
            System.out.print("Silakan pilih ulang (input String): ");
            pilihan = input.nextLine();
            if (pilihan.equals("Exit")) {
                return;
            }
        }
        // apus sim dari daftarSim untuk ruangan yang lama
        System.out.println("Ruangan lama : " + getRuanganSaatIni().getNamaRuangan());
        System.out.println("Berikut adalah daftar sim yang sedang berada di " + getRuanganSaatIni().getNamaRuangan());
        Map<Sim, Point> map = this.getRuanganSaatIni().getDaftarSim();
        // check if empty
        if (map.isEmpty()) {
            System.out.println("Tidak ada sim yang sedang berada di ruangan ini.");
        }
        for (Map.Entry<Sim, Point> entry : map.entrySet()) {
            System.out.println(entry.getKey().getNama() + " pada titik (" + entry.getValue().getX() + ", "
                    + entry.getValue().getY() + ")");
        }
        // print sim map
        System.out.println("Sim : " + this.getNama() + " pada titik (" + this.getPosisiSim().getX() + ", "
                + this.getPosisiSim().getY() + ")");
        getRuanganSaatIni().removeSim(this);

        // pindah ruangan
        setRuanganSaatIni(getRumahSaatIni().getRuangan(pilihan));

        // masukin sim ke daftarSim untuk ruangan yang baru
        System.out.println("Ruangan baru : " + getRuanganSaatIni().getNamaRuangan());
        System.out.println("Berikut adalah daftar sim yang sedang berada di " + getRuanganSaatIni().getNamaRuangan());
        Map<Sim, Point> map1 = this.getRuanganSaatIni().getDaftarSim();
        if (map.isEmpty()) {
            System.out.println("Tidak ada sim yang sedang berada di ruangan ini.");
        }
        for (Map.Entry<Sim, Point> entry : map1.entrySet()) {
            System.out.println(entry.getKey().getNama() + " pada titik (" + entry.getValue().getX() + ", "
                    + entry.getValue().getY() + ")");
        }
        System.out.println("Sim : " + this.getNama() + " pada titik (" + this.getPosisiSim().getX() + ", "
                + this.getPosisiSim().getY() + ")");
        getRuanganSaatIni().putSim(this, new Point(0, 0));
        this.setPosisiSim(new Point(0, 0));
        System.out.println("Berhasil pindah ruangan. Sekarang Anda berada di " + getRuanganSaatIni().getNamaRuangan());
    }

    public void store() {
        // Print the header row
        System.out.println("-----------------------------------");
        System.out.printf("| %-4s| %-18s| %-6s|%n", "No.", "Item", "Price");
        System.out.println("-----------------------------------");

        // Print the data row
        System.out.printf("| %-4d| %-18s| %-6d|%n", 1, "Kasur Single", 50);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 2, "Kasur Queen Size", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 3, "Kasur King Size", 150);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 4, "Toilet", 50);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 5, "Kompor Gas", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 6, "Kompor Listrik", 200);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 7, "Meja dan Kursi", 50);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 8, "Jam", 10);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 9, "TV", 150);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 10, "Komputer", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 11, "Sajadah", 10);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 12, "Piano", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 13, "Shower", 50);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 14, "Teleskop", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 15, "Rak Buku", 100);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 16, "Nasi", 5);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 17, "Kentang", 3);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 18, "Ayam", 10);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 19, "Sapi", 12);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 20, "Wortel", 3);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 21, "Bayam", 3);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 22, "Kacang", 2);
        System.out.printf("| %-4d| %-18s| %-6d|%n", 23, "Susu", 2);
        System.out.println("-----------------------------------");
    }

    public void beliBarang() {
        Item item = null;
        store();

        System.out.println("\nUang Anda : " + getUang());
        System.out.println("\nPilih nomor item yang ingin dibeli : ");
        Scanner input = new Scanner(System.in);
        int pilihan = input.nextInt();
        while (pilihan < 1 || pilihan > 23) {
            System.out.println("Pilihan tidak valid");
            System.out.println("Pilih nomor item yang ingin dibeli : ");
            pilihan = input.nextInt();
        }

        if (pilihan == 1) {
            item = new SingleBed();
        } else if (pilihan == 2) {
            item = new QueenBed();
        } else if (pilihan == 3) {
            item = new KingBed();
        } else if (pilihan == 4) {
            item = new Toilet();
        } else if (pilihan == 5) {
            item = new GasStove();
        } else if (pilihan == 6) {
            item = new EStove();
        } else if (pilihan == 7) {
            item = new MejaKursi();
        } else if (pilihan == 8) {
            item = new Clock();
        } else if (pilihan == 9) {
            item = new TV();
        } else if (pilihan == 10) {
            item = new Komputer();
        } else if (pilihan == 11) {
            item = new Sajadah();
        } else if (pilihan == 12) {
            item = new Piano();
        } else if (pilihan == 13) {
            item = new Shower();
        } else if (pilihan == 14) {
            item = new Teleskop();
        } else if (pilihan == 15) {
            item = new RakBuku();
        } else if (pilihan == 16) {
            item = new BahanBaku("Nasi", 5, 5);
        } else if (pilihan == 17) {
            item = new BahanBaku("Kentang", 3, 4);
        } else if (pilihan == 18) {
            item = new BahanBaku("Ayam", 10, 8);
        } else if (pilihan == 19) {
            item = new BahanBaku("Sapi", 12, 15);
        } else if (pilihan == 20) {
            item = new BahanBaku("Wortel", 3, 2);
        } else if (pilihan == 21) {
            item = new BahanBaku("Bayam", 3, 2);
        } else if (pilihan == 22) {
            item = new BahanBaku("Kacang", 2, 2);
        } else if (pilihan == 23) {
            item = new BahanBaku("Susu", 2, 1);
        }
        final Item itemFinal = item;
        Buyable barang = (Buyable) item;

        if (uang < barang.getHarga()) {
            System.out.println("Anda tidak memiliki uang yang cukup untuk membeli barang ini");
        } else {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    int deliveryTime = barang.getDeliveryTime();
                    Time.getInstance().setTimeMapBeliBarang(itemFinal, deliveryTime);
                    System.out.println("Barang akan sampai dalam " + deliveryTime + " hari");
                }
            });
            t.start();
        }
    }

    public synchronized void decrementBeliBarangTime() {
        for (Map.Entry<Item, Integer> entry : Time.getInstance().getTimeMapBeliBarang().entrySet()) {
            Item key = entry.getKey();
            Integer value = entry.getValue() - 1;
            Time.getInstance().setTimeMapBeliBarang(key, value);
            if (value == 0) {
                System.out.println("Barang sampai");
                Buyable barang = (Buyable) key;
                setUang(getUang() - barang.getHarga());
                inventory.addItem(key, 1);
                Time.getInstance().removeTimeMapBeliBarang(key);
            }
        }
    }

    public synchronized void decrementUpgradeRumahTime() {
        for (Map.Entry<Rumah, Integer> entry : Time.getInstance().getTimeMapUpgradeRumah().entrySet()) {
            Rumah key = entry.getKey();
            Integer value = entry.getValue() - 1;
            Time.getInstance().setTimeMapUpgradeRumah(key, value);
            if (value == 0) {
                System.out.printf("%s berhasil ditambahkan ke rumah %s%n", key.getNamaRuanganBaru(),
                        key.getNamaRumah());
                System.out.println("Rumah berhasil diupgrade");
                key.addRuangan(key.getRuanganToUpgrade(), key.getArahFinal(), key.getNamaRuanganBaru());
                Time.getInstance().removeTimeMapUpgradeRumah(key);
            }
        }
    }

    public void editRoom() {
        Scanner scanner = new Scanner(System.in);
        // cek apakah sim berada di rumah sim lain
        System.out.println("Menu :");
        System.out.println("1. Beli Barang");
        if (this.getRumahSaatIni() == this.getRumahUtama()) {
            System.out.println("2. Pasang Barang");
            System.out.println("3. Pindah Barang");
        }
        System.out.println("0. Kembali");
        System.out.print("Masukkan pilihan Anda (Angka saja) : ");
        int choice = scanner.nextInt();

        while (this.getRumahSaatIni() != this.getRumahUtama() && choice != 1) {
            System.out.println("Inputan salah. Silakan masukkan ulang angka.");
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            choice = scanner.nextInt();
        }

        // terus minta inputan sampai benar bahkan jika memasukkan inputan char/string
        while (choice < 0 || choice > 3) {
            System.out.println("Inputan salah. Silakan masukkan angka antara 0 dan 3.");
            System.out.print("Pilihan : ");
            choice = scanner.nextInt();
        }

        switch (choice) {
            case 0:
                clearTerminal();
                // Kembali ke menu utama
                MainMenu.showInGameMenu();
                break;
            case 1:
                clearTerminal();
                beliBarang();
                break;
            case 2:
                clearTerminal();
                // Cek inventory yang ingin dipasang, Cek Furniture apa aja di inventory
                inventory.showFurnitureInventory();
                System.out.print("Pilih furniture yang ingin dipasang: ");

                Furniture furniture = null;
                String pilih = scanner.nextLine();
                while (furniture == null) {
                    if (pilih.equals("Jam")) {
                        // check apakah ada jam di inventory
                        if (inventory.getFurniture("Jam") != null) {
                            furniture = inventory.getFurniture("Jam");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Jam tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("TV")) {
                        if (inventory.getFurniture("TV") != null) {
                            furniture = inventory.getFurniture("TV");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("TV tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Komputer")) {
                        if (inventory.getFurniture("Komputer") != null) {
                            furniture = inventory.getFurniture("Komputer");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Komputer tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Piano")) {
                        if (inventory.getFurniture("Piano") != null) {
                            furniture = inventory.getFurniture("Piano");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Piano tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Meja dan Kursi")) {
                        if (inventory.getFurniture("Meja dan Kursi") != null) {
                            furniture = inventory.getFurniture("Meja dan Kursi");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Meja dan Kursi tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Sajadah")) {
                        if (inventory.getFurniture("Sajadah") != null) {
                            furniture = inventory.getFurniture("Sajadah");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Sajadah tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Teleskop")) {
                        if (inventory.getFurniture("Teleskop") != null) {
                            furniture = inventory.getFurniture("Teleskop");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Teleskop tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Rak Buku")) {
                        if (inventory.getFurniture("Rak Buku") != null) {
                            furniture = inventory.getFurniture("Rak Buku");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Rak Buku tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Toilet")) {
                        if (inventory.getFurniture("Toilet") != null) {
                            furniture = inventory.getFurniture("Toilet");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Toilet tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Kompor Gas")) {
                        if (inventory.getFurniture("Kompor Gas") != null) {
                            furniture = inventory.getFurniture("Kompor Gas");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Kompor Gas tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Kompor Listrik")) {
                        if (inventory.getFurniture("Kompor Listrik") != null) {
                            furniture = inventory.getFurniture("Kompor Listrik");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Kompor Listrik tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Shower")) {
                        if (inventory.getFurniture("Shower") != null) {
                            furniture = inventory.getFurniture("Shower");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Shower tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Kasur Single")) {
                        if (inventory.getFurniture("Kasur Single") != null) {
                            furniture = inventory.getFurniture("Kasur Single");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Kasur Single tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Kasur Queen Size")) {
                        if (inventory.getFurniture("Kasur Queen Size") != null) {
                            furniture = inventory.getFurniture("Kasur Queen Size");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Kasur Queen Size tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else if (pilih.equals("Kasur King Size")) {
                        if (inventory.getFurniture("Kasur King Size") != null) {
                            furniture = inventory.getFurniture("Kasur King Size");
                            inventory.removeItem(furniture);
                        } else {
                            System.out.println("Kasur King Size tidak ada di inventory");
                            System.out.print("Pilih furniture yang ingin dipasang: ");
                            pilih = scanner.nextLine();
                        }
                    } else {
                        System.out.println("Inputan salah. Silakan masukkan nama furniture yang ingin dipasang.");
                        System.out.print("Pilih furniture yang ingin dipasang: ");
                        pilih = scanner.nextLine();
                    }
                }

                // Input point
                System.out.print("Masukkan koordinat X: ");
                int x = scanner.nextInt();
                System.out.print("Masukkan koordinat Y: ");
                int y = scanner.nextInt();

                Point point = new Point(x, y);
                ruanganSaatIni.addFurniture(furniture, point);
                break;
            case 3:
                clearTerminal();
                // Pindah Barang
                pindahBarang();
                break;
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

    // Memindahkan furnitur yang ada di ruanganSaatIni dari titik yang satu ke titik
    // yang lain
    public void pindahBarang() {
        Scanner scanner = new Scanner(System.in);
        // Cek apakah ada furniture di ruanganSaatIni
        if (ruanganSaatIni.getDaftarFurniture().size() == 0) {
            System.out.println("Tidak ada furniture di ruangan ini.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            scanner.nextLine();
            clearTerminal();
            editRoom();
            return;
        }

        // print daftar furniture yang ada di ruanganSaatIni
        System.out.println("Daftar furniture yang ada di ruangan: ");
        ruanganSaatIni.printDaftarFurnitureName();
        System.out.print("Masukkan nama furniture yang ingin dipindahkan: ");
        String pilih = scanner.nextLine();
        Furniture furniturePilihan = ruanganSaatIni.selectFurniture(pilih);
        // Cek apakah furniture ada di ruanganSaatIni
        while (furniturePilihan == null) {
            System.out.println("Furniture tidak ada di ruangan");
            System.out.print("Masukkan nama furniture yang ingin dipindahkan: ");
            pilih = scanner.nextLine();
        }

        // Input point
        System.out.print("Masukkan koordinat X: ");
        int x = scanner.nextInt();
        System.out.print("Masukkan koordinat Y: ");
        int y = scanner.nextInt();

        Point point = new Point(x, y);
        ruanganSaatIni.moveFurniture(furniturePilihan, point);
        System.out.println(furniturePilihan.getNama() + " berhasil dipindahkan");
    }

    public void showPekerjaan() {
        // Print header
        System.out.printf("%-5s| %-18s| %-6s| %-6s%n", "No.", "Pekerjaan", "Gaji", "Biaya");
        System.out.println("________________________________________");

        // Print the data row
        System.out.printf("%-5d| %-18s| %-6d| %-6s%n", 1, "Badut Sulap", 15, 8);
        System.out.printf("%-5d| %-18s| %-6d| %-6s%n", 2, "Koki", 30, 15);
        System.out.printf("%-5d| %-18s| %-6d| %-6s%n", 3, "Polisi", 35, 18);
        System.out.printf("%-5d| %-18s| %-6d| %-6s%n", 4, "Programmer", 45, 23);
        System.out.printf("%-5d| %-18s| %-6d| %-6s%n", 5, "Dokter", 50, 25);
    }

    public void gantiPekerjaan() {
        Scanner in = new Scanner(System.in);
        // Pengecekan apakah Sim bisa mengganti pekerjaan
        if (this.pekerjaan.getTimesWorked() < 720) {
            System.out.println("Anda harus bekerja minimal 12 menit untuk pekerjaan sekarang!!");
        } else if (isGantiPekerjaan) {
            System.out.println("Anda sudah mengganti pekerjaan hari ini, tunggu besok untuk mengganti pekerjaan lagi");
        } else {
            showPekerjaan();
            System.out.print("Pilih nomor pekerjaan yang diinginkan : ");
            int input = in.nextInt();
            String pilihan = "";
            int biaya = 0;
            if (input == 1) {
                pilihan = "Badut Sulap";
                biaya = 8;
            } else if (input == 2) {
                pilihan = "Koki";
                biaya = 15;
            } else if (input == 3) {
                pilihan = "Polisi";
                biaya = 18;
            } else if (input == 4) {
                pilihan = "Programmer";
                biaya = 23;
            } else if (input == 5) {
                pilihan = "Dokter";
                biaya = 25;
            }

            while (input < 1 || input > 5 || this.pekerjaan.equals(pilihan)) {
                if (this.pekerjaan.equals(pilihan)) {
                    System.out.println("Anda tidak bisa memilih pekerjaan Anda sekarang");
                } else {
                    System.out.println("Pilihan tidak valid");
                }
                System.out.println("Pilih nomor pekerjaan yang diinginkan : ");
                input = in.nextInt();
                if (input == 1) {
                    pilihan = "Badut Sulap";
                    biaya = 8;
                } else if (input == 2) {
                    pilihan = "Koki";
                    biaya = 15;
                } else if (input == 3) {
                    pilihan = "Polisi";
                    biaya = 18;
                } else if (input == 4) {
                    pilihan = "Programmer";
                    biaya = 23;
                } else if (input == 5) {
                    pilihan = "Dokter";
                    biaya = 25;
                }
            }

            // Membuat object pekerjaan yang pilih
            pekerjaanBaru = new Pekerjaan(pilihan);

            // Pengecekan apakah memiliki uang yang cukup
            if (uang < biaya) {
                System.out.println("Anda tidak memiliki uang yang cukup mengganti ke perkerjaan ini");
            } else {
                setUang(uang - biaya); // kurangi uang sebanyak biaya
                // Thread mengganti pekerjaan
                isGantiPekerjaan = true; // Dijadikan true agar tidak bisa ganti pekerjaan lagi
                waktuGantiPekerjaan = Time.getInstance().getTimeRemaining(); // Waktu ganti pekerjaan

                // Harinya sudah berganti, kemudian diganti pekerjaan selama 1 hari
                // setPekerjaan(pekerjaanPilihan);
                // isGantiPekerjaan = false;
                
            }
        }
    }

    public synchronized void setPekerjaanBaru() {
        // Pengecekan apakah sudah waktunya ganti pekerjaan
        if (waktuGantiPekerjaan != -1) {
            // Harinya sudah berganti, kemudian diganti pekerjaan selama 1 hari
            waktuGantiPekerjaan--;
            if(waktuGantiPekerjaan == 0) {
                setPekerjaan(pekerjaanBaru);
                isGantiPekerjaan = false;
                waktuGantiPekerjaan = -1;
            }
        }
    }

    public static void main(String[] args) {
        // Sim sim1 = new Sim("Gibran");
        // Sim sim2 = new Sim("Samudra");
        // World world = World.getInstance();
        // Item item = new MejaKursi();
        // System.out.println("Nama sim1: " + sim1.getNama());
        // System.out.println("Pekerjaan sim1: " +
        // sim1.getPekerjaan().getNamaPekerjaan());
        // System.out.println("Uang sim1: " + sim1.getUang());
        // System.out.println("Alamat inventory sim1: " + sim1.getInventory());
        // sim1.viewInventory();
        // sim1.addItemToInventory(item, 1);
        // sim1.viewInventory();
        // System.out.println("Apakah ada toilet di inventory sim1? " +
        // sim1.isItemInInventory(new Toilet()));
        // System.out
        // .println("Apakah ada " + item.getNama() + " di dalam inventory sim1? " +
        // sim1.isItemInInventory(item));
        // System.out.println("Kekenyangan : " + sim1.getKekenyangan());
        // System.out.printf("Mood %d\n", sim1.getMood());
        // System.out.printf("Kesehatan %d\n", sim1.getKesehatan());

        // System.out.println("\nNama sim2: " + sim2.getNama());
        // System.out.println("Pekerjaan sim2: " +
        // sim2.getPekerjaan().getNamaPekerjaan());
        // System.out.println("Uang sim2: " + sim2.getUang());
        // System.out.println("Alamat inventory sim2: " + sim2.getInventory());
        // sim2.viewInventory();
        // System.out.println("Apakah ada toilet di inventory sim2? " +
        // sim1.isItemInInventory(new Toilet()));
        // System.out
        // .println("Apakah ada " + item.getNama() + " di dalam inventory sim2? " +
        // sim1.isItemInInventory(item));

        // System.out.println("Sebelum ditambahkan ke world :");
        // try {
        // System.out.println("Posisi sim : " + sim1.getPosisiSim());
        // System.out.println("Rumah saat ini : " +
        // sim1.getRumahSaatIni().getNamaRumah());
        // System.out.println("Ruangan saat ini : " +
        // sim1.getRuanganSaatIni().getNamaRuangan());
        // System.out.println("Daftar ruangan di rumah saat ini : ");
        // sim1.getRumahSaatIni().printDaftarRuangan();
        // } catch (NullPointerException e) {
        // System.out.println("Sim belum memiliki rumah");
        // }

        // world.addRumah(sim1);
        // world.addRumah(sim2, new Point(13, 2));

        // System.out.println("Setelah ditambahkan ke world :");
        // System.out.println("Posisi rumah sim1 : " +
        // sim1.getRumahSaatIni().getLokasi().getX() + ", "
        // + sim1.getRumahSaatIni().getLokasi().getY());
        // System.out.println("Rumah saat ini: " +
        // sim1.getRumahSaatIni().getNamaRumah());
        // System.out.println("Ruangan saat ini : " +
        // sim1.getRuanganSaatIni().getNamaRuangan());
        // System.out.println("Daftar ruangan di rumah saat ini : ");
        // sim1.getRumahSaatIni().printDaftarRuangan();
        // System.out.println(sim1.getStatus());

        // System.out.println("\nPosisi rumah sim2 : " +
        // sim2.getRumahSaatIni().getLokasi().getX() + ", "
        // + sim2.getRumahSaatIni().getLokasi().getY());
        // System.out.println("Rumah saat ini: " +
        // sim2.getRumahSaatIni().getNamaRumah());
        // System.out.println("Ruangan saat ini : " +
        // sim2.getRuanganSaatIni().getNamaRuangan());
        // System.out.println("Daftar ruangan di rumah saat ini : ");
        // sim2.getRumahSaatIni().printDaftarRuangan();
        // System.out.println(sim2.getStatus());

        // sim1.berkunjung();

        // System.out.println("\nPosisi rumah sim1 saat ini : " +
        // sim1.getRumahSaatIni().getLokasi().getX() + ", "
        // + sim1.getRumahSaatIni().getLokasi().getY());
        Sim sim1 = new Sim("Gibran");
        Sim sim2 = new Sim("Samudra");
        World world = World.getInstance();
        Item item = new MejaKursi();
        System.out.println("Nama sim1: " + sim1.getNama());
        System.out.println("Pekerjaan sim1: " + sim1.getPekerjaan().getNamaPekerjaan());
        System.out.println("Uang sim1: " + sim1.getUang());
        System.out.println("Alamat inventory sim1: " + sim1.getInventory());
        sim1.viewInventory();
        sim1.addItemToInventory(item, 1);
        sim1.viewInventory();
        System.out.println("Apakah ada toilet di inventory sim1? " + sim1.isItemInInventory(new Toilet()));
        System.out
                .println("Apakah ada " + item.getNama() + " di dalam inventory sim1? " + sim1.isItemInInventory(item));
        System.out.println("Kekenyangan : " + sim1.getKekenyangan());
        System.out.printf("Mood %d\n", sim1.getMood());
        System.out.printf("Kesehatan %d\n", sim1.getKesehatan());

        System.out.println("\nNama sim2: " + sim2.getNama());
        System.out.println("Pekerjaan sim2: " + sim2.getPekerjaan().getNamaPekerjaan());
        System.out.println("Uang sim2: " + sim2.getUang());
        System.out.println("Alamat inventory sim2: " + sim2.getInventory());
        sim2.viewInventory();
        System.out.println("Apakah ada toilet di inventory sim2? " + sim1.isItemInInventory(new Toilet()));
        System.out
                .println("Apakah ada " + item.getNama() + " di dalam inventory sim2? " + sim1.isItemInInventory(item));

        System.out.println("Sebelum ditambahkan ke world :");
        try {
            System.out.println("Posisi sim : " + sim1.getPosisiSim());
            System.out.println("Rumah saat ini : " + sim1.getRumahSaatIni().getNamaRumah());
            System.out.println("Ruangan saat ini : " + sim1.getRuanganSaatIni().getNamaRuangan());
            System.out.println("Daftar ruangan di rumah saat ini : ");
            sim1.getRumahSaatIni().printDaftarRuangan();
        } catch (NullPointerException e) {
            System.out.println("Sim belum memiliki rumah");
        }

        world.addRumah(sim1);
        world.addRumah(sim2, new Point(13, 2));

        System.out.println("Setelah ditambahkan ke world :");
        System.out.println("Posisi rumah sim1 : " + sim1.getRumahSaatIni().getLokasi().getX() + ", "
                + sim1.getRumahSaatIni().getLokasi().getY());
        System.out.println("Rumah saat ini: " + sim1.getRumahSaatIni().getNamaRumah());
        System.out.println("Ruangan saat ini : " + sim1.getRuanganSaatIni().getNamaRuangan());
        System.out.println("Daftar ruangan di rumah saat ini : ");
        sim1.getRumahSaatIni().printDaftarRuangan();
        System.out.println(sim1.getStatus());

        System.out.println("\nPosisi rumah sim2 : " + sim2.getRumahSaatIni().getLokasi().getX() + ", "
                + sim2.getRumahSaatIni().getLokasi().getY());
        System.out.println("Rumah saat ini: " + sim2.getRumahSaatIni().getNamaRumah());
        System.out.println("Ruangan saat ini : " + sim2.getRuanganSaatIni().getNamaRuangan());
        System.out.println("Daftar ruangan di rumah saat ini : ");
        sim2.getRumahSaatIni().printDaftarRuangan();
        System.out.println(sim2.getStatus());

        sim1.berkunjung();

        System.out.println("\nPosisi rumah sim1 saat ini : " + sim1.getRumahSaatIni().getLokasi().getX() + ", "
                + sim1.getRumahSaatIni().getLokasi().getY());

        sim1.setNama("Gibran Jakarta");
        sim1.setPekerjaan(new Pekerjaan("Koki"));
        sim1.setUang(100);
        sim1.setKekenyangan(100);
        sim1.setMood(50);
        sim1.setKesehatan(15);

        // System.out.println("Pembelian barang dimulai");
        sim1.olahraga();
        System.out.println("Kekenyangan : " + sim1.getKekenyangan());
        System.out.printf("Mood %d\n", sim1.getMood());
        System.out.printf("Kesehatan %d\n", sim1.getKesehatan());
        System.out.printf("Uang %d\n", sim1.getUang());

        sim1.beliBarang();
        System.out.println(Time.getInstance().getCurrentTime());
        System.out.println(Time.getInstance().getTimeRemaining());
        Time.getInstance().getActivityTimeRemaining();
        sim1.getInventory().showInventory();

        sim1.kerja();
        System.out.println(Time.getInstance().getTimeRemaining());
        Time.getInstance().getActivityTimeRemaining();
        System.out.println(sim1.getPosisiSim());
        sim1.getInventory().showInventory();

        sim1.olahraga();
        System.out.println(Time.getInstance().getTimeRemaining());
        sim1.getInventory().showInventory();
    }
}

// Time.getInstance().getTimeMap().remove(item.getNama());

// jika thread sepanjang delivery time, barang sampai

// while (!status.equals("None") && deliveryTime > 0 ) {
// try {
// Thread.sleep(1000);
// Time.getInstance().incrementTime();
// System.out.println("Barang akan sampai dalam " + deliveryTime + " detik");
// deliveryTime--;
// } catch (InterruptedException e) {
// System.out.println("Thread interrupted");
// }
// }

// Time.getInstance().setTimeMap(item, barang.getDeliveryTime());
// if (Time.getInstance().getTimeMap().containsKey(item.getNama())
// && Time.getInstance().getTimeMap().get(item.getNama()) == 0) {
// System.out.println("Barang sampai");
// uang -= barang.getHarga();
// inventory.addItem(item, 1);
// Time.getInstance().getTimeMap().remove(item.getNama());
// }