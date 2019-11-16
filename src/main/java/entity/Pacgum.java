package entity;

import JSON.LevelData;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Pacgum extends Pickable{

    LevelData data = new LevelData();
    public Pacgum(double x, double y) {
        super(x, y);
        type="Pacgum";
    }

    @Override
    public void onPick() {
        data.load("customLevel1.pml");
        int i = 0;
        int test = 0;
        while(test == 0)
        {
            if(data.pickables.get(i).x == this.x && data.pickables.get(i).y == this.y) {
                data.pickables.remove(i);
                test = 1;
            }
            else
                i++;
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(x, y, size, size);
    }
    
}
