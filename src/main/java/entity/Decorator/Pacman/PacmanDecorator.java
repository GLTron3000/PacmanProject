package entity.Decorator.Pacman;

import entity.Direction;
import entity.Movable;
import entity.MovableFantom;
import entity.MovablePacman;
import javafx.scene.canvas.Canvas;

public abstract class PacmanDecorator extends MovablePacman {
    protected MovablePacman movablePacman;
    public PacmanDecorator(MovablePacman movablePacman) {
        super(movablePacman.getX(), movablePacman.getY(), movablePacman.getSpeed());
        super.initX=movablePacman.initX;
        super.initY=movablePacman.initY;
        this.movablePacman=movablePacman;
    }
    @Override
    public abstract void draw(Canvas canvas);
    @Override
    public abstract void move();
    @Override
    public abstract Direction getDirection();
    @Override
    public abstract double getX();
    @Override
    public abstract double getY();
    @Override
    public abstract double getSize();
    @Override
    public abstract void setX(double x);
    @Override
    public abstract void setY(double y);

    public abstract int getLife();

    public abstract Direction getNextDirection();

    public abstract void setLife(int life);

    public abstract void setNextDirection(Direction nextDirection);

    public abstract void nextDirection();
}
