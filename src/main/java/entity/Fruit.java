package entity;

import game.Kernel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;


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
        try {
            Image image = new Image(new FileInputStream("assets/Pickable/Fruit.png"));
            gc.save();
            gc.drawImage(image, x, y, size, size);
            gc.restore();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Fruit.class.getName()).log(Level.SEVERE, null, ex);
        }
        //gc.setFill(Color.ORANGE);
        //gc.fillRect(x, y, size, size);
    }
    
}
