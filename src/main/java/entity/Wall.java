package entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Wall extends Entity {

    public Wall(double x, double y) {
        super(x, y);
        type="Wall";
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(texture == null){
            gc.setFill(Color.GRAY);
            gc.fillRect(x, y, size, size);
        }else gc.drawImage(texture, x, y, size, size);        
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
