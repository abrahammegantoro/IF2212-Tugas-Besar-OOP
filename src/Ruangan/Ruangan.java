package src.Ruangan;

import src.Item.Furniture.*;
import src.World.Point;
import java.util.*;

public class Ruangan {
    private String namaRuangan;
    private List<Furniture> daftarObject;
    private Furniture[][] grid;
    private Point lokasiRuangan;
    private Ruangan up;
    private Ruangan down;
    private Ruangan left;
    private Ruangan right;
    private int panjang = 6;
    private int lebar = 6;

    //Konstruktor
    public Ruangan(String namaRuangan, Point lokasiRuangan, Ruangan up, Ruangan down, Ruangan left, Ruangan right){
        this.namaRuangan = namaRuangan;
        this.lokasiRuangan = lokasiRuangan;
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;

    }

    public Ruangan(String namaRuangan, Point lokasiRuangan){
        this.namaRuangan = namaRuangan;
        this.lokasiRuangan = lokasiRuangan;
        this.grid = new Furniture[6][6];
        this.daftarObject = new ArrayList<>();
    }

    public Ruangan(String namaRuangan, int lebar, Point lokasiRuangan){
        this.namaRuangan = namaRuangan;
        this.lebar = lebar;
        this.grid = new Furniture[lebar][lebar];
        this.lokasiRuangan = lokasiRuangan;
        this.daftarObject = new ArrayList<>();
    }

    //getter
    public String getNamaRuangan(){
        return this.namaRuangan;
    }

    public List<Furniture> getDaftarObject(){
        return this.daftarObject;
    }

    // method untuk memasang atau menambah barang/object di dalam ruangan
    /**
    public void pasangBarang(Furniture object, Point lokasiObject){
        // periksa apakah lokasi input valid
        if(lokasiObject.x >= 0.0 && lokasiObject.x < lebar && lokasiObject.y >= 0.0 && lokasiObject.y < lebar){
            // lokasi input valid
            // periksa apakah lokasi object tersebut kosong
            if (grid[lokasiObject.x][lokasiObject.y] == null){
                // bila kosong
                grid[lokasiObject.x][lokasiObject.y] = object;
                object.setLocation(lokasiObject);
                daftarObject.add(object);
            }
            // bila lokasi tidak kosong
            else {
                System.out.println("Tidak bisa memasang furnitur dikarenakan lokasi input sudah terisi");
            }
        }
        // lokasi input invalid (out of bound)
        else {
            System.out.println("Tidak bisa menambahkan furnitur dikarenakan lokasi input invalid");
        }

    }
    

    // method untuk memindahkan barang yang sudah ada di dalam ruangan
    public void pindahBarang(Item, Point){

    }

    // method untuk editRuangan

    // method untuk getLokasi

    // method perpindahan posisi Sim agar Sim bisa berinteraksi dengan object/item di ruangan
    public void goToObject(Sim, Furniture){
        
    }

    **/
}
