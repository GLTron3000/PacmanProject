package entity.Decorator;

import entity.Direction;
import entity.Fantom;
import entity.MovableFantom;
import javafx.scene.canvas.Canvas;

public abstract class FantomDecorator extends MovableFantom {
    protected MovableFantom movableFantom;

    public FantomDecorator(MovableFantom fantom) {
        super(fantom.getX(),fantom.getY(),fantom.speed);
        super.initX=fantom.initX;
        super.initY=fantom.initY;
        this.movableFantom = fantom;
    }

    @Override
    public abstract Fantom.FantomState getState() ;
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
    public abstract void setX(double x);
    @Override
    public abstract void setY(double y);

}
