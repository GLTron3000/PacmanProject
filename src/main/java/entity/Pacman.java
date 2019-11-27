package entity;

import static entity.Direction.*;
import game.Kernel;

public class Pacman extends MovablePacman {
    public int life;
    public int powerUpReductor;

    public Direction nextDirection;
    
    public Pacman(double x, double y) {
        super(x, y, 1);
        initX=x;
        initY=y;
        direction = STOP;
        nextDirection = STOP;
        type="Pacman";
        life = 3;
        frameNumber = 2;
        textureSize = 25;
    }

    @Override
    public String toString() {
        return "Pacman{" +
                "direction=" + direction +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void goUp(){
        nextDirection = UP;
    }

    @Override
    public void goDown(){
        nextDirection = DOWN;
    }

    @Override
    public void goLeft(){
        nextDirection = LEFT;
    }

    @Override
    public void goRight(){
        nextDirection = RIGHT;
    }

    @Override
    public int getLife() {
        return life;
    }

    @Override
    public Direction getNextDirection() {
        return nextDirection;
    }

    @Override
    public void setLife(int life) {
        this.life = life;
    }

    @Override
    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }
    
    @Override
    public void nextDirection(){
        direction = nextDirection;
        nextDirection = STOP;
    }

    @Override
    public MovablePacman removeDecorator() {
        return this;
    }

    @Override
    public int getPowerUpReductor() {
        return powerUpReductor;
    }

    @Override
    public void setPowerUpReductor(int powerUpReductor) {
        this.powerUpReductor = powerUpReductor;
    }  
    
    @Override
    public void checkNextMove(Kernel k){
        if(getNextDirection() == STOP) return;

        Pacman nextPacman = new Pacman(getX(), getY());
        nextPacman.size = getSize();
        nextPacman.direction = getNextDirection();
        
        for(Wall w : k.walls){
            if(k.engine.isCollide(nextPacman, w)) k.collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.direction != STOP) nextDirection();
    }
}
