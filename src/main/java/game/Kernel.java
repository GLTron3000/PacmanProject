package game;

import entity.Entity;
import entity.Pacman;
import ia.IA;
import java.util.ArrayList;

public class Kernel {
    public CollisionEngine collisionEngine;
    public ArrayList<Entity> entities;
    public ArrayList<IA> ias;
    double canvasWidth;
    double canvasHeight;

    public Kernel(double canvasWidth, double canvasHeight) {
        collisionEngine = new CollisionEngine();
        entities = new ArrayList<>();
        ias = new ArrayList<>();
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }
    
    

    public void step(){
        Pacman pacman = (Pacman) getPacman();
        if(collisionEngine.outOfBorad(pacman , canvasHeight, canvasWidth)) pacman.stop();
        pacman.move();
    }

    public Entity getPacman(){
        for(Entity entity : entities){
            if(entity instanceof Pacman) return entity;
        }
        return null;
    }
    
}
