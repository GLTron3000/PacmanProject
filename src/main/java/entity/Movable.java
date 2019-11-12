package entity;

import static entity.Direction.*;


public abstract class Movable extends Entity{
    public Direction direction;
    double speed;

    public Movable(double x, double y, double speed) {
        super(x, y);
        direction = STOP;
        this.speed = speed;
    }
    
    public void goUp(){
        direction = Direction.UP;
    }

    public void goDown(){
        direction = Direction.DOWN;
    }

    public void goLeft(){
        direction = Direction.LEFT;
    }

    public void goRight(){
        direction = Direction.RIGHT;
    }

    public void stop(){
        direction = Direction.STOP;
    }

    public void move(){        
        switch (direction){
            case UP: y-=speed; break;
            case DOWN: y+=speed; break;
            case LEFT: x-=speed; break;
            case RIGHT: x+=speed; break;
            default: break;
        }
    }
}
