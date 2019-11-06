package entity;

import javafx.scene.canvas.GraphicsContext;

public class Pacman extends Entity {
    Direction direction;

    public Pacman(double x, double y) {
        super(x, y);
        direction = Direction.STOP;
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
            case UP: y--; break;
            case DOWN: y++; break;
            case LEFT: x++; break;
            case RIGHT: x--; break;
            default: break;
        }
    }

    @Override
    public void draw(GraphicsContext gc) {

    }
}
