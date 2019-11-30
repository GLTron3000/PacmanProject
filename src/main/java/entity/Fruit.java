package entity;

import entity.Decorator.Fantom.FantomKillable;
import game.Kernel;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.canvas.GraphicsContext;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;



public class Fruit extends Pickable{
    public static long buffDuration=10000; //millsecond

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
        value = 50;
    }


    @Override
    public void onPick(Kernel k) {
        System.out.println("PICKED FRUIT");
        k.pickables.remove(this);
        k.score+=Fruit.value;
        
        effect(k);
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

    public static void effect(Kernel k) {
        for(MovableFantom f: k.fantoms){
            k.fantoms.remove(f);
            k.fantoms.add(new FantomKillable(f));
        }
        
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("REMOVE EFFECT FRUIT");
                for(MovableFantom f: k.fantoms){
                    k.fantoms.remove(f);
                    k.fantoms.add(f.removeDecorator());
                }
                timer.cancel();
            }
        }, Fruit.buffDuration);
    }
    
}
