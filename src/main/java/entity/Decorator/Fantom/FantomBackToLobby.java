package entity.Decorator.Fantom;

import entity.Direction;
import entity.Fantom;
import entity.MovableFantom;
import entity.Pacman;
import game.Kernel;
import ia.IA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class FantomBackToLobby extends FantomDecorator  {
    
    private Image textureBackToLobby;
    
    public FantomBackToLobby(MovableFantom fantom) {
        super(fantom);
        
        try {
            textureBackToLobby = new Image(new FileInputStream("assets/Fantome/BackToLobby.png"));
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
        return Fantom.FantomState.BACKTOLOBBY;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D(); 
        
        gc.setFill(Color.BLACK);
        gc.fillRect(movableFantom.lastDrawX, movableFantom.lastDrawY, movableFantom.size, movableFantom.size);
        movableFantom.lastDrawX = movableFantom.x;
        movableFantom.lastDrawY = movableFantom.y;
        
        if(textureBackToLobby == null){
            gc.setFill(Color.DARKBLUE);
            gc.fillRect(movableFantom.x, movableFantom.y, movableFantom.size, movableFantom.size);
            return;
        }  

        gc.drawImage(textureBackToLobby, movableFantom.x, movableFantom.y, movableFantom.size, movableFantom.size);
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
    public void setDirection(Direction direction) {
        movableFantom.setDirection(direction);
    }
    
    @Override
    public void goUp(){
        movableFantom.goUp();
    }

    @Override
    public void goDown(){
        movableFantom.goDown();
    }

    @Override
    public void goLeft(){
        movableFantom.goLeft();
    }

    @Override
    public void goRight(){
        movableFantom.goRight();
    }

    @Override
    public void stop(){
        movableFantom.stop();
    }
    
    @Override
    public MovableFantom removeDecorator() {
        return movableFantom.removeDecorator();
    }
}
