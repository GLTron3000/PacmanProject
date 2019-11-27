package entity.Decorator.Pacman;

import entity.Direction;
import static entity.Direction.STOP;
import entity.MovablePacman;
import entity.Pacman;
import entity.Wall;
import game.Kernel;
import javafx.scene.canvas.Canvas;

public class PacmanWallBreacher extends PacmanDecorator{
    public int breachCount;
    
    public PacmanWallBreacher(MovablePacman pacman) {
        super(pacman);
        breachCount = 2;
    }

// changed behavior
   @Override
    public void checkNextMove(Kernel k) {
        if(movablePacman.getNextDirection() == STOP) return;

        Pacman nextPacman = new Pacman(movablePacman.getX(), movablePacman.getY());
        nextPacman.size = movablePacman.getSize();
        nextPacman.direction = movablePacman.getNextDirection();
        
        for(Wall w : k.walls){
            if(k.engine.isCollide(nextPacman, w)) k.collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.direction != STOP) movablePacman.nextDirection();
        else{
            breachCount--;
            if(breachCount == 0){
                k.pacman = k.pacman.removeDecorator();
            }
            movablePacman.nextDirection();
        }
    }  

// No change, here for consistency

    @Override
    public void draw(Canvas canvas) {
        movablePacman.draw(canvas);
    }
    
    @Override
    public double getSize() {
        return movablePacman.getSize();
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
