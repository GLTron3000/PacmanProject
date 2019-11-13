package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pacman extends Movable {

    public Pacman(double x, double y) {
        super(x, y, 0.5);
        direction = Direction.STOP;
        type="Pacman";
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
