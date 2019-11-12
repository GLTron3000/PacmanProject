package game;

import entity.Entity;
import entity.Pacman;
import entity.Direction;
import javafx.scene.canvas.Canvas;
public class CollisionEngine {
    
    public Boolean outOfBorad(Pacman pacman , Canvas canvas){
        //tant qu'on ne touche pas un bord
        if(pacman.getX() > 0 && pacman.getY() > 0 && pacman.getX()+49 < canvas.getWidth()  
                && pacman.getY()+49 < canvas.getHeight() ) 
            return false;
        
        if(pacman.getX() == 0 && pacman.getY()+49 == canvas.getHeight() 
                && (pacman.direction == Direction.LEFT || pacman.direction == Direction.DOWN)) //empecher le coin gauche bas
            return true;
        if(pacman.getX() == 0 && pacman.getY() == 0 
                && (pacman.direction == Direction.LEFT || pacman.direction == Direction.UP)) //empecher le coin gauche haut
            return true;
        if(pacman.getX()+49 == canvas.getWidth() && pacman.getY() == 0 
                && (pacman.direction == Direction.RIGHT || pacman.direction == Direction.UP)) //empecher le coin droite haut
            return true;
        if(pacman.getX()+49 == canvas.getWidth() && pacman.getY()+49 == canvas.getHeight() 
                && (pacman.direction == Direction.RIGHT || pacman.direction == Direction.DOWN)) //empecher le coin droite bas
            return true;
        
        return !(pacman.getX() == 0 && pacman.direction != Direction.LEFT 
                || pacman.getY() == 0 && pacman.direction != Direction.UP
                || pacman.getX()+49 == canvas.getWidth() && pacman.direction != Direction.RIGHT 
                || pacman.getY()+49 == canvas.getHeight() && pacman.direction != Direction.DOWN); //continuer de bouger contre le bord gauche

    }
  
}
