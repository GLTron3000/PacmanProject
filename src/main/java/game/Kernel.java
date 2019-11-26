package game;

import entity.*;

import static game.GameState.*;
import static entity.Direction.*;

import entity.MovableFantom;
import game.CollisionEngine.*;
import ia.RandomAI;
import ia.SmartAI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public Engine engine;
    public CollideBehavior collBeha;
    public OutOfBoardBehavior OOBBeha;
    
    public Pacman pacman;
    public CopyOnWriteArrayList<MovableFantom> fantoms;
    public CopyOnWriteArrayList<Pickable> pickables;
    public List<Wall> walls;
    
    public GameState gameState;
    
    double canvasWidth;
    double canvasHeight;

    public int score;
    public int timer;

    public Kernel(double canvasWidth, double canvasHeight) {
        engine = new CollisionEngineRectangle();
        collBeha=new CollideBehaviorClassic();
        OOBBeha= new OutOfBoardWrapper(canvasHeight,canvasWidth);
        fantoms = new CopyOnWriteArrayList<>();
        pickables = new CopyOnWriteArrayList<>();
        walls = new ArrayList<>();
        
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
        
        moveFantoms();
    }

    public void collide(){
        for(Wall w :walls ){
            for(Movable f :fantoms ){
                if(engine.isCollide(w,f)){
                    f.stop();
                }
            }
            if(engine.isCollide(pacman,w)){
                //System.out.println("collision mur");
                collBeha.collideMovableWall(pacman,w);
            }
        }
        for(MovableFantom f: fantoms){
            if(engine.isCollide(pacman,f)){
                if(f.getState() == Fantom.FantomState.NORMAL) {
                    playerCatched();
                }
                if(f.getState() == Fantom.FantomState.KILLABLE) {
                    f.setBackToLobby();
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
        for (Movable p : fantoms) {
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
    
    private void moveFantoms(){
        fantoms.forEach(fantom -> {
            fantom.computeMove(this);
            fantom.move();
        });
    }
    
    public void setFantomIA(){
        int counter = 0;
        for(MovableFantom fantom : fantoms){
            switch(counter){
                case 0: fantom.setIA(new SmartAI()); break;
                case 1: fantom.setIA(new RandomAI(this)); break;
                default: fantom.setIA(new RandomAI(this)); break;
            }
            //counter++;
        }
    }
}
