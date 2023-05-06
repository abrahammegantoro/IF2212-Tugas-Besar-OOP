package src.MainMenu;

import java.util.*;

import src.AllData.AllData;
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

    public static void addSim(Sim sim) {
        listSim.add(sim);
    }

    // Hapus sim dari listSim dan ganti currentSim dengan inputan pengguna
    public static void removeSimAndChangeSim(Sim sim) {
        listSim.remove(sim);
        if (!isAllSimDead()) {
            // Menerima masukan dari pengguna sim mana yang ingin dimainkan
            System.out.println("Silakan pilih sim yang ingin dimainkan :");
            for (int i = 0; i < listSim.size(); i++) {
                System.out.println((i + 1) + ". " + listSim.get(i).getNama());
            }
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            int input = in.nextInt();
            in.nextLine();
            currentSim = listSim.get(input - 1);
        }
    }

    // Menu paling pertama yang ditampilkan
    public static void showGameMenu() {
        try {
            clearTerminal();
            System.out.println("███████╗███████╗██╗      █████╗ ███╗   ███╗ █████╗ ████████╗    ██████╗  █████╗ ████████╗ █████╗ ███╗   ██╗ ██████╗ ");
            System.out.println("██╔════╝██╔════╝██║     ██╔══██╗████╗ ████║██╔══██╗╚══██╔══╝    ██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗████╗  ██║██╔════╝ ");
            System.out.println("███████╗█████╗  ██║     ███████║██╔████╔██║███████║   ██║       ██║  ██║███████║   ██║   ███████║██╔██╗ ██║██║  ███╗");
            System.out.println("╚════██║██╔══╝  ██║     ██╔══██║██║╚██╔╝██║██╔══██║   ██║       ██║  ██║██╔══██║   ██║   ██╔══██║██║╚██╗██║██║   ██║");
            System.out.println("███████║███████╗███████╗██║  ██║██║ ╚═╝ ██║██║  ██║   ██║       ██████╔╝██║  ██║   ██║   ██║  ██║██║ ╚████║╚██████╔╝");
            System.out.println("╚══════╝╚══════╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝  ╚═╝   ╚═╝       ╚═════╝ ╚═╝  ╚═╝   ╚═╝   ╚═╝  ╚═╝╚═╝  ╚═══╝ ╚═════╝ ");
            System.out.println("                 ██████╗ ██╗    ███████╗██╗███╗   ███╗██████╗ ██╗     ██╗ ██████╗██╗████████╗██╗   ██╗");
            System.out.println("                 ██╔══██╗██║    ██╔════╝██║████╗ ████║██╔══██╗██║     ██║██╔════╝██║╚══██╔══╝╚██╗ ██╔╝");
            System.out.println("                 ██║  ██║██║    ███████╗██║██╔████╔██║██████╔╝██║     ██║██║     ██║   ██║    ╚████╔╝ ");
            System.out.println("                 ██║  ██║██║    ╚════██║██║██║╚██╔╝██║██╔═══╝ ██║     ██║██║     ██║   ██║     ╚██╔╝  ");
            System.out.println("                 ██████╔╝██║    ███████║██║██║ ╚═╝ ██║██║     ███████╗██║╚██████╗██║   ██║      ██║   ");
            System.out.println("                 ╚═════╝ ╚═╝    ╚══════╝╚═╝╚═╝     ╚═╝╚═╝     ╚══════╝╚═╝ ╚═════╝╚═╝   ╚═╝      ╚═╝   ");
            System.out.println("Silakan pilih menu yang tersedia :");
            System.out.println("1. Start Game");
            System.out.println("2. Help");
            System.out.println("0. Exit Game");
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            int input = in.nextInt();
            in.nextLine();

            switch (input) {
                case 1:
                    clearTerminal();
                    showGameMenuNewGameOrLoadGame();
                    break;
                case 2:
                    clearTerminal();
                    help();
                    showGameMenu();
                    break;
                case 0:
                    clearTerminal();
                    System.out.println("Terima kasih telah bermain Simplicity!");
                    System.exit(0);
                    break;
                default:
                    clearTerminal();
                    System.out.println("Pilihan tidak tersedia.");
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    clearTerminal();
                    showGameMenu();
                    break;
            }
        } catch (InputMismatchException e) {
            clearTerminal();
            System.out.println("Masukan tidak valid. Silakan masukkan angka.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            clearTerminal();
            showGameMenu();
        }
    }

    // Menu kedua yang ditampilkan
    public static void showGameMenuNewGameOrLoadGame() {
        try {
            System.out.println("███╗   ██╗███████╗██╗    ██╗     ██████╗ ██████╗     ██╗      ██████╗  █████╗ ██████╗ ");
            System.out.println("████╗  ██║██╔════╝██║    ██║    ██╔═══██╗██╔══██╗    ██║     ██╔═══██╗██╔══██╗██╔══██╗");
            System.out.println("██╔██╗ ██║█████╗  ██║ █╗ ██║    ██║   ██║██████╔╝    ██║     ██║   ██║███████║██║  ██║");
            System.out.println("██║╚██╗██║██╔══╝  ██║███╗██║    ██║   ██║██╔══██╗    ██║     ██║   ██║██╔══██║██║  ██║");
            System.out.println("██║ ╚████║███████╗╚███╔███╔╝    ╚██████╔╝██║  ██║    ███████╗╚██████╔╝██║  ██║██████╔╝");
            System.out.println("╚═╝  ╚═══╝╚══════╝ ╚══╝╚══╝      ╚═════╝ ╚═╝  ╚═╝    ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═════╝ ");
            System.out.println("Silakan pilih menu yang tersedia :");
            System.out.println("1. New Game");
            System.out.println("2. Load Game (msh blm bisa)");
            System.out.println("0. Kembali");
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            int input = in.nextInt();
            in.nextLine(); // hati-hati
            switch (input) {
                case 1:
                    clearTerminal();
                    addSim();
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    clearTerminal();
                    showInGameMenu();
                    break;
                case 2:
                    AllData.load();
                    showInGameMenu();
                    break;
                case 0:
                    clearTerminal();
                    showGameMenu();
                    break;
                default:
                    clearTerminal();
                    System.out.println("Pilihan tidak tersedia.");
                    System.out.println("Tekan Enter untuk melanjutkan...");
                    in.nextLine();
                    in.nextLine();
                    clearTerminal();
                    showGameMenuNewGameOrLoadGame();
                    break;
            }
        } catch (InputMismatchException e) {
            clearTerminal();
            System.out.println("Masukan tidak valid. Silakan masukkan angka.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            in.nextLine();
            clearTerminal();
            showGameMenuNewGameOrLoadGame();
        }
    }

    // Menu setelah game dimulai dan sim pertama sudah dibuat
    public static void showInGameMenu() {
        if (!isAllSimDead()) {
            try {
                System.out.println("███╗   ███╗ █████╗ ██╗███╗   ██╗    ███╗   ███╗███████╗███╗   ██╗██╗   ██╗");
                System.out.println("████╗ ████║██╔══██╗██║████╗  ██║    ████╗ ████║██╔════╝████╗  ██║██║   ██║");
                System.out.println("██╔████╔██║███████║██║██╔██╗ ██║    ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║");
                System.out.println("██║╚██╔╝██║██╔══██║██║██║╚██╗██║    ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║");
                System.out.println("██║ ╚═╝ ██║██║  ██║██║██║ ╚████║    ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝");
                System.out.println("╚═╝     ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═══╝    ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ ");
                System.out.println("Silakan pilih menu yang tersedia :");
                for (int i = 1; i <= 10; i++) {
                    System.out.printf("%2d. %-25s", i, getMenuOption(i));
                    if (i + 10 <= 19) {
                        System.out.printf("%2d. %s", i + 10, getMenuOption(i + 10));
                    }
                    System.out.println();
                }
                System.out.print("Masukkan pilihan Anda (Angka saja) : ");
                int input = in.nextInt();
                in.nextLine();
                switch (input) {
                    case 1:
                        clearTerminal();
                        currentSim.kerja();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 2:
                        clearTerminal();
                        currentSim.olahraga();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 3:
                        clearTerminal();
                        currentSim.berkunjung();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 4:
                        clearTerminal();
                        moveToObject();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 5:
                        clearTerminal();
                        showAksiOnFurniture();
                        // System.out.println("Tekan Enter untuk melanjutkan...");
                        // in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 6:
                        clearTerminal();
                        if (currentSim.getRumahSaatIni() == currentSim.getRumahUtama()) {
                            currentSim.getRumahUtama().upgradeRumah(currentSim);
                            System.out.println("Tekan Enter untuk melanjutkan...");
                            in.nextLine();
                            clearTerminal();
                            showInGameMenu();
                        } else {
                            System.out.println("Anda tidak berada di rumah utama.");
                            System.out.println("Tekan Enter untuk melanjutkan...");
                            in.nextLine();
                            clearTerminal();
                            showInGameMenu();
                        }
                        break;
                    case 7:
                        clearTerminal();
                        currentSim.pindahRuangan();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 8:
                        clearTerminal();
                        currentSim.viewInventory();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 9:
                        clearTerminal();
                        currentSim.editRoom();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 10:
                        clearTerminal();
                        currentSim.gantiPekerjaan();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 11:
                        clearTerminal();
                        currentSim.getRuanganSaatIni().printDaftarFurnitureName();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 12:
                        clearTerminal();
                        viewSimInfo(); // jgn lupa rapiin, bikinnya di Sim.java
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 13:
                        clearTerminal();
                        viewCurrentLocation(); // jgn lupa rapiin, bikinnya di Sim.java
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 14:
                        clearTerminal();
                        addSim();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        break;
                    case 15:
                        clearTerminal();
                        changeSim();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 16:
                        AllData.save();
                        showInGameMenu();
                        break;
                    case 17:
                        // load();
                        break;
                    case 18:
                        clearTerminal();
                        help();
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                    case 19:
                        clearTerminal();
                        System.out.println("Terima kasih telah bermain Simplicity!");
                        System.exit(0);
                        break;
                    default:
                        clearTerminal();
                        System.out.println("Pilihan tidak tersedia.");
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showInGameMenu();
                        break;
                }
            } catch (InputMismatchException e) {
                clearTerminal();
                System.out.println("Input harus berupa angka.");
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                clearTerminal();
                showInGameMenu();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            clearTerminal();
            System.out.println("Semua sim anda telah meninggal.");
            System.out.println("Tekan Enter untuk mengakhiri permainan :(");
            in.nextLine();
            clearTerminal();
            System.out.println("Terima kasih telah bermain Simplicity!");
            // exit program
            System.exit(0);
        }
    }

    public static String getMenuOption(int option) {
        switch (option) {
            case 1:
                return "Kerja";
            case 2:
                return "Olahraga";
            case 3:
                return "Berkunjung";
            case 4:
                return "Move to Object";
            case 5:
                return "Lihat Aksi Tambahan";
            case 6:
                return "Upgrade Rumah";
            case 7:
                return "Pindah Ruangan";
            case 8:
                return "Lihat Inventory";
            case 9:
                return "Edit Room";
            case 10:
                return "Ganti Pekerjaan";
            case 11:
                return "View List Object";
            case 12:
                return "View Sim Info";
            case 13:
                return "View Current Location";
            case 14:
                return "Add Sim";
            case 15:
                return "Change Sim";
            case 16:
                return "Save Game";
            case 17:
                return "Load Game";
            case 18:
                return "Help";
            case 19:
                return "Exit Game";
            default:
                return "";
        }
    }

    public static void showAksiOnFurniture() {
        if (!currentSim.getRuanganSaatIni().isSimOnFurniture(currentSim, Furniture.class)) {
            System.out.println("Anda tidak berada di furniture.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            clearTerminal();
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
            System.out.println("\n0. Kembali");
            System.out.print("Pilihan: ");
            String pilihan = in.nextLine();
            try {
                switch (pilihan) {
                    case "1":
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
                        pilihan = in.nextLine();

                        clearTerminal();
                        showInGameMenu();
                        break;

                    case "0":
                        clearTerminal();
                        showInGameMenu();
                        break;
                    default:
                        clearTerminal();
                        System.out.println("Pilihan tidak tersedia.");
                        System.out.println("Tekan Enter untuk melanjutkan...");
                        in.nextLine();
                        clearTerminal();
                        showAksiOnFurniture();
                        break;
                }
            } catch (Exception e) {
                clearTerminal();
                System.out.println("Testing");
                System.out.println("Pilihan tidak tersedia.");
                System.out.println("Tekan Enter untuk melanjutkan...");
                e.printStackTrace();
                // in.nextLine();
                // clearTerminal();
                // showAksiOnFurniture();
            }

        } //
    }

    public static void addSim() { // Menambahkan sim baru
        System.out.print("Silakan masukkan nama sim Anda : ");
        String nama = in.nextLine();

        // handling input nama sim yang kosong dan juga kurang dari empat karakter
        while (nama.equals("") || nama.length() < 4) {
            System.out.println("Nama sim tidak boleh kosong dan harus lebih dari 4 karakter.");
            System.out.print("Silakan masukkan nama sim Anda : ");
            nama = in.nextLine();
        }

        // Cek apakah ada nama sim yang sama
        for (Sim sim : listSim) {
            if (sim.getNama().equals(nama)) {
                System.out.println("Nama sim sudah ada.");
                System.out.print("Silakan masukkan nama sim Anda : ");
                nama = in.nextLine();
            }
        }

        Sim sim = new Sim(nama);
        System.out.println("Sim berhasil dibuat.");

        // Pembuatan rumah, user memilih untuk dibuatkan rumah di point yang di mana
        // saja ataupun yang dipilih oleh user secara langsung
        System.out.println(
                "Apakah anda ingin menentukan lokasi/titik tertentu ((0, 0) s.d. (64, 64)) dari rumah sim? Jika tidak, akan dipilihkan secara otomatis. (Y/N)");
        String input = in.nextLine();

        while (!input.equals("Y") && !input.equals("N")) {
            System.out.println("Input tidak valid.");
            System.out.print("Masukkan ulang input (Y/N) : ");
            input = in.nextLine();
        }

        // handling input yang tidak valid
        if (input.equals("Y")) {
            try {
                System.out.println("Silakan masukkan koordinat X dan Y untuk lokasi rumah untuk sim yang baru dibuat.");
                System.out.print("X : ");
                int x = in.nextInt();
                System.out.print("Y : ");
                int y = in.nextInt();
                world.addRumah(sim, new Point(x, y));
                // System.out.println("Rumah sim berhasil dibuat di titik (" + x + ", " + y + ")");
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid.");
                System.out.println("Silakan ulangi pembuatan rumah sim.");
                System.out.println("Tekan Enter untuk melanjutkan...");
                in.nextLine();
                in.nextLine();
                addSim();
            }
        } else if (input.equals("N")) {
            world.addRumah(sim);
        }
    }

    public static void moveToObject() {
        // Kalau isi furnitures kosong
        if (currentSim.getRuanganSaatIni().getDaftarFurniture().size() == 0) {
            System.out.println("Tidak ada furniture di ruangan ini.\n");
            // System.out.println("Anda tetap berada pada titik (" + currentSim.getPosisiSim().getX()
            // + ", " + currentSim.getPosisiSim().getY() + ")");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            clearTerminal();
            showInGameMenu();
        } else {
            // print available furnitures
            System.out.println("Berikut adalah daftar furniture yang tersedia di ruangan ini :");
            currentSim.getRuanganSaatIni().printDaftarFurnitureName();
            System.out.println("Silakan pilih furniture yang ingin Anda tuju :");
            String furnitureName = in.nextLine();
            currentSim.getRuanganSaatIni().moveSimToFurniture(currentSim, furnitureName);
        }       
    }

    public static void changeSim() {
        System.out.println("Silakan pilih sim yang ingin Anda mainkan :");
        for (int i = 0; i < listSim.size(); i++) {
            System.out.println((i + 1) + ". " + listSim.get(i).getNama());
        }

        try {
            System.out.print("Masukkan pilihan Anda (Angka saja) : ");
            int input = in.nextInt();
            currentSim = listSim.get(input - 1);
        } catch (InputMismatchException e) {
            clearTerminal();
            System.out.println("Input tidak valid.");
            System.out.println("Silakan ulangi pemilihan sim.");
            System.out.println("Tekan Enter untuk melanjutkan...");
            in.nextLine();
            in.nextLine();
            changeSim();
        }
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
        // printRuangan dan print simMap
        world.printWorld();
        System.out.println("\n");
        currentSim.getRuanganSaatIni().printRuangan();
        System.out.println("\n");
        // print daftarSim
        System.out.println("Berikut adalah daftar sim yang sedang berada di ruangan ini :");
        Map<Sim, Point> map = currentSim.getRuanganSaatIni().getDaftarSim();
        for (Map.Entry<Sim, Point> entry : map.entrySet()) {
            System.out.println(entry.getKey().getNama() + " pada titik (" +
                    entry.getValue().getX() + ", "
                    + entry.getValue().getY() + ")");
        }
        currentSim.getRuanganSaatIni().printSim();
        System.out.println("\nSim sedang berada di " + currentSim.getRumahSaatIni().getNamaRumah()
                + " pada titik dunia " + "(" + currentSim.getRumahSaatIni().getLokasi().getX() + ", "
                + currentSim.getRumahSaatIni().getLokasi().getY() + ")" + ", di "
                + currentSim.getRuanganSaatIni().getNamaRuangan() + " pada titik ruangan " + "("
                + currentSim.getPosisiSim().getX() + ", " + currentSim.getPosisiSim().getY() + ")");
    }

    public static void help() {
        boolean end = false;
        int input;

        while (!end) {
            System.out.println("██╗  ██╗███████╗██╗     ██████╗     ███╗   ███╗███████╗███╗   ██╗██╗   ██╗");
            System.out.println("██║  ██║██╔════╝██║     ██╔══██╗    ████╗ ████║██╔════╝████╗  ██║██║   ██║");
            System.out.println("███████║█████╗  ██║     ██████╔╝    ██╔████╔██║█████╗  ██╔██╗ ██║██║   ██║");
            System.out.println("██╔══██║██╔══╝  ██║     ██╔═══╝     ██║╚██╔╝██║██╔══╝  ██║╚██╗██║██║   ██║");
            System.out.println("██║  ██║███████╗███████╗██║         ██║ ╚═╝ ██║███████╗██║ ╚████║╚██████╔╝");
            System.out.println("╚═╝  ╚═╝╚══════╝╚══════╝╚═╝         ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝ ╚═════╝ ");
            System.out.println("Silakan pilih menu yang tersedia :");
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

    public static boolean isAllSimDead() {
        // cek jika arraylist kosong
        if (listSim.isEmpty()) {
            return true;
        }
        return false;
    }

    public static void exit() {
        System.out.println("Terima kasih telah memainkan Simplicity");
        System.exit(0);
    }

    // Method digunakan di class AllData.java
    public static ArrayList<Sim> getListSim() {
        return listSim;
    }

    // Method digunakan di class AllData.java
    public static void setListSim(ArrayList<Sim> sims) {
        listSim = sims;
    }

    // Method digunakan di class AllData.java
    public static Sim getCurrentSim() {
        return currentSim;
    }

    // Method digunakan di class AllData.java
    public static void setCurrentSim(Sim sim) {
        currentSim = sim;
    }

    public static void clearTerminal() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
