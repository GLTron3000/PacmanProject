package entity;

import game.Kernel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class Pacgum extends Pickable{

    public Pacgum(double x, double y) {
        super(x, y);
        type="Pacgum";
    }

    @Override
    public void onPick(Kernel k) {
        k.pickables.remove(this);
        k.score+=10;
    }

    @Override
    public void draw(GraphicsContext gc) {
        try {
            Image image = new Image(new FileInputStream("assets/Pickable/Pacgum.png"));
            gc.save();
            gc.drawImage(image, x, y, size, size);
            gc.restore();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fruit.class.getName()).log(Level.SEVERE, null, ex);
        }
        //gc.setFill(Color.WHITE);
        //gc.fillRect(x, y, size, size);
    }


    
}
