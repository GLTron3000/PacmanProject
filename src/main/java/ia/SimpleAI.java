package ia;

import entity.Direction;
import static entity.Direction.*;
import entity.Fantom;
import entity.Wall;
import game.Kernel;

public class SimpleAI implements IA{

    private int continueDirection = 0;
    private Direction lastDirection = STOP;
    
    @Override
    public Direction getMove(Kernel kernel, Fantom fantom) {
        
        continueDirection--;
        if(continueDirection > 0) return lastDirection;
        
        continueDirection = 25;
        
        double angle = angle(fantom.getX(), fantom.getY(), kernel.pacman.getX(), kernel.pacman.getY());
        Direction direction = angleToDirection(angle);
        
        while(!checkDirection(direction, fantom, kernel)){
            angle+=20;
            if(angle > 360) angle = 0;
            direction = angleToDirection(angle);
        }
        
        //System.out.println("[SIMPLE AI] angle : "+angle+" direction : "+direction);
        lastDirection = direction;
        return direction;
    }
    
    private boolean checkDirection(Direction direction, Fantom fantom, Kernel kernel){
        Fantom nextFantom = new Fantom(fantom.getX(), fantom.getY(), "");
        nextFantom.direction = direction;
        
        for(Wall w : kernel.walls){
            if(kernel.engine.isCollide(nextFantom, w))
                kernel.collBeha.collideMovableWall(nextFantom, w);
        }

        return nextFantom.direction != STOP;
    }
    
    private Direction angleToDirection(double angle){
        if(angle < 45) return RIGHT;
        else if(angle < 135) return UP;
        else if(angle < 225) return LEFT;
        else if(angle < 315) return DOWN;
        else return RIGHT;
    }
    
    public double angle(double p1_x, double p1_y, double p2_x, double p2_y) {
        final double deltaY = (p1_y - p2_y);
        final double deltaX = (p2_x - p1_x);
        final double result = Math.toDegrees(Math.atan2(deltaY, deltaX)); 
        return (result < 0) ? (360d + result) : result;
    }
}
