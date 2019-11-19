package game.CollisionEngine;

import entity.Movable;

public class OutOfBoardStop implements OutOfBoardBehavior {
    @Override
    public void behavior(Movable m) {
        m.stop();
    }
}
