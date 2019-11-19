package game;

import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import static game.GameState.*;
import ia.IA;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public CollisionEngine collisionEngine;
    public ArrayList<IA> ias;
    
    public Pacman pacman;
    public ArrayList<Fantom> fantoms;
    public CopyOnWriteArrayList<Pickable> pickables;
    public ArrayList<Wall> walls;
    
    public GameState gameState;
    
    double canvasWidth;
    double canvasHeight;

    public int score;

    public Kernel(double canvasWidth, double canvasHeight) {
        collisionEngine = new CollisionEngine();
        
        fantoms = new ArrayList<>();
        pickables = new CopyOnWriteArrayList<>();
        walls = new ArrayList<>();
        ias = new ArrayList<>();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        score=0;
        
        gameState = PLAY;
    }
    
    

    public void step(){
        
        if(collisionEngine.outOfBoard(pacman , canvasHeight, canvasWidth)) pacman.stop();
        
        collide();
        
        checkVictory();
                
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
                if(f.fState== Fantom.FantomState.NORMAL) {
                    pacman.life -= 1;
                    pacman.stop();
                    if (pacman.life == 0) {
                        gameState = GAMEOVER;
                    }
                    //renvoie pacman à sa position initiale
                    pacman.setX(pacman.initX);
                    pacman.setY(pacman.initY);

                    //renvoie les fantômes à leur position initiale
                    for (Fantom p : fantoms) {
                        p.setX(p.initX);
                        p.setY(p.initY);
                    }
                }
                if(f.fState == Fantom.FantomState.KILLABLE) {
                    f.fState=Fantom.FantomState.BACKTOLOBBY;
                }


            }
        }
        for(Pickable p : pickables){
            if(collisionEngine.isCollide(pacman,p)){
                p.onPick(this);
            }
        }
    }
    
    private void checkVictory(){
        if(pickables.isEmpty()) gameState = VICTORY;
    }
}
