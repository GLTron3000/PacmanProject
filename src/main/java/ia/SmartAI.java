package ia;

import entity.Direction;
import entity.Wall;
import game.Kernel;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class SmartAI implements IA {
    private PriorityQueue<Direction> directions = new PriorityQueue<>();
    public SmartAI() {
    }

    @Override
    public Direction getMove(Kernel kernel) {
        if(directions.isEmpty()){
        int posX = (int) kernel.fantoms.get(0).getX();
        int posY = (int) kernel.fantoms.get(0).getY();
        Astar astar = new Astar(800, 800, posX, posY, (int) kernel.pacman.getX(), (int) kernel.pacman.getY(), getWall(kernel.walls));
        PathToDirection translater = new PathToDirection(astar.process(), posX, posY);
        directions = translater.translate();
        }
        return directions.poll();
    }

    private int[][] getWall(ArrayList<Wall> walls){
        int[][] wall = new int[walls.size()][walls.size()];
        int i = 0;
        for (Wall w: walls) {
            wall[i][0]= (int) w.getX();
            wall[i][1] = (int) w.getY();
            i++;
        }
        return wall;
    }

}
