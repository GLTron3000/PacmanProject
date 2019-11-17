package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pacman extends Movable {
    public int life;
    public double initX;
    public double initY;

    public Pacman(double x, double y) {
        super(x, y, 0.5);
        initX=x;
        initY=y;
        direction = Direction.STOP;
        type="Pacman";
        life = 3;
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.YELLOW);
        gc.fillRect(x, y, size, size);
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
