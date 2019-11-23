package ia;

import entity.Direction;
import org.junit.jupiter.api.Test;

import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import static org.junit.jupiter.api.Assertions.*;

class SmartAITest {

    @Test
    void getMove() {
        int [][] wall =  new int[][]{{0, 4}, {2, 2}, {3, 1}, {3, 3}, {2, 1}, {2, 3}};
        Astar astar = new Astar(5, 5, 0, 0, 3, 2,wall);
        Stack<Cell> cells = astar.process();
        PathToDirection translater = new PathToDirection(cells);
        translater.displayCell();
        LinkedBlockingQueue<Direction> directions = translater.translate(astar.grid);
        astar.displaySolution();
        /*for(Direction d: directions)
        System.out.println("taille : " + directions.size()+ "\n direction : " + d.toString()); */

    }
}