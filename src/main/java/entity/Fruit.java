package entity;

import game.Kernel;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;


public class Fruit extends Pickable{
    public static long buffDuration=10000; //millsecond

    public Fruit(double x, double y) {
        super(x, y);
        type="Fruit";
    }


    @Override
    public void onPick(Kernel k) {
        for(Fantom f: k.fantoms){
            f.fState= Fantom.FantomState.KILLABLE;
        }
        k.pickables.remove(this);
        k.score+=50;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("timer");
                for(Fantom f: k.fantoms){
                    f.fState= Fantom.FantomState.NORMAL;
                }
                timer.cancel();
            }
        },Fruit.buffDuration);
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(Color.ORANGE);
        gc.fillRect(x, y, size, size);
    }
    
}
