package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Fruit extends Pickable{

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
    }

    @Override
    public void onPick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(x, y, size, size);
    }
    
}
