package game;

import entity.Pacman;
import entity.Direction;
public class CollisionEngine {
    
    public Boolean outOfBorad(Pacman pacman , double canvasHeight, double canvasWidth){
        //tant qu'on ne touche pas un bord
        if(pacman.getX() > 0 && pacman.getY() > 0 && pacman.getX()+49 < canvasWidth  
                && pacman.getY()+49 < canvasHeight ) 
            return false;
        
        if(pacman.getX() == 0 && pacman.getY()+49 == canvasHeight 
                && (pacman.direction == Direction.LEFT || pacman.direction == Direction.DOWN)) //empecher le coin gauche bas
            return true;
        if(pacman.getX() == 0 && pacman.getY() == 0 
                && (pacman.direction == Direction.LEFT || pacman.direction == Direction.UP)) //empecher le coin gauche haut
            return true;
        if(pacman.getX()+49 == canvasWidth && pacman.getY() == 0 
                && (pacman.direction == Direction.RIGHT || pacman.direction == Direction.UP)) //empecher le coin droite haut
            return true;
        if(pacman.getX()+49 == canvasWidth && pacman.getY()+49 == canvasHeight 
                && (pacman.direction == Direction.RIGHT || pacman.direction == Direction.DOWN)) //empecher le coin droite bas
            return true;
        
        boolean north = pacman.getY() == 0 && pacman.direction != Direction.UP;
        boolean east = pacman.getX()+49 == canvasWidth && pacman.direction != Direction.RIGHT;
        boolean west = pacman.getX() == 0 && pacman.direction != Direction.LEFT;
        boolean south = pacman.getY()+49 == canvasHeight && pacman.direction != Direction.DOWN;
        
        return !( west || north || east || south); //continuer de bouger contre le bord gauche

    }
  
}
