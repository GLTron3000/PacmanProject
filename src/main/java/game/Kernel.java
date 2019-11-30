package game;

import entity.*;
import entity.Decorator.Fantom.FantomBackToLobby;

import static game.GameState.*;

import game.CollisionEngine.*;
import ia.RandomAI;
import ia.SimpleAI;
import ia.SmartAI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Kernel {
    public Engine engine;
    public CollideBehavior collBeha;
    public OutOfBoardBehavior OOBBeha;
    
    public MovablePacman pacman;
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
        
        computeMove();
        
        collide();
        
        pacman.checkNextMove(this);
        pacman.move();
        moveFantoms();
        
        checkVictory();
    }

    public void collide(){
        for(Wall w :walls ){
            for(Movable f :fantoms ) if(engine.isCollide(w,f)) collBeha.collideMovableWall(f,w);
            if(engine.isCollide(pacman,w))collBeha.collideMovableWall(pacman,w);
        }
        for(MovableFantom f: fantoms){
            if(engine.isCollide(pacman,f)){
                if(f.getState() == Fantom.FantomState.NORMAL) playerCatched();
                if(f.getState() == Fantom.FantomState.KILLABLE) fantomCatched(f);
            }
        }
        for(Pickable p : pickables){
            if(engine.isCollide(pacman,p)) p.onPick(this);
        }
    }
    
    private void playerCatched(){
        timer = 120;
        pacman.setLife(pacman.getLife()-1);
        pacman.stop();
        if (pacman.getLife() == 0) {
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
        
        setFantomIA();
    }
    
    private void fantomCatched(MovableFantom f) {
        fantoms.remove(f);
        fantoms.add(new FantomBackToLobby(f.removeDecorator()));
        
        //renvoie les fantômes à leur position initiale
        f.setX(f.initX);
        f.setY(f.initY);
    }
    
    private void checkVictory(){
        if(timer <= 0) playerCatched();
        if(pickables.isEmpty()) gameState = VICTORY;
    }
    
    private void computeMove(){
        fantoms.forEach(fantom -> fantom.computeMove(this));
    }
    
    private void moveFantoms(){
        fantoms.forEach(fantom -> fantom.move());
    }
    
    public void setFantomIA(){
        int counter = 0;
        for(MovableFantom fantom : fantoms){
            switch(counter){
                case 0: fantom.setIA(new SmartAI()); break;
                case 1: fantom.setIA(new SimpleAI()); break;
                default: fantom.setIA(new RandomAI()); break;
            }
            counter++;
        }
    }   
    
    public void activateWallPowerUp(){
        FruitWall.effect(this);
    }
    
    public void activateReductorPowerUp(){
        FruitRet.effect(this);
    }

}
