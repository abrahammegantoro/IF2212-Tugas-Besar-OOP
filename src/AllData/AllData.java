package src.AllData;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;

import src.MainMenu.MainMenu;
import src.Sim.Sim;
import src.World.Time;
import src.World.World;

public class AllData {
    private ArrayList<Sim> listSim;
    private World world;
    private Time time;
    private Sim currentSim;

    public AllData(){
        listSim = MainMenu.getListSim();
        world = World.getInstance();
        time = Time.getInstance();
        currentSim = MainMenu.getCurrentSim();
    }

    public static void save(){
        Scanner in = new Scanner(System.in);
        String place = null;

        try {
            place = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Tuliskan nama file : ");
        String input = in.next();
        Path path = Paths.get(place + "\\file").resolve(input + ".json");
        save(path);

        System.out.println("Data berhasil disave.");
    }
    
    public static void save(Path path){
        AllData data = new AllData();

        Gson gson = new Gson();
        String json = gson.toJson(data);
        
        try {
            Files.write(path, json.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load(){
        Scanner in = new Scanner(System.in);
        String place = null;

        try {
            place = new File(".").getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print("Tuliskan nama file : ");
        String input = in.next();
        Path path = Paths.get(place + "\\file").resolve(input + ".json");
        load(path);
        System.out.println("Data berhasil diload.");
    }

    public static void load(Path path){
        Reader json = null;
        try {
            json = Files.newBufferedReader(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        AllData data = new Gson().fromJson(json, AllData.class);

        MainMenu.setListSim(data.listSim);
        World.setInstance(data.world);
        Time.setInstance(data.time);
        MainMenu.setCurrentSim(data.currentSim);
    }
}
