package src.World;

// import src.Sim.Sim;
// import java.util.ArrayList;
// import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Time {
    private static Time instance;
    private int currentTime;
    private Map<String, Integer> timeMap;

    private Time() {
        currentTime = 0;
        timeMap = new HashMap<>();
    }

    public static Time getInstance() {
        if (instance == null) {
            instance = new Time();
        }
        return instance;
    }

    public void setTimeMap(String key, int value) {
        timeMap.put(key, value);
    }

    public Map<String, Integer> getTimeMap() {
        return timeMap;
    }

    public synchronized int getCurrentTime() {
        return currentTime;
    }

    public synchronized int getTimeRemaining() {
        return 720 - (currentTime % 720);
    }

    public synchronized void getActivityTimeRemaining() {
        for (Map.Entry<String, Integer> entry : timeMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }

    public synchronized void incrementTime() {
        currentTime++;
        for (Map.Entry<String, Integer> entry : timeMap.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue() - 1;
            timeMap.put(key, value);
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
