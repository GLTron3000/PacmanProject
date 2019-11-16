package game;

import entity.Direction;
import entity.Entity;
import entity.Movable;
public class CollisionEngine {
    
    public Boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth){
        //tant qu'on ne touche pas un bord
        if(movable.getX() > 0 && movable.getY() > 0 && movable.getX()+movable.getSize()-1 < canvasWidth  
                && movable.getY()+49 < canvasHeight ) 
            return false;
        
        if(movable.getX() == 0 && movable.getY()+movable.getSize()-1 == canvasHeight 
                && (movable.direction == Direction.LEFT || movable.direction == Direction.DOWN)) //empecher le coin gauche bas
            return true;
        if(movable.getX() == 0 && movable.getY() == 0 
                && (movable.direction == Direction.LEFT || movable.direction == Direction.UP)) //empecher le coin gauche haut
            return true;
        if(movable.getX()+movable.getSize()-1 == canvasWidth && movable.getY() == 0 
                && (movable.direction == Direction.RIGHT || movable.direction == Direction.UP)) //empecher le coin droite haut
            return true;
        if(movable.getX()+movable.getSize()-1 == canvasWidth && movable.getY()+movable.getSize()-1 == canvasHeight 
                && (movable.direction == Direction.RIGHT || movable.direction == Direction.DOWN)) //empecher le coin droite bas
            return true;
        
        boolean north = movable.getY() == 0 && movable.direction != Direction.UP;
        boolean east = movable.getX()+movable.getSize()-1 == canvasWidth && movable.direction != Direction.RIGHT;
        boolean west = movable.getX() == 0 && movable.direction != Direction.LEFT;
        boolean south = movable.getY()+movable.getSize()-1 == canvasHeight && movable.direction != Direction.DOWN;
        
        return !( west || north || east || south); //continuer de bouger contre le bord gauche

    }
    public boolean isCollide(Entity e1, Entity e2){
       if(distanceEntities(e1,e2) <e1.getSize()/2+e2.getSize()/2){
           return true;
       }
       return false;
    }

    public double distanceEntities(Entity e1, Entity e2){
        double xE1 = e1.getX();
        double yE1 = e1.getY();
        double xE2 = e2.getX();
        double yE2 = e2.getY();

        double centerE1X=xE1+e1.getSize()/2;
        double centerE1Y=yE1+e1.getSize()/2;

        double centerE2X=xE2+e2.getSize()/2;
        double centerE2Y=yE2+e2.getSize()/2;
        return Math.sqrt(Math.pow(centerE1X-centerE2X,2) + Math.pow(centerE1Y-centerE2Y,2)*1.0);
    }
  
}
