package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Entity;

import java.lang.reflect.Type;
import java.util.List;


class LoaderTest {

    @org.junit.jupiter.api.Test
    void loadlevel() {
        String path="test1";
        Loader loader = new Loader();
        List<Entity> entities= loader.loadlevel(path);
        System.out.print(entities);
    }
    @org.junit.jupiter.api.Test
    void deserialiazeListEntity(){
        String input ="[{\"x\":15.0,\"y\":16.0,\"type\":\"Wall\"},{\"x\":74.0,\"y\":56.0,\"type\":\"Fantom\"}," +
                "{\"x\":55.0,\"y\":22.0,\"type\":\"Wall\"},{\"direction\":\"STOP\",\"x\":86.0,\"y\":80.0,\"type\":\"Pacman\"}]";


        Gson gson = new GsonBuilder().registerTypeAdapter(Entity.class, new EntityJsonDeserialize()).create();

        Type type = new TypeToken<List<Entity>>(){}.getType();

        List<Entity> list = gson.fromJson(input, type);

        for(Entity e : list) {
            System.out.println(e);
        }
    }
}