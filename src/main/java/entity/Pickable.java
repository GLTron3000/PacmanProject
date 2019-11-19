package entity;


import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Pickable extends Entity{

    public Pickable(double x, double y) {
        super(x, y);
    }

    public abstract int onPick(CopyOnWriteArrayList<Pickable> pickables, int score);
}
