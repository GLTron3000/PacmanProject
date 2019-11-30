package entity;

import entity.Decorator.Pacman.PacmanWallBreacher;
import game.Kernel;
import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FruitWall extends Pickable{
    public static int powerUpCost = 200;
    public static long buffDuration=10000; //millsecond

    public FruitWall(double x, double y) {
        super(x, y);
        type = "FruitWall";
    }    

    @Override
    public void onPick(Kernel k) {
    }

    public static void effect(Kernel k) {
        
        if(k.score - powerUpCost < 0) return;
        
        System.out.println("ADD EFFECT WALL BREACHER");
        k.score-=powerUpCost;
        k.pacman = new PacmanWallBreacher(k.pacman);
        
        Timer timerPowerUp = new Timer();
        timerPowerUp.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("REMOVE EFFECT WALL BREACHER");

                k.pacman = k.pacman.removeDecorator();
                //Detecter si pacman toujours dans mur => ded
                
                timerPowerUp.cancel();
            }
        }, FruitWall.buffDuration);
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
    
}
