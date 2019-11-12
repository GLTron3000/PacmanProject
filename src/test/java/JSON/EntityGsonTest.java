package JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Entity;
import entity.Fantom;
import entity.Pacman;
import entity.Wall;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EntityGsonTest {
    @org.junit.jupiter.api.Test
    void SerializeListEntity() {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        List<Entity> list = new ArrayList<>();
        list.add(new Wall(15,16));
        list.add(new Fantom(74,56,"inky"));
        list.add(new Wall(55,22));
        list.add(new Pacman(86,80));
        final String json = gson.toJson(list);
        String expected ="[{\"x\":15.0,\"y\":16.0,\"type\":\"Wall\"},{\"x\":74.0,\"y\":56.0,\"type\":\"Fantom\"}," +
                "{\"x\":55.0,\"y\":22.0,\"type\":\"Wall\"},{\"direction\":\"STOP\",\"x\":86.0,\"y\":80.0,\"type\":\"Pacman\"}]";
        assertEquals(expected,json);
        System.out.println(json);
    }
    @org.junit.jupiter.api.Test
    void SerializeWall() {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        Wall w1 =new Wall(15,16);
        Wall w2=new Wall(55,22);
        final String json = gson.toJson(w1);
        System.out.println(json);
    }

}