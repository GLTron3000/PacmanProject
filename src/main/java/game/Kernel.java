package game;

import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import static game.GameState.*;
import static entity.Direction.*;

import game.CollisionEngine.*;
import ia.IA;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public Engine engine;
    public CollideBehavior collBeha;
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
        engine = new CollisionEngineRectangle();
        collBeha=new CollideBehaviorClassic();
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
        
        if(engine.outOfBoard(pacman , canvasHeight, canvasWidth)) pacman.stop();
        
        collide();
        
        checkVictory();

        checkNextMove();

        pacman.move();
    }

    public void collide(){
        for(Wall w :walls ){
            for(Fantom f :fantoms ){
                if(engine.isCollide(w,f)){
                    f.stop();
                }
            }
            if(engine.isCollide(pacman,w)){
                //System.out.println("collision mur");
                collBeha.collideMovableWall(pacman,w);
            }
        }
        for(Fantom f: fantoms){
            if(engine.isCollide(pacman,f)){
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
            if(engine.isCollide(pacman,p)){
                p.onPick(this);
            }
        }
    }
    
    private void checkVictory(){
        if(pickables.isEmpty()) gameState = VICTORY;
    }

    private void checkNextMove(){
        if(pacman.nextDirection == STOP) return;

        Pacman nextPacman = new Pacman(pacman.getX(), pacman.getY());
        nextPacman.direction = pacman.nextDirection;      
        
        for(Wall w : walls){
            if(engine.isCollide(nextPacman, w)) collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.direction != STOP){
            pacman.nextDirection();
        }
    }
}
