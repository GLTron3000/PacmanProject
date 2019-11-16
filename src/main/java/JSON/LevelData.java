package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


import java.io.*;
import java.lang.reflect.Type;


import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import scenes.LevelBuilder;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LevelData {
    public Pacman pacman;
    public ArrayList<Fantom> fantoms;
    public ArrayList<Pickable> pickables;
    public ArrayList<Wall> walls;

    private String delimiter= "##&&##";

    public void save(Pacman pacman, List<Fantom> fantoms, List<Pickable> pickables, List<Wall> walls, String path){
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        String strPacman= gson.toJson(pacman);
        String strFantoms=gson.toJson(fantoms);
        String strPickables=gson.toJson(pickables);
        String strWall=gson.toJson(walls);
        File file = new File(path);
        FileWriter fr = null;
        BufferedWriter br = null;
        PrintWriter pr = null;
        try {
            fr = new FileWriter(file, true);
            br = new BufferedWriter(fr);
            pr = new PrintWriter(br);
            pr.print(strPacman);
            pr.print(delimiter);
            pr.print(strFantoms);
            pr.print(delimiter);
            pr.print(strPickables);
            pr.print(delimiter);
            pr.print(strWall);
        } catch (IOException ex) {
            Logger.getLogger(LevelBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                pr.close();
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    public void load(String path){
        File file = new File(path);
        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String input = new String(data, StandardCharsets.UTF_8);
            String list[]=input.split(delimiter);
            final Gson gson = new GsonBuilder().create();
            this.pacman=gson.fromJson(list[0],Pacman.class);
            Type listTypeFantom = new TypeToken<ArrayList<Fantom>>(){}.getType();
            this.fantoms=gson.fromJson(list[1],listTypeFantom);
            Type listTypeWalls = new TypeToken<ArrayList<Wall>>(){}.getType();
            this.walls=gson.fromJson(list[3],listTypeWalls);

            final Gson gsonPickable= new GsonBuilder().registerTypeAdapter(Pickable.class,new PickableJsonDeserialize()).create();
            Type listTypePickable = new TypeToken<ArrayList<Pickable>>(){}.getType();
            this.pickables=gsonPickable.fromJson(list[2],listTypePickable);

        }catch (IOException ex){
            Logger.getLogger(LevelBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
