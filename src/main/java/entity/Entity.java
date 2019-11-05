package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    private double x;
    private double y;

    public abstract void draw(GraphicsContext gc);

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
