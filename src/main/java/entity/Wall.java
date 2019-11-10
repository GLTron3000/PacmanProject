package entity;

import javafx.scene.canvas.GraphicsContext;

public class Wall extends Entity {

    public Wall(double x, double y) {
        super(x, y);
        type="Wall";
    }

    @Override
    public void draw(GraphicsContext gc) {

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
