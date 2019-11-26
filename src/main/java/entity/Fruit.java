package entity;

import entity.Decorator.FantomKillable;
import game.Kernel;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;



public class Fruit extends Pickable{
    public static long buffDuration=10000; //millsecond

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
    }


    @Override
    public void onPick(Kernel k) {
        for(MovableFantom f: k.fantoms){
            f.setKillable();
            k.fantoms.remove(f);
            k.fantoms.add(new FantomKillable(f));
        }
        k.pickables.remove(this);
        k.score+=50;
    }


    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(texture == null){
            gc.setFill(Color.ORANGE);
            gc.fillRect(x, y, size, size);
        }else{
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, size, size);
            gc.drawImage(texture, x, y, size, size);
        }    
    }
    
}
