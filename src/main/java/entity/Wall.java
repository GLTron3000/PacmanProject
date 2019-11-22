package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Wall extends Entity {

    public Wall(double x, double y) {
        super(x, y);
        type="Wall";
    }

    @Override
    public void draw(GraphicsContext gc) {
        try {
            Image image = new Image(new FileInputStream("assets/Pickable/Fruit.png"));
            gc.save();
            gc.drawImage(image, x, y, size, size);
            gc.restore();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fruit.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
