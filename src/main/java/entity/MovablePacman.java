package entity;

import entity.Movable;

public abstract class MovablePacman extends Movable {
    public MovablePacman(double x, double y, double speed) {
        super(x, y, speed);
    }
    
    public abstract int getLife();
    public abstract void setLife(int life);
    public abstract int getPowerUpReductor();
    public abstract void setPowerUpReductor(int powerUpReductor);
    public abstract Direction getNextDirection();
    public abstract void setNextDirection(Direction nextDirection);
    public abstract void nextDirection();
    public abstract MovablePacman removeDecorator();
}
