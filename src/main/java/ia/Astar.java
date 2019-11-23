package ia;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;

class Astar {
    private static final int DIAGONAL_COST = 14;
    private static final int VERTICAL_AND_HORIZONTAL_COST = 10;

    private Cell[][] grid;

    private PriorityQueue<Cell> open; // liste des cellules qui vont être étudiées
    private boolean[][] closed; // liste des cellules déja étudiées
    private Stack<Cell> solution = new Stack<>(); // représente le chemin du départ vers la destination

    private int StartX, StartY;
    private int endX, endY;

    Astar(int witdh, int height, int sx, int sy, int ex, int ey, int[][] wall){
        grid = new Cell[witdh][height];
        closed = new boolean[witdh][height];
        open = new PriorityQueue<>(Comparator.comparingInt((Cell c) -> c.finalCost));

        startCell(sx,sy);
        endCell(ex,ey);

        for(int i = 0 ; i <grid.length; i++){
            for(int j = 0; j < grid[i].length;j++){
                grid[i][j] = new Cell(i,j);
                grid[i][j].heuristicCost =Math.abs(i - endX) + Math.abs(j - endY);
                grid[i][j].isSolution = false;
            }
        }

        grid[StartX][StartY].finalCost = 0;

        for (int[] ints : wall) {
            addWallOnCell(ints[0], ints[1]);
        }
    }

    private void addWallOnCell(int i, int j){
        grid[i][j] = null;
    }

    private void startCell(int i, int j){
        StartX = i;
        StartY = j;
    }

    private void endCell(int i, int j){
        endX = i;
        endY = j;
    }


    private void updateCostIfNeeded(Cell curent, Cell t, int cost){
        if(t == null || closed[t.x][t.y])
            return;

        int tFinalCost = t.heuristicCost + cost;
        boolean isInOpen = open.contains(t);

        if(!isInOpen || tFinalCost < t.finalCost){
            t.finalCost = tFinalCost;
            t.parent = curent;
            if(!isInOpen)
                open.add(t);
        }
    }


    Stack<Cell> process() {
        open.add(grid[StartX][StartY]);
        Cell current; // celulle courante
        boolean goal = false;
        while (!open.isEmpty() || !goal) {
            current = open.poll();
            closed[current.x][current.y] = true;

            if (current.equals(grid[endX][endY]))
                goal = true;

            Cell t;

            if (current.x - 1 >= 0) {
                t = grid[current.x - 1][current.y];
                updateCostIfNeeded(current, t, current.finalCost + VERTICAL_AND_HORIZONTAL_COST);


                if (current.y - 1 >= 0) {
                    t = grid[current.x - 1][current.y - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.y + 1 < grid[0].length) {
                    t = grid[current.x - 1][current.y + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }


            }
            if (current.y - 1 >= 0) {
                t = grid[current.x][current.y - 1];
                updateCostIfNeeded(current, t, current.finalCost + VERTICAL_AND_HORIZONTAL_COST);
            }

            if (current.y + 1 < grid[0].length) {
                t = grid[current.x][current.y + 1];
                updateCostIfNeeded(current, t, current.finalCost + VERTICAL_AND_HORIZONTAL_COST);
            }

            if (current.x + 1 < grid.length) {
                t = grid[current.x + 1][current.y];
                updateCostIfNeeded(current, t, current.finalCost + VERTICAL_AND_HORIZONTAL_COST);

                if (current.y - 1 >= 0) {
                    t = grid[current.x + 1][current.y - 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }

                if (current.y + 1 < grid[0].length) {
                    t = grid[current.x + 1][current.y + 1];
                    updateCostIfNeeded(current, t, current.finalCost + DIAGONAL_COST);
                }
            }
        }

        if (goal) {
            Cell cell = grid[endX][endY];
            solution.push(cell);

            while (cell.parent != null) {
                cell = cell.parent;
                solution.push(cell);
            }
        }

    return solution;
    }
}
