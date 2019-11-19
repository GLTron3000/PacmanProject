package entity;


import game.Kernel;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Pickable extends Entity{

    public Pickable(double x, double y) {
        super(x, y);
    }

    public abstract int onPick(Kernel k);
}
