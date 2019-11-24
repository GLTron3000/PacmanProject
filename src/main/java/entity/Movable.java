package entity;

import static entity.Direction.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Movable extends Entity{
    public Direction direction;
    double speed;
    int frame = 0;
    int frameNumber;
    boolean reverseFrames = false;

    public Movable(double x, double y, double speed) {
        super(x, y);
        direction = STOP;
        this.speed = speed;
    }
    
    public void goUp(){
        direction = Direction.UP;
    }

    public void goDown(){
        direction = Direction.DOWN;
    }

    public void goLeft(){
        direction = Direction.LEFT;
    }

    public void goRight(){
        direction = Direction.RIGHT;
    }

    public void stop(){
        direction = Direction.STOP;
    }

    public void move(){        
        switch (direction){
            case UP: y-=speed; break;
            case DOWN: y+=speed; break;
            case LEFT: x-=speed; break;
            case RIGHT: x+=speed; break;
            default: break;
        }
    }
    
    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();      
        
        if(texture == null){
            gc.setFill(Color.RED);
            gc.fillRect(x, y, size, size);
            return;
        }  
        
        int ySource = 0;
        switch (direction){
            case UP: ySource+=size; break;
            case DOWN: ySource+=3*size; break;
            case LEFT: ySource+=2*size; break;
            default: break;
        }

        if(reverseFrames){
            if(frame == 0) reverseFrames = false;
            else frame--;
        }else{
            if(frame == frameNumber) reverseFrames = true;
            else frame++;
        }

        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);
        
        // x_source y_source w_source h_source x_dest y_dest w_dest h_dest
        gc.drawImage(texture, frame*size, ySource, size, size, x, y, size, size);
    }
}
