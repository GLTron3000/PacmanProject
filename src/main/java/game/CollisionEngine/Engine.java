package game.CollisionEngine;

import entity.Entity;
import entity.Movable;

public interface Engine {
    public boolean isCollide(Entity e1, Entity e2);
    public boolean outOfBoard(Movable movable , double canvasHeight, double canvasWidth);
}
