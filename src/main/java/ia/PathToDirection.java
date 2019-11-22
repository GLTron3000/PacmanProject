package ia;

import entity.Direction;


import java.util.PriorityQueue;
import java.util.Stack;

class PathToDirection {
    private PriorityQueue<Direction> directions = new PriorityQueue<>();
    private Stack<Cell> cells;
    private int posX;
    private int posY;

    PathToDirection(Stack<Cell> cells, int x, int y) {
        this.cells = cells;
        posX = x;
        posY = y;
    }

    PriorityQueue<Direction> translate(){
        for (Cell c: cells) {
            if(c.x < posX && c.y == posY)
                directions.add(Direction.UP);

            if(c.x > posX && c.y == posY)
                directions.add(Direction.DOWN);

            if(c.y < posY && c.x == posX)
                directions.add(Direction.LEFT);

            if(c.y > posY && c.x == posX)
                directions.add(Direction.RIGHT);

            if(c.x > posX && c.y > posY){
                directions.add(Direction.DOWN);
                directions.add(Direction.RIGHT);
            }

            if(c.x > posX && c.y < posY){
                directions.add(Direction.DOWN);
                directions.add(Direction.LEFT);
            }

            if(c.x < posX && c.y > posY){
                directions.add(Direction.UP);
                directions.add(Direction.RIGHT);
            }

            if(c.x < posX && c.y < posY){
                directions.add(Direction.UP);
                directions.add(Direction.LEFT);
            }
        }
        return directions;
    }
}
