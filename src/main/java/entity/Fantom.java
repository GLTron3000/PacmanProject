package entity;

import game.Kernel;
import ia.IA;
import java.util.Objects;

public class Fantom extends MovableFantom {
    public String name;
    IA ia;

    public enum FantomState{NORMAL, KILLABLE, BACKTOLOBBY}

    @Override
    public FantomState getState() {
        return FantomState.NORMAL;
    }

    public Fantom(double x, double y, String name) {
        super(x, y, 1);
        initX=x;
        initY=y;
        type="Fantom";
        this.name = name;
        frameNumber = 1;
        textureSize = 25;
    }
    
    @Override
    public void setIA(IA ia){
        this.ia = ia;
    }
    
    @Override
    public void computeMove(Kernel kernel){
        direction = ia.getMove(kernel, this);
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

    @Override
    public MovableFantom removeDecorator() {
        return this;
    }
}
