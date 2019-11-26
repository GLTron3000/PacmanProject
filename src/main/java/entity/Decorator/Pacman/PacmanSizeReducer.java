package entity.Decorator.Pacman;

import entity.Direction;
import entity.Fruit;
import entity.MovablePacman;
import javafx.scene.canvas.Canvas;

import java.util.Timer;
import java.util.TimerTask;

public class PacmanSizeReducer extends PacmanDecorator {
    public double sizeModifier=0.5;
    private Boolean isBuffUp=false;

    public PacmanSizeReducer(MovablePacman pacman) {
        super(pacman);
        isBuffUp=true;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                isBuffUp=false;
                timer.cancel();
            }
        }, 10_000);
    }

// changed behavior
    @Override
    public double getSize() {
        double size=movablePacman.getSize();
        if(isBuffUp){
            size*=sizeModifier;
        }
        return size;
    }

// No change, here for consistency

    @Override
    public void draw(Canvas canvas) {
        movablePacman.draw(canvas);
    }

    @Override
    public void move() {
        movablePacman.move();
    }

    @Override
    public Direction getDirection() {
        return movablePacman.getDirection();
    }

    @Override
    public double getX() {
        return movablePacman.getX();
    }

    @Override
    public double getY() {
        return movablePacman.getY();
    }
    @Override
    public void setX(double x) {
        movablePacman.setX(x);
    }

    @Override
    public void setY(double y) {
        movablePacman.setY(y);
    }

    @Override
    public int getLife() {
        return  movablePacman.getLife();
    }

    @Override
    public Direction getNextDirection() {
        return movablePacman.getNextDirection();
    }

    @Override
    public void setLife(int life) {
        movablePacman.setLife(life);
    }

    @Override
    public void setNextDirection(Direction nextDirection) {
        movablePacman.setNextDirection(nextDirection);
    }

    @Override
    public void nextDirection() {
        movablePacman.nextDirection();
    }
}
