package entity;


public abstract class Pickable extends Entity{

    public Pickable(double x, double y) {
        super(x, y);
    }

    public abstract void onPick();
}
