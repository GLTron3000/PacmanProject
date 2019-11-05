package game;

import entity.Entity;
import ia.IA;
import java.util.ArrayList;

public class Kernel {
    public CollisionEngine collisionEngine;
    public ArrayList<Entity> entities;
    public ArrayList<IA> ias;

    public Kernel() {
        collisionEngine = new CollisionEngine();
        entities = new ArrayList<>();
        ias = new ArrayList<>();
    }
    
    
}
