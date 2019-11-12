package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class Entity {
    double x;
    double y;
    String type;
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
