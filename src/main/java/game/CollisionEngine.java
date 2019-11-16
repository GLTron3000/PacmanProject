package game;

import entity.Direction;
import entity.Entity;
import entity.Movable;
import entity.Wall;

public class CollisionEngine {
    
    public Boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth){
        //tant qu'on ne touche pas un bord
        if(movable.getX() > 0 && movable.getY() > 0 && movable.getX()+movable.getSize()-1 < canvasWidth  
                && movable.getY()+movable.getSize()-1 < canvasHeight ) 
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

    public boolean isCollideRec(Entity e1,Entity e2){
        if (e1.getX() < e2.getX() + e2.getSize() &&
                e1.getX() + e1.getSize() > e2.getX() &&
                e1.getY() < e2.getY() + e2.getSize() &&
                e1.getSize() + e1.getY() > e2.getY()) {
            return true;
        }
        return false;
    }

    public void collideMovableWall(Movable m, Wall w ){

        if(m.direction==Direction.UP && (w.getY())-m.getY()<=-24.5 && Math.abs((w.getX())-m.getX())!=24.5){
            System.out.println("Colli : UP");
            m.stop();
        }else if(m.direction==Direction.LEFT && (w.getX())-m.getX()<=-24.5 && Math.abs((w.getY())-m.getY())!=24.5 ){
            System.out.println("Colli : left : w :"+w.getX()+"m : "+m.getX() );
            m.stop();
        }else if(m.direction==Direction.DOWN && (w.getY())-m.getY()>=24.5 && Math.abs((w.getX())-m.getX())!=24.5){
            System.out.println("Colli : Down");
            m.stop();
        }else if(m.direction==Direction.RIGHT && (w.getX())-m.getX()>=24.5 && Math.abs((w.getY())-m.getY())!=24.5){
            System.out.println("Colli : Right");
            m.stop();
        }

    }
}
