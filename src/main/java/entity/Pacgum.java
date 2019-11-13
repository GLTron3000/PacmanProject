package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Pacgum extends Pickable{

    public Pacgum(double x, double y) {
        super(x, y);
    }

    @Override
    public void onPick() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, size, size);
    }
    
}
