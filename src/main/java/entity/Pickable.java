package entity;

import game.Kernel;

public abstract class Pickable extends Entity{
    public static long value = 10;

    public Pickable(double x, double y) {
        super(x, y);
        size = 10;
    }

    public abstract void onPick(Kernel k);
}
