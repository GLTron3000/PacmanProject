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
        if(null!= m.direction) switch (m.direction) {
            case UP: m.setY(m.getY()+height-m.getSize()); break;
            case DOWN: m.setY(m.getY()-height+m.getSize()); break;
            case LEFT: m.setX(m.getX()+width-m.getSize()); break;
            case RIGHT: m.setX(m.getX()-width+m.getSize()); break;
            default: break;
        }
    }
}
