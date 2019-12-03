package entity.Decorator.Fantom;

import entity.Direction;
import entity.Fantom;
import entity.FruitRet;
import entity.MovableFantom;
import game.Kernel;
import ia.IA;
import javafx.scene.canvas.Canvas;

public class FantomSizeReducer extends FantomDecorator{
    final double originalSize;
    final double reducedSize;

    public FantomSizeReducer(MovableFantom fantom) {
        super(fantom);
        originalSize = fantom.getSize();
        reducedSize = fantom.getSize()*FruitRet.sizeModifier;
    }
    
    @Override
    public double getSize() {
        return reducedSize;
    }
    
    @Override
    public void draw(Canvas canvas) {
        movableFantom.size = reducedSize;
        movableFantom.draw(canvas);
        movableFantom.size = originalSize;
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
        return movableFantom.getState();
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
