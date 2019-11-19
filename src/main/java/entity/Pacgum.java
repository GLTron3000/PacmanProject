package entity;

import JSON.LevelData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.concurrent.CopyOnWriteArrayList;


public class Pacgum extends Pickable{

    LevelData data = new LevelData();
    public Pacgum(double x, double y) {
        super(x, y);
        type="Pacgum";
    }

    @Override
    public int onPick(CopyOnWriteArrayList<Pickable> pickables, int score) {
        int i = 0;
        int test = 0;
        while(test == 0)
        {
            if(pickables.get(i).x == this.x && pickables.get(i).y == this.y) {
                pickables.remove(i);
                score+=10;
                test = 1;
            }
            else
                i++;
        }
        return score;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, size, size);
    }
    
}
