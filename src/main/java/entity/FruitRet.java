package entity;

import entity.Decorator.Fantom.FantomSizeReducer;
import entity.Decorator.Pacman.PacmanSizeReducer;
import game.Kernel;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FruitRet extends Pickable{
    public static long buffDuration=10000; //millsecond
    public static double sizeModifier = 0.5;

    public FruitRet(double x, double y) {
        super(x, y);
        type="FruitRet";
        value = 100;
    }

    @Override
    public void onPick(Kernel k) {
        System.out.println("PICKED FRUIT REDUCTOR");
        k.pacman.setPowerUpReductor(k.pacman.getPowerUpReductor()+1);
        k.pickables.remove(this);
        k.score+=FruitRet.value;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        if(texture == null){
            gc.setFill(Color.GREEN);
            gc.fillRect(x, y, size, size);
        }else{
            gc.setFill(Color.BLACK);
            gc.fillRect(x, y, size, size);
            gc.drawImage(texture, x, y, size, size);
        }
    }

    public static void effect(Kernel k) {
        if(k.pacman.getPowerUpReductor() <= 0) return;
        
        k.pacman.setPowerUpReductor(k.pacman.getPowerUpReductor()-1);
        
        System.out.println("ADD EFFECT REDUCTOR");
        
        for(MovableFantom f: k.fantoms){
            k.fantoms.remove(f);
            k.fantoms.add(new FantomSizeReducer(f));
        }
        
        k.pacman = new PacmanSizeReducer(k.pacman);
        
        Timer timerPowerUp = new Timer();
        timerPowerUp.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("REMOVE EFFECT REDUCTOR");
                for(MovableFantom f: k.fantoms){
                    k.fantoms.remove(f);
                    k.fantoms.add(f.removeDecorator());
                }
                
                k.pacman = k.pacman.removeDecorator();
                
                timerPowerUp.cancel();
            }
        }, FruitRet.buffDuration);    
    }
    
}
