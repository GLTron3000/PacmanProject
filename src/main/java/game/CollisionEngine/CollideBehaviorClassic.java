package game.CollisionEngine;

import entity.Direction;
import entity.Entity;
import entity.Movable;
import entity.Wall;

public class CollideBehaviorClassic implements CollideBehavior {
    @Override
    public void collideMovableWall(Movable m, Wall w ){

        if(m.direction== Direction.UP && ((w.getY())-m.getY()<=-w.getSize() && Math.abs((w.getX())-m.getX())!=w.getSize())){
            //System.out.println("Colli : UP : wY :"+w.getY()+"m : "+m.getY());
            m.stop();
        }else if(m.direction==Direction.LEFT && ((w.getX())-m.getX()<=-w.getSize() && Math.abs((w.getY())-m.getY())!=w.getSize())){
            //System.out.println("Colli : left : wX :"+w.getX()+" mX : "+m.getX() );
            m.stop();
        }else if(m.direction==Direction.DOWN && ((w.getY())-m.getY()>=w.getSize() && Math.abs((w.getX())-m.getX())!=w.getSize())){
            //System.out.println("Colli : Down : wY :"+w.getY()+"mY : "+m.getY());
            //System.out.println("Colli : Down : wX :"+w.getX()+"mX : "+m.getX());
            m.stop();
        }else if(m.direction==Direction.RIGHT && ((w.getX())-m.getX()>=w.getSize() && Math.abs((w.getY())-m.getY())!=w.getSize())){
            //System.out.println("Colli : Right : wX :"+w.getX()+"mX : "+m.getX());
            m.stop();
        }
    }
}
