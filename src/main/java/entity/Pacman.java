package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pacman extends Entity {
    public Direction direction;

    public Pacman(double x, double y) {
        super(x, y);
        direction = Direction.STOP;
        type="Pacman";
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
            case UP: y-=1.0; break;
            case DOWN: y+=1.0; break;
            case LEFT: x-=1.0; break;
            case RIGHT: x+=1.0; break;
            default: break;
        }
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, 50, 50);
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
}
