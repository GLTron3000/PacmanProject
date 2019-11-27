package entity;

import entity.Fantom;
import entity.Movable;
import game.Kernel;
import ia.IA;

public abstract class MovableFantom extends Movable {
    public MovableFantom(double x, double y, double speed) {
        super(x, y, speed);
    }
    public abstract void setIA(IA ia);
    public abstract void computeMove(Kernel kernel);
    public abstract Fantom.FantomState getState();
    public abstract MovableFantom removeDecorator();
}
