package src.World;

import entity.sim.Sim;
import java.util.ArrayList;
import java.util.List;

public class Time {
    private long waktu;
    private long mulai = System.currentTimeMillis();
    private int hari;
    private static Time instance;
    private ArrayList<Sim> daftarSim;

    private Time(ArrayList<Sim> daftarSim){
        this.daftarSim = daftarSim;
        this.waktu = 0;
        this.hari = 0;
    }

    private Time(ArrayList<Sim> daftarSim, int hari, long waktu){
        this.daftarSim = daftarSim;
        this.hari = hari;
        this.waktu = waktu;
    }

    // Getter
    public long getWaktu(){
        return waktu;
    }
    public int getHari(){
        return hari;
    }
    public long getSisaWaktu(){
        return 720000-(waktu % 720000);
    }


    public synchronized static void init(ArrayList<Sim> daftarSim){
        if(instance == null){
            instance = new Time(daftarSim);
        }
    }

    public synchronized static void Time(ArrayList<Sim> daftarSim, int hari, long waktu){
        if(instance == null){
            instance = new Time(daftarSim, hari, waktu);
        }
    }

    public synchronized void setTime(){
        mulai = System.currentTimeMillis();
    }

    public synchronized void updateTime(){
        waktu = waktu + System.currentTimeMillis() - mulai;
        if(hari != (int) (waktu/720000)){
            hari = (int) (waktu/720000);
            for (Sim sim : daftarSim){
                sim.changeDayUpdate;
            }
        }
    }
    /**
     * public synchronized void updateTime(){
        boolean allIdle = true;
        for(Sim sim : daftarSim){
            if (!(sim.getAksi().isIdle())){
                allIdle = false;
            }
        }
        if (!allIdle){
            waktu = waktu + System.currentTimeMillis() - mulai;
            if (hari != (int) (waktu/720000)){
                hari = (int) (waktu/720000);
                for(Sim sim : daftarSim){
                    sim.changeDayUpdate();
                }
            }

            
            
        }
    }
     * @return
     */

}

