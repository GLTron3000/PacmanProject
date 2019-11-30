package entity.Decorator.Pacman;

import entity.*;
import game.Kernel;
import javafx.scene.canvas.Canvas;

import static entity.Direction.STOP;
import static java.lang.Math.round;

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
        return round(reducedSize+0.5);
    }

    @Override
    public void checkNextMove(Kernel k) {
        if(getNextDirection() == STOP) return;

        PacmanSizeReducer nextPacman = new PacmanSizeReducer(movablePacman);
        nextPacman.size = getSize();
        nextPacman.setDirection(getNextDirection());

        for(Wall w : k.walls){
            if(k.engine.isCollide(nextPacman, w)) k.collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.getDirection() != STOP){
            nextDirection();
        }
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
    public void setDirection(Direction direction) {
        movablePacman.setDirection(direction);
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
