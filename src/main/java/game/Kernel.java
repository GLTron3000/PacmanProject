package game;

import entity.*;
import entity.Decorator.Fantom.FantomBackToLobby;
import entity.Decorator.Fantom.FantomSizeReducer;
import entity.Decorator.Pacman.PacmanSizeReducer;
import entity.Decorator.Pacman.PacmanWallBreacher;

import static game.GameState.*;
import static entity.Direction.*;

import game.CollisionEngine.*;
import ia.RandomAI;
import ia.SmartAI;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
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

        collide();
        
        checkVictory();

        pacman.checkNextMove(this);
        pacman.move();
        
        //moveFantoms();
    }

    public void collide(){
        for(Wall w :walls ){
            for(Movable f :fantoms ){
                if(engine.isCollide(w,f)) f.stop();
            }
            if(engine.isCollide(pacman,w)){
                //System.out.println("COLLISION PACMAN");
                collBeha.collideMovableWall(pacman,w);
            }
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
    
    public void activateWallPowerUp(){
        int powerUpCost = 10;
        int duration = 10000;
        if(score - powerUpCost < 0) return;
        
        System.out.println("ADD EFFECT WALL BREACHER");
        pacman = new PacmanWallBreacher(pacman);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("REMOVE EFFECT WALL BREACHER");

                pacman = pacman.removeDecorator();
                //Detecter si pacman toujours dans mur => ded
                
                timer.cancel();
            }
        }, duration);
    }
    
    public void activateReductorPowerUp(){
        if(pacman.getPowerUpReductor() <= 0) return;
        
        pacman.setPowerUpReductor(pacman.getPowerUpReductor()-1);
        
        System.out.println("ADD EFFECT REDUCTOR");
        
        for(MovableFantom f: fantoms){
            fantoms.remove(f);
            fantoms.add(new FantomSizeReducer(f));
        }
        
        pacman = new PacmanSizeReducer(pacman);
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("REMOVE EFFECT REDUCTOR");
                for(MovableFantom f: fantoms){
                    fantoms.remove(f);
                    fantoms.add(f.removeDecorator());
                }
                
                pacman = pacman.removeDecorator();
                
                timer.cancel();
            }
        }, FruitRet.buffDuration);
    }

}
