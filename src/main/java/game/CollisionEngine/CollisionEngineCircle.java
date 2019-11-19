package game.CollisionEngine;

import entity.Entity;
import entity.Movable;

public class CollisionEngineCircle implements Engine {
    OutOfBoard OOBBehavior=new OutOfBoard();

    public boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth){
        return OOBBehavior.outOfBoard(movable,canvasHeight,canvasWidth);
    }

    public boolean isCollide(Entity e1, Entity e2){
       if(distanceEntities(e1,e2) <=e1.getSize()/2+e2.getSize()/2){
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
