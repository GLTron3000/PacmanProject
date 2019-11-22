package entity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.canvas.GraphicsContext;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

public abstract class Entity {
    double x;
    double y;
    double size;
    String type;
    Image image;
    
    public abstract void draw(GraphicsContext gc);

    public Entity(double x, double y) {
        this.x = x;
        this.y = y;
        size = 25;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Double.compare(entity.x, x) == 0 &&
                Double.compare(entity.y, y) == 0 &&
                Double.compare(entity.size, size) == 0 &&
                Objects.equals(type, entity.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, size, type);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
    }

    public String getType() { return type; }

    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }
    
    public void loadTexture(String path){
        try {
            image = new Image(new FileInputStream(path));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
