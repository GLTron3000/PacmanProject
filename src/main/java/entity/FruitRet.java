package entity;

import game.Kernel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FruitRet extends Pickable{
    public static long buffDuration=10000; //millsecond
    public static double sizeModifier = 0.5;

    public FruitRet(double x, double y) {
        super(x, y);
        type="FruitRet";
    }

    @Override
    public void onPick(Kernel k) {
        System.out.println("PICKED FRUIT REDUCTOR");
        
        k.pacman.setPowerUpReductor(k.pacman.getPowerUpReductor()+1);
        k.pickables.remove(this);
        k.score+=50;
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
