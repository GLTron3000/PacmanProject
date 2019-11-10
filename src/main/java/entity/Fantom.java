package entity;

import javafx.scene.canvas.GraphicsContext;

public class Fantom extends Entity {
    Direction direction;

    String name;
    public Fantom(double x, double y, String name) {
        super(x, y);
        type="Fantom";
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

    @Override
    public void draw(GraphicsContext gc) {

    }

    @Override
    public String toString() {
        return "Fantom{" +
                "direction=" + direction +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
