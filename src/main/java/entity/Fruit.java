package entity;

import game.Kernel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.CopyOnWriteArrayList;


public class Fruit extends Pickable{

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
    }


    @Override
    public void onPick(Kernel k) {
        k.pickables.remove(this);
        k.score+=50;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(x, y, size, size);
    }
    
}
