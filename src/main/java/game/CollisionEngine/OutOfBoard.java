package game.CollisionEngine;

import entity.Direction;
import entity.Movable;

public class OutOfBoard {
    public boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth){
        //tant qu'on ne touche pas un bord
        if(movable.getX() > 0 && movable.getY() > 0 && movable.getX()+movable.getSize()-1 < canvasWidth
                && movable.getY()+movable.getSize()-1 < canvasHeight )
            return false;

        if(movable.getX() == 0 && movable.getY()+movable.getSize()-1 == canvasHeight
                && (movable.getDirection() == Direction.LEFT || movable.getDirection() == Direction.DOWN)) //empecher le coin gauche bas
            return true;
        if(movable.getX() == 0 && movable.getY() == 0
                && (movable.getDirection() == Direction.LEFT || movable.getDirection() == Direction.UP)) //empecher le coin gauche haut
            return true;
        if(movable.getX()+movable.getSize()-1 == canvasWidth && movable.getY() == 0
                && (movable.getDirection() == Direction.RIGHT || movable.getDirection() == Direction.UP)) //empecher le coin droite haut
            return true;
        if(movable.getX()+movable.getSize()-1 == canvasWidth && movable.getY()+movable.getSize()-1 == canvasHeight
                && (movable.getDirection() == Direction.RIGHT || movable.getDirection() == Direction.DOWN)) //empecher le coin droite bas
            return true;

        boolean north = movable.getY() == 0 && movable.getDirection() != Direction.UP;
        boolean east = movable.getX()+movable.getSize()-1 == canvasWidth && movable.getDirection() != Direction.RIGHT;
        boolean west = movable.getX() == 0 && movable.getDirection() != Direction.LEFT;
        boolean south = movable.getY()+movable.getSize()-1 == canvasHeight && movable.getDirection() != Direction.DOWN;

        return !( west || north || east || south); //continuer de bouger contre le bord gauche

    }
}
