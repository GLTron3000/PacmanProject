package ia;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstarTest {
    @Test
    void process() {
        Astar astar = new Astar(5, 5, 0, 0, 3, 2,
                new int[][]{
                        {0, 4}, {2, 2}, {3, 1}, {3, 3}, {2, 1}, {2, 3}
                });
        astar.display();
        astar.process();
        astar.displaySolution();
    }



}