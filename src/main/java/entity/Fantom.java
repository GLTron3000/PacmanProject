package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Fantom extends Movable {
    String name;
    
    public Fantom(double x, double y, String name) {
        super(x, y, 0.1);
        type="Fantom";
        this.name = name;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.CYAN);
        gc.fillRect(x, y, size, size);
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
