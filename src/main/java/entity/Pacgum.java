package entity;

import game.Kernel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Pacgum extends Pickable{

    public Pacgum(double x, double y) {
        super(x, y);
        type="Pacgum";
    }

    @Override
    public int onPick(Kernel k) {
        k.pickables.remove(this);
        k.score+=10;
        return k.score;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, size, size);
    }


    
}
