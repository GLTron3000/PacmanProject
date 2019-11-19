package game;

import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import ia.IA;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public CollisionEngine collisionEngine;
    
    public Pacman pacman;
    public ArrayList<Fantom> fantoms;
    public CopyOnWriteArrayList<Pickable> pickables;
    public ArrayList<Wall> walls;
    
    public ArrayList<IA> ias;
    
    double canvasWidth;
    double canvasHeight;

    int score;

    public Kernel(double canvasWidth, double canvasHeight) {
        collisionEngine = new CollisionEngine();
        
        fantoms = new ArrayList<>();
        pickables = new CopyOnWriteArrayList<>();
        walls = new ArrayList<>();
        ias = new ArrayList<>();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        score=0;
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
                if(f.fState== Fantom.FantomState.NORMAL) {
                    pacman.life -= 1;
                    pacman.stop();
                    if (pacman.life == 0) {
                        //TODO gestion du game over
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
                if(p.getType().equals("Fruit")) {
                    score = p.onPick(pickables,score);
                    //pickables.remove(p);
                    System.out.println(" Fruit mangé " + score);
                    //score+=50;
                    for(Fantom f: fantoms){
                        f.fState= Fantom.FantomState.KILLABLE;
                    }
                    //TODO Mettre un temps pour l'état "KILLABLE"
                }

                if(p.getType().equals("Pacgum")){
                    score = p.onPick(pickables,score);
                    //pickables.remove(p);
                    System.out.println(" pacgum mangé" + score);
                    //score+=10;
                }

            }
        }
    }
}
