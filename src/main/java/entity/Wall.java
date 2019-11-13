package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Entity {

    public Wall(double x, double y) {
        super(x, y);
        type="Wall";
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(x, y, size, size);
    }

    @Override
    public String toString() {
        return "Wall{" +
                "x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
