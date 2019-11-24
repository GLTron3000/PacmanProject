package entity;

import game.Kernel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(texture == null){
            gc.setFill(Color.WHITE);
            gc.fillRect(x, y, size, size);
        }else gc.drawImage(texture, x, y, size, size);    
    }


    
}
