package game.CollisionEngine;

import entity.Movable;
import entity.Wall;

public interface CollideBehavior {
    public void collideMovableWall(Movable m, Wall w );
}
