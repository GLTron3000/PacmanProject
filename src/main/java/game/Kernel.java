package game;

import entity.Entity;
import entity.Pacman;
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

    public void step(){
        Pacman pacman = (Pacman) getPacman();
        pacman.move();
    }

    public Entity getPacman(){
        for(Entity entity : entities){
            if(entity instanceof Pacman) return entity;
        }
        return null;
    }
    
}
