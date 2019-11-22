package ia;

public class Cell {
    public int x, y;
    Cell parent;
    int heuristicCost;
    int finalCost;
    boolean isSolution;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "[" + x +"," + y + "]";
    }
}
