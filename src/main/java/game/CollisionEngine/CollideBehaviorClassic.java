package game.CollisionEngine;

import entity.Direction;
import entity.Entity;
import entity.Movable;
import entity.Wall;

public class CollideBehaviorClassic implements CollideBehavior {
    @Override
    public void collideMovableWall(Movable m, Wall w ){

        if(m.direction== Direction.UP && ((w.getY())-m.getY()>=-24.5 && Math.abs((w.getX())-m.getX())!=24.5)){
            System.out.println("Colli : UP");
            m.stop();
        }else if(m.direction==Direction.LEFT && ((w.getX())-m.getX()>=-24.5 && Math.abs((w.getY())-m.getY())!=24.5)){
            System.out.println("Colli : left : w :"+w.getX()+"m : "+m.getX() );
            m.stop();
        }else if(m.direction==Direction.DOWN && ((w.getY())-m.getY()<=24.5 && Math.abs((w.getX())-m.getX())!=24.5)){
            System.out.println("Colli : Down");
            m.stop();
        }else if(m.direction==Direction.RIGHT && ((w.getX())-m.getX()<=24.5 && Math.abs((w.getY())-m.getY())!=24.5)){
            System.out.println("Colli : Right");
            m.stop();
        }
    }
}
