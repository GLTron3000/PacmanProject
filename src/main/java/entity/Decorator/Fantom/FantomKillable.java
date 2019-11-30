package entity.Decorator.Fantom;

import entity.Direction;
import entity.Fantom;
import entity.MovableFantom;
import entity.Pacman;
import game.Kernel;
import ia.IA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.Canvas;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FantomKillable extends FantomDecorator {

    private Image textureKillable;

    public FantomKillable(MovableFantom fantom) {
        super(fantom);        
        try {
            textureKillable = new Image(new FileInputStream("assets/Fantome/KillableFull.png"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void setIA(IA ia) {
        movableFantom.setIA(ia);
    }

    @Override
    public void computeMove(Kernel kernel) {
        movableFantom.computeMove(kernel);
    }

    @Override
    public Fantom.FantomState getState() {
        return Fantom.FantomState.KILLABLE;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();   
        
        gc.setFill(Color.BLACK);
        gc.fillRect(movableFantom.lastDrawX, movableFantom.lastDrawY, movableFantom.size, movableFantom.size);
        movableFantom.lastDrawX = movableFantom.x;
        movableFantom.lastDrawY = movableFantom.y;
        
        if(textureKillable == null){
            gc.setFill(Color.WHITE);
            gc.fillRect(movableFantom.x, movableFantom.y, movableFantom.size, movableFantom.size);
            return;
        }  

        if(reverseFrames){
            if(frame == 0) reverseFrames = false;
            else frame--;
        }else{
            if(frame == 3) reverseFrames = true;
            else frame++;
        }
        
        // x_source y_source w_source h_source x_dest y_dest w_dest h_dest
        gc.drawImage(textureKillable, frame*movableFantom.textureSize, 0, movableFantom.textureSize, movableFantom.textureSize, movableFantom.x, movableFantom.y, movableFantom.size, movableFantom.size);
    }

    @Override
    public void move() {
        movableFantom.move();
    }

    @Override
    public Direction getDirection() {
        return movableFantom.getDirection();
    }

    @Override
    public double getX() {
        return movableFantom.getX();
    }

    @Override
    public double getY() {
        return movableFantom.getY();
    }

    @Override
    public double getSpeed() {
        return movableFantom.getSpeed();
    }

    @Override
    public double getSize() {
        return movableFantom.getSize();
    }

    @Override
    public void setX(double x) {
        movableFantom.setX(x);
    }

    @Override
    public void setY(double y) {
        movableFantom.setY(y);
    }

    @Override
    public MovableFantom removeDecorator() {
        return movableFantom.removeDecorator();
    }
}
