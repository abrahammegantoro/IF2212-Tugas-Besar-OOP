package src.Ruangan;

import src.Item.Furniture.*;
import src.World.Point;
import src.Sim.Sim;
import java.util.*;

public class Ruangan {
    private String namaRuangan;
    private ArrayList<Furniture> daftarObject;
    private Furniture[][] grid;
    private Point lokasiRuangan;
    private Ruangan up;
    private Ruangan down;
    private Ruangan left;
    private Ruangan right;
    private int panjang = 6;
    private int lebar = 6;
    
    // Scanner
    Scanner input = new Scanner(System.in);

    //Konstruktor
    public Ruangan(String namaRuangan, Point lokasiRuangan, Ruangan up, Ruangan down, Ruangan left, Ruangan right){
        this.namaRuangan = namaRuangan;
        this.lokasiRuangan = lokasiRuangan;
        this.grid = new Furniture[this.lebar][this.lebar];
        this.daftarObject = new ArrayList<>();
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;

    }

    public Ruangan(String namaRuangan, Point lokasiRuangan){
        this.namaRuangan = namaRuangan;
        this.lokasiRuangan = lokasiRuangan;
        this.grid = new Furniture[this.lebar][this.lebar];
        this.daftarObject = new ArrayList<>();
    }

    //getter
    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    public List<Furniture> getDaftarObject(){
        return this.daftarObject;
    }

    public Furniture getObject (Point lokasiRuangan){
        for (Furniture object : this.daftarObject){
            if (object.getPoint().equals(lokasiRuangan)){
                return object;
            }
        }
        return null;
    }

    // method untuk memasang atau menambah barang/object di dalam ruangan
    public void pasangBarang(Furniture object, Point lokasiObject) throws RuanganException {
        int lebarObject = object.getLebar();
        int panjangObject = object.getPanjang();
        
        if ((lokasiObject.getX() + lebarObject) > lebar || (lokasiObject.getY() + panjangObject) > lebar) {
            throw new RuanganException("Objek di luar batas");
        }

        for (int i = lokasiObject.getX(); i < lokasiObject.getX() + lebarObject; i++) {
            for (int j = lokasiObject.getY(); j < lokasiObject.getY() + panjangObject; j++) {
                if (grid[i][j] != null) {
                    throw new RuanganException("Collision detected");
                }
            }
        }

        // No collision
        for (int i = lokasiObject.getX(); i < lokasiObject.getX() + lebarObject; i++) {
            for (int j = lokasiObject.getY(); j < lokasiObject.getY() + panjangObject; j++) {
                grid[i][j] = object;
            }
        }

        object.setlokasiObject(lokasiObject);

        daftarObject.add(object);

    }
    
    private void pasangBarang(Furniture object) throws RuanganException {
        int x,y;
        System.out.printf("\nMasukkan x: ");
        x = input.nextInt();
        System.out.printf("\nMasukkan y: ");
        y = input.nextInt();

        Point lokasiObject = new Point(x, y);

        int lebarObject = object.getLebar();
        int panjangObject = object.getPanjang();
        

        if (lokasiObject.getX() + lebarObject > lebar || lokasiObject.getY() + panjangObject > lebar) {
            throw new RuanganException("Objek di luar batas");
        }

        for (int i = lokasiObject.getX(); i < lokasiObject.getX() + lebarObject; i++) {
            for (int j = lokasiObject.getY(); j < lokasiObject.getY() + panjangObject; j++) {
                if (grid[i][j] != null) {
                    throw new RuanganException("Collision detected");
                }
            }
        }

        // No collision
        for (int i = lokasiObject.getX(); i < lokasiObject.getX() + lebarObject; i++) {
            for (int j = lokasiObject.getY(); j < lokasiObject.getY() + panjangObject; j++) {
                grid[i][j] = object;
            }
        }

        object.setLocation(lokasiObject);

        daftarObject.add(object);
    }

    private void removeObject(Furniture object) {
        Point lokasiObject = object.getPoint();
        int lebarObject = object.getLebar();
        int panjangObject = object.getPanjang();
    
        for (int i = lokasiObject.getX(); i < lokasiObject.getX() + lebarObject; i++) {
            for (int j = location.getY(); j < location.getY() + panjangObject; j++) {
                grid[i][j] = null;
            }
        }
    
        daftarObject.remove(object);
    }

    // method untuk editRuangan
    public void editRuangan(Sim sim) {
        printRuangan();
        System.out.printf("\nPilih aksi:\n1. Letakkan Objek\n2. Pindahkan Objek\n3. Ambil Objek\nNomor aksi:");
        int x = input.nextInt();
        while ((x < 1) || (x > 3)) {
            System.out.printf("\n\nSilahkan ulangi input: ");
            x = input.nextInt();
        }
    
        // Memilih aksi
        switch (x) {
            case 1:
                // Menambahkan objek
                aksiTambahObject(sim);
                break;
            case 2:
                // Memindahkan objek
                aksiPindahObject(sim);
                break;
            case 3:
                // Menghapus objek
                aksiHapusObject(sim);
                break;
        }

        private void aksiTambahObject(Sim sim) {
            Furniture currentFurniture = sim.getInventory().pilihFurniture();
            if (currentFurniture != null) {
                try {
                    addObject(currentFurniture);
                } catch (RuanganException e) {
                    System.out.println("Penempatan furnitur gagal: " + e.getMessage());
                    sim.getInventory().addItem((Item) currentFurniture);
                    System.out.println("Furnitur telah diletakkan kembali ke inventory Sim");
                }
            }
        }
    
        private void aksiPindahObject(Sim sim) {
            if (daftarObject.isEmpty()) {
                System.out.println("\nRuang ini tidak mempunyai furnitur");
                return;
            }
            Furniture currentFurniture = null;
            int idx = -1;
            int count = 0;
            String inputNamaFurniture = null;
    
            while (count == 0){
                System.out.printf("\nMasukkan nama furnitur: ");
                inputNamaFurniture = input.nextLine().trim();
                for (Furniture furniture : daftarObject){
                    if (furniture.getNama().equals(inputNamaFurniture)){
                        idx = daftarObject.indexOf(furniture);
                        count++;
                    }
                }
                if (count ==0 ){
                    System.out.println("\nNama furnitur tidak valid");
                }
            }
    
            if (count == 1){
                currentFurniture = daftarObject.get(idx);
                removeObject(daftarObject.get(idx));
            } else {
                System.out.println("Terdapat " + count + " " + inputNamaFurniture + " di ruangan ini");
                count = 1;
                for (Furniture furniture : daftarObject){
                    if (furniture.getNama().equals(inputNamaFurniture)){
                        System.out.println(furniture.getNama() + " " + count + ": " + furniture.getPoint().displayPoint());
                    }
                }
                    
                int i, j;
                boolean found = false;
                    
                while (!found){
                    System.out.println("\nMasukkan koordinat furnitur yang ingin dipindahkan");
                    System.out.printf("\nX: ");
                    i = scanner.nextInt();
                    System.out.printf("\nY: ");
                    j = scanner.nextInt();
                    for (Furniture furniture : daftarObject){
                        if (furniture.getPoint().equals(i, j)){
                            found = true;
                            currentFurniture = furniture;
                            removeObject(furniture);
                            break;
                        }
                    }
                    if (!found){
                        System.out.println("\nKoordinat tidak valid");
                    }
                }
            }
    
            try {
                this.addObject(currentFurniture);
            } catch (RuanganException e) {
                System.out.println("Penempatan furnitur gagal: " + e.getMessage());
                sim.getInventory().addItem((Item) currentFurniture);
                System.out.println("Furnitur telah diletakkan kembali ke inventory sim");
            }
        }
    
        private void aksiHapusObject(Sim sim) {
            if (daftarObject.isEmpty()) {
                System.out.println("\nRuang ini tidak mempunyai furnitur");
                return;
            }
            Furniture currentFurniture = null;
            int idx = -1;
            int count = 0;
            String inputNamaFurniture = null;
    
            while (count == 0){
                System.out.printf("\nMasukkan nama furnitur: ");
                inputNamaFurniture = input.nextLine().trim();
                for (Furniture furniture : daftarObject){
                    if (furniture.getName().equals(inputNamaFurniture)){
                        idx = daftarObject.indexOf(furniture);
                        count++;
                    }
                }
                if (count == 0 ){
                    System.out.println("\nNama furnitur tidak valid");
                }
            }
    
            if (count == 1){
                currentFurniture = daftarObject.get(idx);
                removeObject(daftarObject.get(idx));
            } else {
                System.out.println("Terdapat " + count + " " + inputNamaFurniture + " di ruangan ini");
                count = 1;
                for (Furniture furniture : daftarObject){
                    if (furniture.getName().equals(inputNamaFurniture)){
                        System.out.println(furniture.getName() + " " + count + ": " + furniture.getPoint().displayPoint());
                    }
                }
                    
                int i, j;
                boolean found = false;
    
                while (!found){
                    System.out.println("\nMasukkan koordinat furnitur yang ingin dimasukkan ke inventory");
                    System.out.printf("\nX: ");
                    i = scanner.nextInt();
                    System.out.printf("\nY: ");
                    j = scanner.nextInt();
                    for (Furniture furniture : daftarObject){    
                        if (furniture.getPoint().equals(i, j)){
                            found = true;
                            currentFurniture = furniture;
                            removeObject(furniture);
                        }
                    }
                }
            }
        }
    }

}
