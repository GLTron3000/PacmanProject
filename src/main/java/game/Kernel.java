package game;

import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import ia.IA;
import java.util.ArrayList;

public class Kernel {
    public CollisionEngine collisionEngine;
    
    public Pacman pacman;
    public ArrayList<Fantom> fantoms;
    public ArrayList<Pickable> pickables;
    public ArrayList<Wall> walls;
    public ArrayList<IA> ias;
    
    double canvasWidth;
    double canvasHeight;

    public Kernel(double canvasWidth, double canvasHeight) {
        collisionEngine = new CollisionEngine();
        
        fantoms = new ArrayList<>();
        pickables = new ArrayList<>();
        walls = new ArrayList<>();
        ias = new ArrayList<>();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
    }
    
    

    public void step(){
        if(collisionEngine.outOfBoard(pacman , canvasHeight, canvasWidth)) pacman.stop();
        collide();

        pacman.move();
    }
    public void collide(){
        for(Wall w :walls ){
            for(Fantom f :fantoms ){
                if(collisionEngine.isCollide(w,f)){
                    f.stop();
                }
            }
            if(collisionEngine.isCollide(pacman,w)){
                pacman.stop();
            }
        }
        for(Fantom f: fantoms){
            if(collisionEngine.isCollide(pacman,f)){

            }
        }
        for(Pickable p : pickables){
            if(collisionEngine.isCollide(pacman,p)){

            }
        }
    }
}
