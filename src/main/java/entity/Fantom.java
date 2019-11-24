package entity;

import java.util.Objects;

public class Fantom extends Movable {
    String name;
    public double initX;
    public double initY;
    public enum FantomState{NORMAL, KILLABLE, BACKTOLOBBY}

    public FantomState fState;

    public Fantom(double x, double y, String name) {
        super(x, y, 5);
        initX=x;
        initY=y;
        type="Fantom";
        this.name = name;
        fState = FantomState.NORMAL;
        frameNumber = 1;
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
