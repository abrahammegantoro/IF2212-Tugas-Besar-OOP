package src.Sim;

import src.Pekerjaan.Pekerjaan;
import src.Inventory.Inventory;
import src.World.Time;
// import src.Item.Edible;
import src.Item.Item;
import src.Item.Furniture.*;
import src.World.Point;
import src.Item.Buyable;

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
    // private Rumah rumahUtama;
    // private Rumah rumahSaatIni;
    // private Ruangan ruanganSaatIni;
    private String namaRuanganSaatIni;
    private String namaRumahSaatIni;
    private String status;

    public Sim(String nama) { // , Rumah rumahUtama
        Random random = new Random();
        this.nama = nama;
        this.pekerjaan = new Pekerjaan(random.nextInt(1, 6));
        this.uang = 100;
        this.inventory = new Inventory<>();
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.posisiSim = new Point(0, 0);
        this.namaRuanganSaatIni = "Ruang Tamu";
        this.namaRumahSaatIni = "Rumah " + this.nama;
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
        Pekerjaan pekerjaanDefault = this.pekerjaan;
        int hariGanti = Time.getHari();
        this.pekerjaan = pekerjaan;
        this.uang -= pekerjaan.getGaji()/2;
        while (hariGanti = Time.getHari()){
            //do nothing
        }
        this.pekerjaan = pekerjaanDefault;
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

    public void setPosisiSim(Point posisiSim) {
        this.posisiSim = posisiSim;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Bagian Danang
    public void kerja() {
        /*
         * -10 kekenyangan / 30 detik
         * -10 mood / 30 detik
         * +gaji / 4 menit
         * kelipatan 120 detik
         */
        Scanner input = new Scanner(System.in);

        System.out.print("Durasi Kerja :  ");
        int durasi = input.nextInt();

        while (durasi % 30 != 0) {
            System.out.println("Durasi kerja harus kelipatan 120");
            System.out.print("Durasi Kerja :  ");
            durasi = input.nextInt();
        }
        final int durasiAkhir = durasi;
        setStatus("Kerja");
        Thread kerjaThread = new Thread(new Runnable() {
            public void run() {
                // Time.getInstance().consumeTime(durasiAkhir);
                int counter = 0;
                while (counter < durasiAkhir) {
                    try {
                        Thread.sleep(1000);
                        Time.getInstance().incrementTime();
                        counter++;
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
            }
        });
        kerjaThread.start();

        try {
            kerjaThread.join();
            setKekenyangan(kekenyangan - ((durasiAkhir / 30) * 10));
            setMood(mood - ((durasiAkhir / 30) * 10));
            setUang(uang + ((durasiAkhir / 240) * pekerjaan.getGaji()));

            pekerjaan.addTimedWorked(durasi);
            setStatus("None");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Bagian Abam
    public void olahraga() {
        Scanner input = new Scanner(System.in);

        System.out.print("Durasi Olahraga :  ");
        int durasi = input.nextInt();

        while (durasi % 5 != 0) {
            System.out.println("Durasi olahraga harus kelipatan 20");
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
                        counter++;
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted");
                    }
                }
            }
        });

        olahragaThread.start();

        try {
            olahragaThread.join();
            setKesehatan(kesehatan + ((durasiFinal / 20) * 5));
            setMood(mood + ((durasiFinal / 20) * 10));
            setKekenyangan(kekenyangan - ((durasiFinal / 20) * 5));
            setStatus("None");
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }
    }

    // Bagian Danang
    public void berkunjung(String namaRumah, int lamaBerkunjung) {
        /* Bagian Danang */
        pindahRumah(namaRumah);

        // Waktu dan Penghitungan Jarak belum
    }

    // Bagian Shulhan
    // Aksi yang jalan di belakang layar, tidak memengaruhi jalannya program lainnya
    public void beliBarang(Item item) {

        if (item instanceof Buyable) {
            Buyable barang = (Buyable) item;
            if (uang < barang.getHarga()) {
                System.out.println("Anda tidak memiliki uang yang cukup untuk membeli barang ini");
            } else {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int deliveryTime = barang.getDeliveryTime();
                        System.out.println("Barang akan sampai dalam " + deliveryTime + " detik");
                        Time.getInstance().setTimeMap(item.getNama(), deliveryTime);

                    }
                });
                t.start();

                System.out.println("Barang sampai");
                uang -= barang.getHarga();
                inventory.addItem(item, 1);
                Time.getInstance().getTimeMap().remove(item.getNama());

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

                // Time.getInstance().setTimeMap(item.getNama(), barang.getDeliveryTime());
                // if (Time.getInstance().getTimeMap().containsKey(item.getNama())
                // && Time.getInstance().getTimeMap().get(item.getNama()) == 0) {
                // System.out.println("Barang sampai");
                // uang -= barang.getHarga();
                // inventory.addItem(item, 1);
                // Time.getInstance().getTimeMap().remove(item.getNama());
                // }
            }
        }

        // Note : delivery time masih belum diurus
    }

    public void pindahRuangan(String namaRuangan) {
        this.namaRuanganSaatIni = namaRuangan;
    }

    public void pindahRumah(String namaRumah) {
        this.namaRumahSaatIni = namaRumah;
    }

    public static void main(String[] args) {
        Sim sim1 = new Sim("Gibran");
        Item item = new MejaKursi();
        System.out.println(sim1.getNama());
        System.out.println(sim1.getPekerjaan());
        System.out.println(sim1.getUang());
        System.out.println(sim1.getInventory());
        sim1.viewInventory();
        sim1.addItemToInventory(item, 1);
        sim1.viewInventory();
        System.out.println(sim1.isItemInInventory(new Toilet()));
        System.out.println(sim1.isItemInInventory(item));
        System.out.println(sim1.getKekenyangan());
        System.out.println(sim1.getMood());
        System.out.println(sim1.getKesehatan());
        System.out.println(sim1.getPosisiSim());
        System.out.println(sim1.getNamaRuanganSaatIni());
        System.out.println(sim1.getNamaRumahSaatIni());
        System.out.println(sim1.getStatus());
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

        sim1.beliBarang(new Toilet());
        System.out.println(Time.getInstance().getCurrentTime());
        System.out.println(Time.getInstance().getTimeRemaining());
        Time.getInstance().getActivityTimeRemaining();

        sim1.kerja();
        System.out.println(Time.getInstance().getTimeRemaining());
        Time.getInstance().getActivityTimeRemaining();
        System.out.println(sim1.getPosisiSim());
    }
}
