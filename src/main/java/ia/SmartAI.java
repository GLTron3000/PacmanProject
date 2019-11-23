package ia;

import entity.Direction;
import entity.Fantom;
import entity.Wall;
import game.Kernel;

import java.util.ArrayList;

import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class SmartAI implements IA {
    private LinkedBlockingQueue<Direction> directions = new LinkedBlockingQueue<>();
    int posX;
    int posY;
    Direction last_dir;
    public SmartAI() {
    }

    @Override
    public Direction getMove(Kernel kernel) {
        if(directions.size() == 0){
            posX = (int) kernel.fantoms.get(0).getX();
            posY = (int) kernel.fantoms.get(0).getY();
            int pacX = (int) kernel.pacman.getX();
            int pacy = (int) kernel.pacman.getY();

            System.out.println("pos fantome" + posX + " " + posY + "\n" + " pos pacman" + pacX + " "+ pacy);
            Astar astar = new Astar(700, 800, posX, posY,pacX , pacy, getWall(kernel.walls));
            Stack<Cell> cells = astar.process();
            //astar.displaySolution();
            PathToDirection translater = new PathToDirection(cells);
            System.out.println(" path calculated");
            directions = translater.translate(astar.grid);
        }

        for(Direction d: directions)
            System.out.println("taille : " + directions.size()+ "direction : " + d.toString());

        Fantom fantom = new Fantom(posX,posY,"fdp");
        do{
            fantom.direction = directions.poll();
            for(Wall w : kernel.walls){
                if(kernel.engine.isCollide(fantom, w))
                    kernel.collBeha.collideMovableWall(fantom, w);
                }
        }while (fantom.direction == Direction.STOP);

            return fantom.direction;
    }


    private int[][] getWall(ArrayList<Wall> walls){
        int[][] wall = new int[walls.size()][2];
        int i = 0;
        for (Wall w: walls) {
            wall[i][0]= (int) w.getX();
            wall[i][1] = (int) w.getY();
            i++;
        }
        return wall;
    }

}
