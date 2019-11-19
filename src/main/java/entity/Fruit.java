package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.CopyOnWriteArrayList;


public class Fruit extends Pickable{

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
    }


    @Override
    public int onPick(CopyOnWriteArrayList<Pickable> pickables, int score) {
        int i = 0;
        int test = 0;
        while(test == 0)
        {
            if(pickables.get(i).x == this.x && pickables.get(i).y == this.y) {
                pickables.remove(i);
                score+=50;
                test = 1;
            }
            else
                i++;
        }
        return score;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.BEIGE);
        gc.fillRect(x, y, size, size);
    }
    
}
