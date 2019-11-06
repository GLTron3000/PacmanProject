package JSON;

import com.google.gson.Gson;
import entity.Entity;

import java.io.*;
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
            String str = new String(data, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String entities="";
        Gson gson = new Gson();
        result=gson.fromJson(entities,Entity[].class);
        return new ArrayList<>(Arrays.asList(result));
    }
}
