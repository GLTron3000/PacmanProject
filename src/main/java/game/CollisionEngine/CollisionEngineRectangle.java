package game.CollisionEngine;

import entity.Entity;
import entity.Movable;

public class CollisionEngineRectangle implements Engine {
    OutOfBoard OOBBehavior=new OutOfBoard();

    @Override
    public boolean isCollide(Entity e1,Entity e2){
        if (e1.getX() < e2.getX() + e2.getSize() &&
                e1.getX() + e1.getSize() > e2.getX() &&
                e1.getY() < e2.getY() + e2.getSize() &&
                e1.getSize() + e1.getY() > e2.getY()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth){
        return OOBBehavior.outOfBoard(movable,canvasHeight,canvasWidth);
    }


}
