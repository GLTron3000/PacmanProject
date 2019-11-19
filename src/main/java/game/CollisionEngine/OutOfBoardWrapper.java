package game.CollisionEngine;

import entity.Direction;
import entity.Movable;

public class OutOfBoardWrapper implements OutOfBoardBehavior {
    double height;
    double width;

    public OutOfBoardWrapper(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public void behavior(Movable m) {
        if(m.direction== Direction.UP){
            m.setY(m.getY()+height-m.getSize());
        }else if(m.direction== Direction.DOWN){
            m.setY(m.getY()-height+m.getSize());
        }else if(m.direction== Direction.LEFT){
            m.setX(m.getX()+width-m.getSize());
        }else if(m.direction== Direction.RIGHT){
            m.setX(m.getX()-width+m.getSize());
        }
    }
}
