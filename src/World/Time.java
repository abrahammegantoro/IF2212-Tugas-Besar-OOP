package src.World;

import java.util.Map;

import src.Item.Item;
import src.Rumah.Rumah;

import java.util.HashMap;

public class Time {
    private static Time instance;
    private int currentTime;
    private int currentDay;
    private Map<Item, Integer> timeMapBeliBarang;
    private Map<Rumah, Integer> timeMapUpgradeRumah;

    private Time() {
        currentTime = 0;
        currentDay = 1;
        timeMapBeliBarang = new HashMap<>();
        timeMapUpgradeRumah = new HashMap<>();
    }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    //Method yang digunakan untuk set Time ketika load file
    public static void setInstance(Time time){
        instance = time;
    }

    public void setTimeMapBeliBarang(Item key, int value) {
        timeMapBeliBarang.put(key, value);
    }

    public void setTimeMapUpgradeRumah(Rumah key, int value) {
        timeMapUpgradeRumah.put(key, value);
    }

    public void removeTimeMapBeliBarang(Item key) {
        timeMapBeliBarang.remove(key);
    }

    public void removeTimeMapUpgradeRumah(Rumah key) {
        timeMapUpgradeRumah.remove(key);
    }

    public Map<Item, Integer> getTimeMapBeliBarang() {
        return timeMapBeliBarang;
    }

    public Map<Rumah, Integer> getTimeMapUpgradeRumah() {
        return timeMapUpgradeRumah;
    }

    public synchronized int getCurrentTime() {
        return currentTime;
    }
    public synchronized int getDayTimeRemaining(){
        return 720 - (currentTime % 720);
    }

    public synchronized int getCurrentDay() {
        return currentDay;
    }
    
    public synchronized String getTimeRemaining() {
        // return 720 - (currentTime % 720);
        int timeRemainingSeconds = 720 - (currentTime % 720);
        int minutes = timeRemainingSeconds / 60;
        int seconds = timeRemainingSeconds % 60;
        return String.format("\nDay %02d\nSisa Waktu %02d:%02d", currentDay, minutes, seconds);
    }

    public synchronized void getActivityTimeRemaining() {
        // kalau map kosong
        if (timeMapBeliBarang.isEmpty() && timeMapUpgradeRumah.isEmpty()) {
            System.out.println("\nTidak ada aktivitas yang ditunggu\n");
        } else {
            System.out.println("------------------------------");
            System.out.println("| Aktivitas     | Sisa waktu |");
            System.out.println("------------------------------");
            for (Map.Entry<Item, Integer> entry : timeMapBeliBarang.entrySet()) {
                Item key = entry.getKey();
                Integer value = entry.getValue();
                System.out.printf("| %-13s | %02d:%02d      |\n", "Beli " + key.getNama(), value / 60, value % 60);
            }
            for (Map.Entry<Rumah, Integer> entry : timeMapUpgradeRumah.entrySet()) {
                Rumah key = entry.getKey();
                Integer value = entry.getValue();
                System.out.printf("| %-13s| %02d:%02d      |\n", key.getNamaRumah(), value / 60, value % 60);
            }
        }
    }

    public synchronized void incrementTime() {
        currentTime++;
        if (currentTime % 720 == 0) {
            currentDay++;
            currentTime = 0;
        }
    }

    // public synchronized void consumeTime(int duration) {
    // while (duration > 0) {
    // try {
    // Thread.sleep(1000);
    // incrementTime();
    // for (Map.Entry<String, Integer> entry : timeMap.entrySet()) {
    // String key = entry.getKey();
    // Integer value = entry.getValue() - 1;
    // timeMap.put(key, value);
    // }
    // duration--;
    // } catch (InterruptedException e) {
    // System.out.println("Thread interrupted");
    // }
    // }
    // }
}

// public class Time {
// private long waktu;
// private long mulai = System.currentTimeMillis();
// private int hari;
// private static Time instance;
// private ArrayList<Sim> daftarSim;

// private Time(ArrayList<Sim> daftarSim){
// this.daftarSim = daftarSim;
// this.waktu = 0;
// this.hari = 0;
// }

// private Time(ArrayList<Sim> daftarSim, int hari, long waktu){
// this.daftarSim = daftarSim;
// this.hari = hari;
// this.waktu = waktu;
// }

// // Getter
// public long getWaktu(){
// return waktu;
// }
// public int getHari(){
// return hari;
// }
// public long getSisaWaktu(){
// return 720000-(waktu % 720000);
// }

// public synchronized static void init(ArrayList<Sim> daftarSim){
// if(instance == null){
// instance = new Time(daftarSim);
// }
// }

// public synchronized static void Time(ArrayList<Sim> daftarSim, int hari, long
// waktu){
// if(instance == null){
// instance = new Time(daftarSim, hari, waktu);
// }
// }

// public synchronized void setTime(){
// mulai = System.currentTimeMillis();
// }

// public synchronized void updateTime(){
// waktu = waktu + System.currentTimeMillis() - mulai;
// if(hari != (int) (waktu/720000)){
// hari = (int) (waktu/720000);
// for (Sim sim : daftarSim){
// sim.changeDayUpdate;
// }
// }
// }
// /**
// * public synchronized void updateTime(){
// boolean allIdle = true;
// for(Sim sim : daftarSim){
// if (!(sim.getAksi().isIdle())){
// allIdle = false;
// }
// }
// if (!allIdle){
// waktu = waktu + System.currentTimeMillis() - mulai;
// if (hari != (int) (waktu/720000)){
// hari = (int) (waktu/720000);
// for(Sim sim : daftarSim){
// sim.changeDayUpdate();
// }
// }

// }
// }
// * @return
// */

// }
