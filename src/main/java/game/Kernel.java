package game;

import entity.Fantom;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import static game.GameState.*;
import static entity.Direction.*;

import game.CollisionEngine.*;
import ia.IA;
import ia.RandomAI;
import ia.SmartAI;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public Engine engine;
    public CollideBehavior collBeha;
    public OutOfBoardBehavior OOBBeha;
    public ArrayList<IA> ias;

    public SmartAI ia;
    
    public Pacman pacman;
    public ArrayList<Fantom> fantoms;
    public CopyOnWriteArrayList<Pickable> pickables;
    public ArrayList<Wall> walls;
    
    public GameState gameState;
    
    double canvasWidth;
    double canvasHeight;

    public int score;
    public int timer;

    public Kernel(double canvasWidth, double canvasHeight) {
        engine = new CollisionEngineRectangle();
        collBeha=new CollideBehaviorClassic();
        OOBBeha= new OutOfBoardWrapper(canvasHeight,canvasWidth);
        fantoms = new ArrayList<>();
        pickables = new CopyOnWriteArrayList<>();
        walls = new ArrayList<>();
        ias = new ArrayList<>();
        ia = new SmartAI();
        
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;

        score = 0;
        timer = 120;
        
        gameState = PLAY;
    }
    
    

    public void step(){
        
        if(engine.outOfBoard(pacman , canvasHeight, canvasWidth)) OOBBeha.behavior(pacman);
        
        collide();
        
        checkVictory();

        checkNextMove();

        pacman.move();

        //fantoms.get(0).direction = ia.getMove(this);
        //fantoms.get(0).move();

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
                    playerCatched();
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
    
    private void playerCatched(){
        timer = 120;
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
    
    private void checkVictory(){
        if(timer <= 0) playerCatched();
        if(pickables.isEmpty()) gameState = VICTORY;
    }

    private void checkNextMove(){
        if(pacman.nextDirection == STOP) return;

        Pacman nextPacman = new Pacman(pacman.getX(), pacman.getY());
        nextPacman.direction = pacman.nextDirection;
        
        for(Wall w : walls){
            if(engine.isCollide(nextPacman, w))
                collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.direction != STOP){
            pacman.nextDirection();
        }

    }
}
