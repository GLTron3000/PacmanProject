package entity;

import static entity.Direction.*;

public class Pacman extends Movable {
    public int life;

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
    
    public void nextDirection(){
        direction = nextDirection;
        nextDirection = STOP;
    }
}
