package game.CollisionEngine;

import entity.Direction;
import entity.Movable;
import entity.Wall;

public class CollideBehaviorClassic implements CollideBehavior {
    @Override
    public void collideMovableWall(Movable m, Wall w ){

        if(m.getDirection()== Direction.UP && ((w.getY())-m.getY()<=-w.getSize() && Math.abs((w.getX())-m.getX())!=w.getSize())){
            System.out.println("Colli : UP : wY :"+w.getY()+"mY : "+m.getY());
            System.out.println("Colli : UP : wX :"+w.getX()+"mX : "+m.getX());
            System.out.println("movable size :"+m.getSize());
            m.stop();
        }else if(m.getDirection()==Direction.LEFT && ((w.getX())-m.getX()<=-w.getSize() && Math.abs((w.getY())-m.getY())!=w.getSize())){
            System.out.println("Colli : LEFT : wY :"+w.getY()+"mY : "+m.getY());
            System.out.println("Colli : LEFT : wX :"+w.getX()+"mX : "+m.getX());
            System.out.println("movable size :"+m.getSize());
            m.stop();
        }else if(m.getDirection()==Direction.DOWN && ((w.getY())-m.getY()<=m.getSize()&& (w.getY()-m.getY()>=0) && Math.abs((w.getX())-m.getX())!=w.getSize())){
            System.out.println("Colli : Down : wY :"+w.getY()+"mY : "+m.getY());
            System.out.println("Colli : Down : wX :"+w.getX()+"mX : "+m.getX());
            System.out.println("movable size :"+m.getSize());
            m.stop();
        }else if(m.getDirection()==Direction.RIGHT && ((w.getX())-m.getX()<=m.getSize()&& (w.getX()-m.getX()>=0) && Math.abs((w.getY())-m.getY())!=w.getSize())){
            System.out.println("Colli : RIGHT : wY :"+w.getY()+"mY : "+m.getY());
            System.out.println("Colli : RIGHT : wX :"+w.getX()+"mX : "+m.getX());
            System.out.println("movable size :"+m.getSize());
            m.stop();
        }


    }
}
