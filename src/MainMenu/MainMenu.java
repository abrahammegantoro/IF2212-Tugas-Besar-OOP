package src.MainMenu;

import java.util.*;

import src.Item.Furniture.Clock;
import src.Item.Furniture.Furniture;
import src.Item.Furniture.Komputer;
import src.Item.Furniture.MejaKursi;
import src.Item.Furniture.Piano;
import src.Item.Furniture.RakBuku;
import src.Item.Furniture.Sajadah;
import src.Item.Furniture.Shower;
import src.Item.Furniture.TV;
import src.Item.Furniture.Teleskop;
import src.Item.Furniture.Toilet;
import src.Item.Furniture.Bed.Bed;
import src.Item.Furniture.Bed.KingBed;
import src.Item.Furniture.Stove.Stove;
import src.Sim.Sim;
import src.World.Point;
import src.World.World;

public class MainMenu {
    private static ArrayList<Sim> listSim = new ArrayList<Sim>();
    private static World world = World.getInstance();
    private static boolean isGameStarted = false;
    private static Sim currentSim;
    private static Scanner in = new Scanner(System.in);

    public static void start() {
        addSim();
        isGameStarted = true;
        while (isGameStarted) {
            showInGameMenu();
        }
    }

    // public static void load(){
    // //masih dalam proses
    // }

    // Menu paling pertama yang ditampilkan
    public static void showGameMenu() {
        System.out.println("Selamat datang di Simpli-City!");
        System.out.println("Silakan pilih menu yang tersedia :");
        System.out.println("1. Start Game");
        System.out.println("2. Help");
        System.out.println("3. Exit");
        System.out.print("Masukkan pilihan Anda (Angka saja) : ");
        int input = in.nextInt();
        in.nextLine();
        switch (input) {
            case 1:
                showGameMenuNewGameOrLoadGame();
                break;
            case 2:
                help();
                showGameMenu();
                break;
            case 3:
                System.out.println("Terima kasih telah bermain!");
                System.exit(0);
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
                showGameMenu();
                break;
        }
    }

    // Menu kedua yang ditampilkan
    public static void showGameMenuNewGameOrLoadGame() {
        System.out.println("Silakan pilih menu yang tersedia :");
        System.out.println("1. New Game");
        System.out.println("2. Load Game (msh blm bisa)");
        System.out.println("3. Back");
        System.out.print("Masukkan pilihan Anda (Angka saja) : ");
        int input = in.nextInt();
        in.nextLine(); // hati-hati
        switch (input) {
            case 1:
                addSim();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 2:
                // load();
                showGameMenuNewGameOrLoadGame();
                break;
            case 3:
                showGameMenu();
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
                showGameMenuNewGameOrLoadGame();
                break;
        }
    }

    // Menu setelah game dimulai dan sim pertama sudah dibuat
    public static void showInGameMenu() {
        System.out.println("Silakan pilih menu yang tersedia :");
        System.out.println("1. Kerja");
        System.out.println("2. Olahraga");
        System.out.println("3. Move to Object");
        System.out.println("4. Berkunjung");
        System.out.println("5. Upgrade Rumah"); // jgn lupa dicek dulu di rumahnya sendiri ato kgk
        System.out.println("6. Pindah Ruangan");
        System.out.println("7. Lihat Inventory");
        System.out.println("8. Edit Room"); // Pasang Barang dan Beli Barang (jgn lupa dicek dulu di rumahnya sendiri
                                            // ato kgk)
        System.out.println("9. View List Object");
        System.out.println("10. View Sim Info");
        System.out.println("11. View Current Location");
        System.out.println("12. Add Sim");
        System.out.println("13. Change Sim");
        System.out.println("14. Save Game");
        System.out.println("15. Load Game");
        System.out.println("16. Help");
        System.out.println("17. Exit");
        System.out.println("18. Lihat Aksi Tambahan");
        System.out.print("Masukkan pilihan Anda (Angka saja) : ");
        int input = in.nextInt();
        in.nextLine();
        switch (input) {
            case 1:
                currentSim.kerja();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 2:
                currentSim.olahraga();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 3:
                moveToObject();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 4:
                currentSim.berkunjung();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 5:
                if (currentSim.getRumahSaatIni() == currentSim.getRumahUtama()) {
                    currentSim.getRumahUtama().upgradeRumah(currentSim);
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    showInGameMenu();
                } else {
                    System.out.println("Anda tidak berada di rumah utama.");
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    showInGameMenu();
                }
                break;
            case 6:
                currentSim.pindahRuangan();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 7:
                currentSim.viewInventory();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 8:
                currentSim.editRoom();
                break;
            case 9:
                currentSim.getRuanganSaatIni().printDaftarFurnitureName();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                break;
            case 10:
                viewSimInfo(); // jgn lupa rapiin, bikinnya di Sim.java
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 11:
                viewCurrentLocation(); // jgn lupa rapiin, bikinnya di Sim.java
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 12:
                addSim();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 13:
                changeSim();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 14:
                // save();
                break;
            case 15:
                // load();
                break;
            case 16:
                help();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            case 17:
                System.out.println("Terima kasih telah bermain!");
                System.exit(0);
                break;
            case 18:
                showAksiOnFurniture();
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                showInGameMenu();
                break;
            default:
                System.out.println("Pilihan tidak tersedia.");
                delay(1000);
                showInGameMenu();
                break;
        }
    }

    public static void showAksiOnFurniture() {
        if (!currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Furniture.class)) {
            System.out.println("Anda tidak berada di furniture.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            showInGameMenu();
        } else {
            System.out.println("Silakan pilih aksi untuk melakukannya:");
            System.out.print("1. ");
            if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, MejaKursi.class)) {
                System.out.print("Makan");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Bed.class)) {
                System.out.print("Tidur");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Stove.class)) {
                System.out.print("Masak");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Clock.class)) {
                System.out.print("Lihat Waktu");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Komputer.class)) {
                System.out.print("Main Game");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Piano.class)) {
                System.out.print("Main Piano");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, RakBuku.class)) {
                System.out.print("Baca Buku");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Sajadah.class)) {
                System.out.print("Sholat");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Shower.class)) {
                System.out.print("Mandi");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Teleskop.class)) {
                System.out.print("Lihat Bintang");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Toilet.class)) {
                System.out.print("Buang Air");
            } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, TV.class)) {
                System.out.print("Nonton TV");
            }
            System.out.println("0. Kembali");
            System.out.print("Pilihan: ");
            int pilihan = in.nextInt();
            in.nextLine();
            switch (pilihan) {
                case 1:
                    if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, MejaKursi.class)) {
                        MejaKursi.makan(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Bed.class)) {
                        Bed.tidur(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Stove.class)) {
                        Stove.masak(currentSim, currentSim.getInventory());
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Clock.class)) {
                        Clock.lihatWaktu();
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Komputer.class)) {
                        Komputer.mainGame(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Piano.class)) {
                        Piano.mainPiano(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, RakBuku.class)) {
                        RakBuku.bacaBuku(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Sajadah.class)) {
                        Sajadah.sholat(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Shower.class)) {
                        Shower.mandi(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Teleskop.class)) {
                        Teleskop.lihatBintang(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Toilet.class)) {
                        Toilet.buangAir(currentSim);
                    } else if (currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, TV.class)) {
                        TV.nontonTV(currentSim);
                    }
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    showInGameMenu();
                    break;

                case 0:
                    showInGameMenu();
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
                    delay(1000);
                    showAksiOnFurniture();
                    break;
            }

        }
    }

    private static void delay(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void addSim() { // Menambahkan sim baru
        System.out.println("Silakan masukkan nama sim Anda");
        String nama = in.nextLine();
        Sim sim = new Sim(nama);
        listSim.add(sim);
        currentSim = sim;
        System.out.println("Sim berhasil dibuat.");

        // Pembuatan rumah, user memilih untuk dibuatkan rumah di point yang di mana
        // saja ataupun yang dipilih oleh user secara langsung
        System.out.println(
                "Apakah anda ingin menentukan lokasi/titik tertentu ((0, 0) s.d. (64, 64)) dari rumah sim? Jika tidak, akan dipilihkan secara otomatis. (Y/N)");
        String input = in.nextLine();
        if (input.equals("Y")) {
            System.out.println("Silakan masukkan koordinat x dan y dari rumah sim Anda");
            System.out.print("x : ");
            int x = in.nextInt();
            System.out.print("y : ");
            int y = in.nextInt();
            world.addRumah(sim, new Point(x, y));
            System.out.println("Rumah sim berhasil dibuat.");
        } else {
            world.addRumah(sim);
            System.out.println("Rumah sim berhasil dibuat.");
        }
    }

    public static void moveToObject() {
        // print available furnitures
        System.out.println("Berikut adalah daftar furniture yang tersedia di ruangan ini :");
        currentSim.getRuanganSaatIni().printDaftarFurnitureName();
        System.out.println("Silakan pilih furniture yang ingin Anda tuju :");
        String furnitureName = in.nextLine();
        currentSim.getRuanganSaatIni().moveSimToFurniture(currentSim, furnitureName);
        // udah bisa make aksi di furniture
        System.out.println("Anda sudah berada di " + furnitureName + " pada titik (" + currentSim.getPosisiSim().getX()
                + ", " + currentSim.getPosisiSim().getY() + ")");
    }

    public static void changeSim() {
        System.out.println("Silakan pilih sim yang ingin Anda mainkan :");
        for (int i = 0; i < listSim.size(); i++) {
            System.out.println((i + 1) + ". " + listSim.get(i).getNama());
        }
        System.out.print("Masukkan pilihan Anda (Angka saja) : ");
        int input = in.nextInt();
        currentSim = listSim.get(input - 1);
    }

    public static void viewSimInfo() {
        System.out.println("Berikut adalah informasi sim yang sedang Anda mainkan");
        System.out.println("Nama        : " + currentSim.getNama());
        System.out.println("Pekerjaan   : " + currentSim.getPekerjaan().getNamaPekerjaan());
        System.out.println("Kesehatan   : " + currentSim.getKesehatan());
        System.out.println("Kekenyangan : " + currentSim.getKekenyangan());
        System.out.println("Mood        : " + currentSim.getMood());
        System.out.println("Uang        : " + currentSim.getUang());
    }

    public static void viewCurrentLocation() {
        System.out.println("Sim sedang berada di rumah " + currentSim.getRumahSaatIni().getNamaRumah()
                + ", pada ruangan " + currentSim.getRuanganSaatIni().getNamaRuangan());
    }

    public static void help() {
        boolean end = false;
        int input;

        while (!end) {
            System.out.println("HELP MENU :");
            System.out.println("1. Apa itu Simplicity?");
            System.out.println("2. Bagaimana cara memulai game ini?");
            System.out.println("3. Tentang Rumah");
            System.out.println("4. Tentang Pekerjaan");
            System.out.println("5. Tentang Kesejahteraan");
            System.out.println("0. Close\n");

            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            input = in.nextInt();
            if (input == 1) {
                System.out.println("Simplicity merupakan permainan berbasis Command line interface.");
                System.out.println(
                        "Dalam game ini, Anda akan membuat seorang Sim dan menjalankan kehidupan sebagai Sim.");
                System.out.println(
                        "Sebagai Sim, Anda akan tinggal dalam sebuah rumah dan melakukan berbagai aksi untuk menjalani kehidupan");
                System.out.println("Segera buat Sim dan mulai kehidupan Anda sebagai Sim!!!\n");
            } else if (input == 2) {
                System.out.println("Pertama, Anda harus membuat seorang Sim yang dapat dilakukan di opsi addSim.");
                System.out.println("Setelah Sim dibuat, Sim Anda akan dispawn di suatu tempat bersamaan dengan rumah.");
                System.out.println(
                        "Di dalam rumah, Anda dapat meletakkan berbagai furniture dan melakukan aksi dengan item tersebut.");
                System.out.println(
                        "Item dapat beli dengan uang yang Anda miliki. Untuk mendapatkan uang Anda harus bekerja.");
                System.out.println("Selain itu, Sim Anda memiliki parameter kesejahteraan.");
                System.out.println(
                        "Sim Anda akan mati bila salah satu atau beberapa parameter tersebut mencapai nilai 0.");
                System.out.println("Kesejahteraan dapat dipengaruhi oleh aksi yang dilakukan oleh Sim.");
                System.out.println(
                        "Oleh karena itu, Anda harus melakukan berbagai macam aksi untuk menjalankan hidup sebagai Sim.");
            } else if (input == 3) {
                System.out.println(
                        "Setiap Sim yang Anda buat akan memiliki rumah yang diperoleh saat Sim tersebut dibentuk.");
                System.out.println("Rumah yang baru dibentuk akan terdiri dari 1 ruangan yang berukuran 6 x 6.");
                System.out.println("Di dalam ruangan, Anda dapat meletakkan berbagai macam furniture.");
                System.out.println("Furniture yang berada dalam ruangan dapat membuat Sim melakukan aksi khusus.");
                System.out.println("Anda juga dapat menambah ruangan dalam rumah dalam melakukan upgrade.");
            } else if (input == 4) {
                System.out.println("Pekerjaan akan dipilih random saat Sim dibuat.");
                System.out.println("Sim akan mendapat gaji setiap dia bekerja selama 4 menit.");
                System.out.println(
                        "Sim juga nantinya dapat mengganti pekerjaan dengan membayar 50% dari gaji default pekerjaan tersebut.");
                System.out.println(
                        "Sim yang mengganti pekerjaan hanya berlaku selam 1 hari, setelah 1 hari pekerjaannya akan kembali menjadi pekerjaan awal.");
            } else if (input == 5) {
                System.out.println(
                        "Sim memiliki parameter kesejahteraan yang terdiri dari 3 parameter, kesehatan, kekenyangan dan mood.");
                System.out.println("Sim akan mati salah satu dari parameter tersebut menyentuh nilai 0.");
                System.out.println("Parameter kesejahteraan bisa dipengaruhi oleh aksi yang dilakukan oleh Sim.");
            } else if (input == 0) {
                end = true;
            }
        }
    }

    public static void exit() {
        System.out.println("Terima kasih telah memainkan Simpli-city");
        System.exit(0);
    }

    public static void main(String[] args) {
        showGameMenu();
    }
}
