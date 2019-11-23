package entity;

import static entity.Direction.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Pacman extends Movable {
    public int life;
    public double initX;
    public double initY;
    public Direction nextDirection;
    
    private int frameNumber = 0;
    private boolean reverseFrames = false;

    public Pacman(double x, double y) {
        super(x, y, 0.5);
        initX=x;
        initY=y;
        direction = STOP;
        nextDirection = STOP;
        type="Pacman";
        life = 3;
    }
    
    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();      
        int ySource = 0;
        switch (direction){
            case UP: ySource+=size; break;
            case DOWN: ySource+=3*size; break;
            case LEFT: ySource+=2*size; break;
            default: break;
        }

        if(reverseFrames){
            if(frameNumber == 0) reverseFrames = false;
            else frameNumber--;
        }else{
            if(frameNumber == 2) reverseFrames = true;
            else frameNumber++;
        }

        //gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);
        
        // x_source y_source w_source h_source x_dest y_dest w_dest h_dest
        gc.drawImage(image, frameNumber*size, ySource, size, size, x, y, size, size);

        //gc.setFill(Color.YELLOW);
        //gc.fillRect(x, y, size, size);
    }

    @Override
    public String toString() {
        return "Pacman{" +
                "direction=" + direction +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public void goUp(){
        nextDirection = UP;
    }

    @Override
    public void goDown(){
        nextDirection = DOWN;
    }

    @Override
    public void goLeft(){
        nextDirection = LEFT;
    }

    @Override
    public void goRight(){
        nextDirection = RIGHT;
    }
    
    public void nextDirection(){
        direction = nextDirection;
        nextDirection = STOP;
    }
}
