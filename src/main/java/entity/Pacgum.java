package entity;

import game.Kernel;
import javafx.scene.canvas.GraphicsContext;


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
        gc.drawImage(image, x, y, size, size);
        //gc.setFill(Color.WHITE);
        //gc.fillRect(x, y, size, size);
    }


    
}
