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
            System.out.println("| Aktivitas    | Sisa waktu |");
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
}