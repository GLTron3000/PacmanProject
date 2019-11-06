package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
            case UP: y-=0.1; break;
            case DOWN: y+=0.1; break;
            case LEFT: x-=0.1; break;
            case RIGHT: x+=0.1; break;
            default: break;
        }
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.DARKGRAY);
        gc.fillRect(x, y, 50, 50);
    }
}
