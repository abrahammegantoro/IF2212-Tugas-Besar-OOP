package src.Ruangan;

import src.Item.Furniture.*;
import src.World.Point;
// import src.Sim.Sim;
import java.util.*;

public class Ruangan {
    private String namaRuangan;
    private Ruangan up;
    private Ruangan down;
    private Ruangan left;
    private Ruangan right;
    private Furniture[][] gridRuangan;
    private Map<Furniture, List<Point>> daftarFurniture; // keynya satu aja valuenya banyak (misal, kasur adaa di mana
                                                         // aja, dll)
    // Generate pertama kali
    public Ruangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
        this.gridRuangan = new Furniture[6][6];
        this.daftarFurniture = new HashMap<Furniture, List<Point>>();
        this.up = null;
        this.down = null;
        this.left = null;
        this.right = null;
    }

    public Ruangan(String namaRuangan, Ruangan up, Ruangan down, Ruangan left, Ruangan right) {
        this.namaRuangan = namaRuangan;
        this.gridRuangan = new Furniture[6][6];
        this.daftarFurniture = new HashMap<Furniture, List<Point>>();
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public Ruangan getUp() {
        return up;
    }

    public Ruangan getDown() {
        return down;
    }

    public Ruangan getLeft() {
        return left;
    }

    public Ruangan getRight() {
        return right;
    }

    public Furniture[][] getGrid() {
        return gridRuangan;
    }

    public Map<Furniture, List<Point>> getDaftarFurniture() {
        return daftarFurniture;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    public void setUp(Ruangan up) {
        this.up = up;
    }

    public void setDown(Ruangan down) {
        this.down = down;
    }

    public void setLeft(Ruangan left) {
        this.left = left;
    }

    public void setRight(Ruangan right) {
        this.right = right;
    }

    public void setGrid(Furniture[][] gridRuangan) {
        this.gridRuangan = gridRuangan;
    }

    public void setDaftarFurniture(Map<Furniture, List<Point>> daftarFurniture) {
        this.daftarFurniture = daftarFurniture;
    }

    public void addFurniture(Furniture furniture, Point point) {
        Furniture lokasiFurniture = gridRuangan[point.getX()][point.getY()];
        if (lokasiFurniture == null) {
            int panjang = furniture.getPanjang();
            int lebar = furniture.getLebar();
            boolean isAvailable = true;
            for (int i = 0; i < panjang; i++) {
                for (int j = 0; j < lebar; j++) {
                    if (gridRuangan[point.getX() + i][point.getY() + j] != null) {
                        isAvailable = false;
                    }
                }
            }

            if (isAvailable) {
                List<Point> listPoint = new ArrayList<Point>();
                for (int i = 0; i < panjang; i++) {
                    for (int j = 0; j < lebar; j++) {
                        gridRuangan[point.getX() + i][point.getY() + j] = furniture;
                        listPoint.add(new Point(point.getX() + i, point.getY() + j));
                    }
                }
                daftarFurniture.put(furniture, listPoint);
            } else {
                System.out.println("Tidak bisa menambahkan furniture di koordinat tersebut.");
            }
        } else {
            System.out.println("Tidak bisa menambahkan furniture di koordinat tersebut.");
        }
    }

    // driver ruangan
    public static void main(String[] args) {
        Ruangan ruangan = new Ruangan("Ruang Tamu");
        Furniture mejaKursi = new MejaKursi();
        ruangan.addFurniture(mejaKursi, new Point(0, 0));
        System.out.println(ruangan.getDaftarFurniture());
        System.out.println(ruangan.getGrid()[0][0]);
        System.out.println(ruangan.getGrid()[0][1]);
        System.out.println(ruangan.getGrid()[0][2]);
        System.out.println(ruangan.getGrid()[1][0]);
        System.out.println(ruangan.getGrid()[1][1]);
        System.out.println(ruangan.getGrid()[1][2]);
        System.out.println(ruangan.getGrid()[2][0]);
        System.out.println(ruangan.getGrid()[2][1]);
        System.out.println(ruangan.getGrid()[2][2]);
        System.out.println(ruangan.getGrid()[3][0]);
    }
}