package entity.Decorator.Pacman;

import entity.Direction;
import entity.FruitRet;
import entity.MovablePacman;
import javafx.scene.canvas.Canvas;

public class PacmanSizeReducer extends PacmanDecorator {
    final double originalSize;
    final double reducedSize;

    public PacmanSizeReducer(MovablePacman pacman) {
        super(pacman);
        originalSize = pacman.getSize();
        reducedSize = pacman.getSize()*FruitRet.sizeModifier;
    }

// changed behavior
    @Override
    public double getSize() {
        return reducedSize;
    }

// No change, here for consistency

    @Override
    public void draw(Canvas canvas) {
        movablePacman.size = reducedSize;
        movablePacman.draw(canvas);
        movablePacman.size = originalSize;
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
        return movablePacman.getLife();
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

    @Override
    public MovablePacman removeDecorator() {
        return movablePacman.removeDecorator();
    }

    @Override
    public int getPowerUpReductor() {
        return movablePacman.getPowerUpReductor();
    }

    @Override
    public void setPowerUpReductor(int powerUpReductor) {
        movablePacman.setPowerUpReductor(powerUpReductor);
    }

    @Override
    public void goUp(){
        movablePacman.goUp();
    }

    @Override
    public void goDown(){
        movablePacman.goDown();
    }

    @Override
    public void goLeft(){
        movablePacman.goLeft();
    }

    @Override
    public void goRight(){
        movablePacman.goRight();
    }
    
    @Override
    public void stop(){
        movablePacman.stop();
    }
}
