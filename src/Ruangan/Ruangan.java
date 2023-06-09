package src.Ruangan;

import src.Item.Furniture.*;
import src.Sim.Sim;
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
    private Map<Sim, Point> daftarSim; // petanya sim, jadi simnya kaya ada di mana aja gitu

    // Generate pertama kali
    public Ruangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
        this.gridRuangan = new Furniture[6][6];
        this.daftarFurniture = new HashMap<Furniture, List<Point>>();
        this.daftarSim = new HashMap<Sim, Point>();
        // Masukan semua sim dan sim.getPosisiSim() ke daftarSim

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

    public void printDaftarSim() {
        System.out.println("-------------------------");
        System.out.printf("%-5s%-15s%-10s\n", "No.", "Nama Sim", "Point");
        System.out.println("-------------------------");
    
        int count = 1;
        for (Map.Entry<Sim, Point> entry : daftarSim.entrySet()) {
            Sim sim = entry.getKey();
            Point position = entry.getValue();
            System.out.printf("%-5d%-15s(%d,%d)\n", count, sim.getNama(), position.getX(), position.getY());
            count++;
        }
    
        System.out.println("-------------------------");
    }
    

    public void printDaftarFurnitureNameSemua() {
        List<String> furnitureNames = new ArrayList<>();
        for (Map.Entry<Furniture, List<Point>> entry : daftarFurniture.entrySet()) {
            Furniture furniture = entry.getKey();
            List<Point> furniturePositions = entry.getValue();
            String furnitureName = furniture.getNama();

            if (!furniturePositions.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(furnitureName).append(" (");
                for (int i = 0; i < furniturePositions.size(); i++) {
                    Point position = furniturePositions.get(i);
                    sb.append("(").append(position.getX()).append(",").append(position.getY()).append(")");
                    if (i < furniturePositions.size() - 1) {
                        sb.append(", ");
                    }
                }
                sb.append(")");
                furnitureName = sb.toString();
            }

            furnitureNames.add(furnitureName);
        }
        System.out.println("Furniture Names:");
        System.out.println("-------------------------");
        System.out.printf("%-7s%-15s\n", "No.", "Name");
        System.out.println("-------------------------");

        Map<String, Integer> nameCountMap = new HashMap<>();
        for (int i = 0; i < furnitureNames.size(); i++) {
            String furnitureName = furnitureNames.get(i);
            if (nameCountMap.containsKey(furnitureName)) {
                int count = nameCountMap.get(furnitureName);
                count++;
                nameCountMap.put(furnitureName, count);
                furnitureName += " " + count;
            } else {
                nameCountMap.put(furnitureName, 1);
            }
            System.out.printf("%-7d%-15s\n", (i + 1), furnitureName);
        }

        System.out.println("-------------------------");
    }

    // UNTUK TITIK TERKECIL
    public void printDaftarFurnitureName() {
        System.out.println("Furniture Names:");
        System.out.println("-------------------------");
        System.out.printf("%-7s%-15s\n", "No.", "Name");
        System.out.println("-------------------------");

        int count = 1;
        for (Furniture furniture : daftarFurniture.keySet()) {
            List<Point> furniturePositions = daftarFurniture.get(furniture);
            String furnitureName;

            if (!furniturePositions.isEmpty()) {
                Point smallestPosition = furniturePositions.get(0);
                for (Point position : furniturePositions) {
                    if (position.getX() < smallestPosition.getX() ||
                            (position.getX() == smallestPosition.getX() && position.getY() < smallestPosition.getY())) {
                        smallestPosition = position;
                    }
                }
                furnitureName = furniture.getNama() + " (" + smallestPosition.getX() + ", " + smallestPosition.getY()
                        + ")";
            } else {
                furnitureName = furniture.getNama();
            }

            System.out.printf("%-7d%-15s\n", count, furnitureName);
            count++;
        }

        System.out.println("-------------------------");
    }

    // visualisasi furniture pada ruangan
    public void printRuangan() {
        System.out.println("Peta " + namaRuangan);
        System.out.println("-------------------------");

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 6; col++) {
                Object obj = gridRuangan[5 - row][col];

                if (obj instanceof Sim) {
                    Sim sim = (Sim) obj;
                    System.out.print(sim.getNama().charAt(0) + " ");
                } else if (obj instanceof Furniture) {
                    Furniture furniture = (Furniture) obj;
                    if (furniture.getNama().equals("Kasur King Size")) {
                        System.out.print("KBD ");
                    } else if (furniture.getNama().equals("Kasur Queen Size")) {
                        System.out.print("QBD ");
                    } else if (furniture.getNama().equals("Kasur Single")) {
                        System.out.print("SBD ");
                    } else if (furniture.getNama().equals("Kompor Listrik")) {
                        System.out.print("EST ");
                    } else if (furniture.getNama().equals("Kompor Gas")) {
                        System.out.print("GST ");
                    } else if (furniture.getNama().equals("Meja dan Kursi")) {
                        System.out.print("TNC ");
                    } else if (furniture.getNama().equals("Jam")) {
                        System.out.print("JAM ");
                    } else if (furniture.getNama().equals("Toilet")) {
                        System.out.print("TOI ");
                    } else if (furniture.getNama().equals("Komputer")) {
                        System.out.print("KOM ");
                    } else if (furniture.getNama().equals("Piano")) {
                        System.out.print("PIA ");
                    } else if (furniture.getNama().equals("Rak Buku")) {
                        System.out.print("RAK ");
                    } else if (furniture.getNama().equals("Sajadah")) {
                        System.out.print("SJD ");
                    } else if (furniture.getNama().equals("Shower")) {
                        System.out.print("SHW ");
                    } else if (furniture.getNama().equals("Teleskop")) {
                        System.out.print("TKP ");
                    } else if (furniture.getNama().equals("TV")) {
                        System.out.print("TLV ");
                    }
                } else {
                    System.out.print("--- ");
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
    }

    // visualisasi sim pada ruangan
    public void printSim() {
        System.out.println("Peta posisi sim :");
        System.out.println("-------------------------");

        String[][] ruangan = new String[6][6];

        for (String[] row : ruangan) {
            Arrays.fill(row, "---");
        }

        for (Map.Entry<Sim, Point> entry : daftarSim.entrySet()) {
            Sim sim = entry.getKey();
            Point simPosition = entry.getValue();
            int row = 5 - simPosition.getX();
            int col = simPosition.getY();
            String simName = sim.getNama();
            if (simName.length() > 3) {
                simName = simName.substring(0, 3);
            }
            ruangan[row][col] = simName.toUpperCase();
        }

        for (String[] row : ruangan) {
            for (String cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }

        System.out.println("-------------------------");
    }

    public void putSim(Sim sim, Point point) {
        daftarSim.put(sim, point);
        sim.setPosisiSim(point);
    }

    public void removeSim(Sim sim) {
        daftarSim.remove(sim);
    }

    public Map<Sim, Point> getDaftarSim() {
        return daftarSim;
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

    public boolean addFurniture(Furniture furniture, Point point) {
        Furniture lokasiFurniture = gridRuangan[point.getY()][point.getX()];
        if (lokasiFurniture == null) {
            int panjang = furniture.getPanjang();
            int lebar = furniture.getLebar();
            boolean isAvailable = true;
            for (int i = 0; i < panjang; i++) {
                for (int j = 0; j < lebar; j++) {
                    if (gridRuangan[point.getY() + j][point.getX() + i] != null) {
                        isAvailable = false;
                    }
                }
            }

            if (isAvailable) {
                List<Point> listPoint = new ArrayList<Point>();
                for (int i = 0; i < panjang; i++) {
                    for (int j = 0; j < lebar; j++) {
                        gridRuangan[point.getY() + j][point.getX() + i] = furniture;
                        listPoint.add(new Point(point.getX() + i, point.getY() + j));
                    }
                }
                daftarFurniture.put(furniture, listPoint);
            } else {
                System.out.println("Tidak bisa menambahkan furniture di koordinat tersebut.");
                return false;
            }
        } else {
            System.out.println("Tidak bisa menambahkan furniture di koordinat tersebut.");
            return false;
        }
        return true;
    }

    public boolean moveFurniture(Furniture furniture, Point point) {
        List<Point> listPoint = daftarFurniture.get(furniture);
        if (listPoint != null) {
            int panjang = furniture.getPanjang();
            int lebar = furniture.getLebar();
            boolean isAvailable = true;
            for (int i = 0; i < panjang; i++) {
                for (int j = 0; j < lebar; j++) {
                    if (gridRuangan[point.getY() + j][point.getX() + i] != null) {
                        isAvailable = false;
                    }
                }
            }

            if (isAvailable) {
                List<Point> newListPoint = new ArrayList<Point>();
                // Remove the furniture's previous position from the daftarFurniture map
                daftarFurniture.remove(furniture);
                for (Point p : listPoint) {
                    gridRuangan[p.getY()][p.getX()] = null;
                }
                for (int i = 0; i < panjang; i++) {
                    for (int j = 0; j < lebar; j++) {
                        gridRuangan[point.getY() + j][point.getX() + i] = furniture;
                        newListPoint.add(new Point(point.getX() + i, point.getY() + j));
                    }
                }
                // Add the furniture's new position to the daftarFurniture map
                daftarFurniture.put(furniture, newListPoint);
            } else {
                System.out.println("Tidak bisa memindahkan " + furniture.getNama()
                        + " ke koordinat tersebut karena sudah ada furniture lain, yaitu "
                        + gridRuangan[point.getX()][point.getY()].getNama() + ".");
                return false;
            }
        } else {
            System.out.println("Tidak ada furniture dengan nama tersebut.");
            return false;
        }
        return true;
    }

    private List<Point> getPositionsOfFurniture(Furniture furniture) {
        List<Point> positions = new ArrayList<>();
        for (Map.Entry<Furniture, List<Point>> entry : daftarFurniture.entrySet()) {
            if (entry.getKey().equals(furniture)) {
                positions.addAll(entry.getValue());
            }
        }
        return positions;
    }

    // cek apakah ada furniture dengan nama yang sama
    private boolean isFurnitureNameExist(String furnitureName) {
        for (Furniture furniture : daftarFurniture.keySet()) {
            if (furniture.getNama().equals(furnitureName)) {
                return true;
            }
        }
        return false;
    }

    // jika ada nama furniture yang sama, user akan disuruh untuk memilih furniture
    // yang mana yang ingin dipilih, jika tidak langsung mereturn furniture tersebut
    public Furniture selectFurniture(String furnitureName) {
        if (isFurnitureNameExist(furnitureName)) {
            List<Furniture> furnitureList = new ArrayList<>();
            for (Furniture furniture : daftarFurniture.keySet()) {
                if (furniture.getNama().equals(furnitureName)) {
                    furnitureList.add(furniture);
                }
            }
            if (furnitureList.size() > 1) {
                Scanner scanner = new Scanner(System.in);
                System.out.println(
                        "Ada beberapa furnitur dengan nama yang sama. Silakan pilih salah satu furnitur berikut:");
                for (int i = 0; i < furnitureList.size(); i++) {
                    System.out.println((i + 1) + ". " + furnitureList.get(i).getNama() + " pada titik "
                            + daftarFurniture.get(furnitureList.get(i)).get(0).getX() + ", "
                            + daftarFurniture.get(furnitureList.get(i)).get(0).getY() + ".");
                }
                System.out.print("Your choice: ");
                int choice = scanner.nextInt();

                // terus minta inputan sampai benar bahkan jika memasukkan inputan char/string
                while (choice < 1 || choice > furnitureList.size()) {
                    System.out.println(
                            "Inputan salah. Silakan masukkan angka antara 1 sampai " + furnitureList.size() + ".");
                    System.out.print("Your choice: ");
                    choice = scanner.nextInt();
                }
                return furnitureList.get(choice - 1);
            } else {
                return furnitureList.get(0);
            }
        } else {
            return null;
        }
    }

    public void moveSimToFurniture(Sim sim, String furnitureName) {
        Furniture furniture = selectFurniture(furnitureName);

        if (furniture == null) {
            System.out.println("Tidak ada furnitur dengan nama tersebut.");
            return;
        }

        if (daftarFurniture.containsKey(furniture)) {
            List<Point> furniturePositions = daftarFurniture.get(furniture);
            if (!furniturePositions.isEmpty()) { // artinya
                // Remove Sim's current position
                // Point currentSimPosition = daftarSim.get(sim);
                // gridRuangan[currentSimPosition.getX()][currentSimPosition.getY()] = null;

                // Move Sim to a position on the furniture
                Point targetPosition = selectPositionOnFurniture(furniture); // kalo bs milih : furniturePositions
                // cek apakah ada sim di posisi tersebut
                for (Map.Entry<Sim, Point> simEntry : daftarSim.entrySet()) {
                    if (simEntry.getValue().equals(targetPosition)) {
                        // check if sim name is the same as the sim name that is going to be moved
                        if (simEntry.getKey().getNama().equals(sim.getNama())) {
                            System.out.println(
                                    "Tidak bisa memindahkan sim ke posisi tersebut karena sim sudah berada di titik tersebut.");
                            return;
                        } else {
                            System.out.println(
                                    "Tidak bisa memindahkan sim ke posisi tersebut karena sudah ada sim lain.");
                            return;
                        }
                    }
                }

                putSim(sim, targetPosition);

                // Print success message
                System.out.println("Sim berhasil dipindahkan ke " + furniture.getNama() + " pada titik "
                        + "(" + targetPosition.getX() + ", " + targetPosition.getY() + ").");
            } else {
                System.out.println("Tidak ada posisi yang tersedia pada furniture.");
            }
        } else {
            System.out.println("Furnitur tidak ada di dalam ruangan.");
        }
    }

    public boolean isSimOnFurniture(Sim sim, Class<? extends Furniture> furnitureClass) {
        for (Map.Entry<Sim, Point> simEntry : daftarSim.entrySet()) {
            if (simEntry.getKey().equals(sim)) {
                for (Map.Entry<Furniture, List<Point>> furnitureEntry : daftarFurniture.entrySet()) {
                    if (furnitureClass.isInstance(furnitureEntry.getKey())) {
                        for (Point point : furnitureEntry.getValue()) {
                            if ((point.getX() == simEntry.getValue().getX())
                                    && (point.getY() == simEntry.getValue().getY())) { // point.equals(simEntry.getValue())
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    // // YANG DIPILIHIN TERDEKAT
    public Point selectPositionOnFurniture(Furniture furniture) {
        List<Point> furniturePositions = getPositionsOfFurniture(furniture);

        if (furniturePositions.isEmpty()) {
            return null;
        }

        // Find the position with the smallest X and Y coordinates
        Point nearestPosition = furniturePositions.get(0);
        for (Point position : furniturePositions) {
            if (position.getX() < nearestPosition.getX() ||
                    (position.getX() == nearestPosition.getX() && position.getY() < nearestPosition.getY())) {
                nearestPosition = position;
            }
        }

        return nearestPosition;
    }
}