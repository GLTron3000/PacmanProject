package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;
import javafx.scene.canvas.Canvas;

public class Fantom extends Movable {
    String name;
    public double initX;
    public double initY;
    public enum FantomState{NORMAL, KILLABLE, BACKTOLOBBY}

    public FantomState fState;


    public Fantom(double x, double y, String name) {
        super(x, y, 0.1);
        initX=x;
        initY=y;
        type="Fantom";
        this.name = name;
        fState = FantomState.NORMAL;
    }

    @Override
    public void draw(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        gc.setFill(Color.BLACK);
        gc.fillRect(x, y, size, size);
        
        if(fState==FantomState.KILLABLE){
            gc.setFill(Color.RED);
            gc.fillRect(x, y, size, size);
        }else{
            gc.setFill(Color.CYAN);
            gc.fillRect(x, y, size, size);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fantom fantom = (Fantom) o;
        return Objects.equals(name, fantom.name);
    }


    @Override
    public String toString() {
        return "Fantom{" +
                "direction=" + direction +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
}
