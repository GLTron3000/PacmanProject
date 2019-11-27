package entity;

import static entity.Direction.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public abstract class Movable extends Entity{
    public Direction direction;
    public double speed;
    public double textureSize;
    public int frame = 0;
    public int frameNumber;
    public boolean reverseFrames = false;
    public double lastDrawX;
    public double lastDrawY;
    public double lastDrawSize;
    public double initX;
    public double initY;


    public Movable(double x, double y, double speed) {
        super(x, y);
        initX=x;
        initY=y;
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

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();    
        
        gc.setFill(Color.BLACK);
        gc.fillRect(lastDrawX, lastDrawY, lastDrawSize, lastDrawSize);
        lastDrawX = x;
        lastDrawY = y;
        lastDrawSize = getSize();
        
        if(texture == null){
            gc.setFill(Color.RED);
            gc.fillRect(x, y, getSize(), getSize());
            return;
        }  
        
        int ySource = 0;
        switch (direction){
            case UP: ySource+=textureSize; break;
            case DOWN: ySource+=3*textureSize; break;
            case LEFT: ySource+=2*textureSize; break;
            default: break;
        }

        if(reverseFrames){
            if(frame == 0) reverseFrames = false;
            else frame--;
        }else{
            if(frame == frameNumber) reverseFrames = true;
            else frame++;
        }

        // x_source y_source w_source h_source x_dest y_dest w_dest h_dest
        gc.drawImage(texture, frame*textureSize, ySource, textureSize, textureSize, x, y, getSize(), getSize());
    }
    
}
