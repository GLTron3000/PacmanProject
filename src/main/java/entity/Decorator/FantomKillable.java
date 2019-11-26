package entity.Decorator;

import entity.Direction;
import entity.Fantom;
import entity.Fruit;
import entity.MovableFantom;
import game.Kernel;
import ia.IA;
import javafx.scene.canvas.Canvas;

import java.util.Timer;
import java.util.TimerTask;

public class FantomKillable extends FantomDecorator {

    private Boolean isBuffUp;

    public FantomKillable(MovableFantom fantom) {
        super(fantom);
        isBuffUp=true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                System.out.println("timer");
                isBuffUp=false;
                movableFantom.setNormal();
                timer.cancel();
            }
        }, Fruit.buffDuration);
    }

    @Override
    public void setBackToLobby() {
        movableFantom.setBackToLobby();
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
        if(isBuffUp){
            return Fantom.FantomState.KILLABLE;
        }
        return movableFantom.getState();
    }

    @Override
    public void draw(Canvas canvas) {
        movableFantom.draw(canvas);
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
    public void setX(double x) {
        movableFantom.setX(x);
    }

    @Override
    public void setY(double y) {
        movableFantom.setY(y);
    }

    @Override
    public void setKillable() {
        movableFantom.setKillable();
    }

    @Override
    public void setNormal() {
        movableFantom.setNormal();
    }
}
