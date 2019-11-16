package JSON;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import entity.Entity;
import entity.Fantom;
import entity.Pacman;
import entity.Wall;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Loader {
    public List<Entity> loadlevel(String path) {
        Entity[] result;
        File file = new File(path);

        try {
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fis.read(data);
            fis.close();
            String input = new String(data, StandardCharsets.UTF_8);

            Gson gson = new GsonBuilder().registerTypeAdapter(Entity.class, new EntityJsonDeserialize()).create();
            Type type = new TypeToken<List<Entity>>(){}.getType();

            return gson.fromJson(input, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }



};
