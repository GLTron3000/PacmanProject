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
                if(collisionEngine.isCollideRec(w,f)){
                    f.stop();
                }
            }
            if(collisionEngine.isCollideRec(pacman,w)){
                //System.out.println("collision mur");
                collisionEngine.collideMovableWall(pacman,w);
            }
        }
        for(Fantom f: fantoms){
            if(collisionEngine.isCollide(pacman,f)){
                collisionEngine.collidePacmanFantom(pacman);

            }
        }
        for(Pickable p : pickables){
            if(collisionEngine.isCollide(pacman,p)){

            }
        }
    }
}
