package ia;

import entity.Direction;
import static entity.Direction.*;
import entity.Fantom;
import entity.Wall;
import game.Kernel;

import java.util.ArrayList;

import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class SmartAI implements IA {
    private LinkedBlockingQueue<Direction> directions = new LinkedBlockingQueue<>();
    private int lastPacX;
    private int lastPacY;

    public SmartAI() {
    }

    @Override
    public Direction getMove(Kernel kernel, Fantom fantom) {
        //|| lastPacX != kernel.pacman.getX() || lastPacY != kernel.pacman.getY()
        if(directions.isEmpty()) computeDirections(kernel, fantom);

        Direction direction = directions.poll();

        //System.out.println("[SMARTAI] Current direction "+direction);
        return direction;
    }

    private boolean checkDirection(Direction direction, Fantom fantom, Kernel kernel){
        Fantom nextFantom = new Fantom(fantom.getX(), fantom.getY(), "");
        nextFantom.direction = direction;
        
        for(Wall w : kernel.walls){
            if(kernel.engine.isCollide(nextFantom, w))
                kernel.collBeha.collideMovableWall(nextFantom, w);
        }

        return nextFantom.direction != STOP;
    }
    
    private void computeDirections(Kernel kernel, Fantom fantom){
        int posX = (int) fantom.getX();
        int posY = (int) fantom.getY();
        int pacX = (int) kernel.pacman.getX();
        int pacy = (int) kernel.pacman.getY();
        lastPacX = pacX;
        lastPacY = pacy;

        //System.out.println("[SMARTAI] pos fantome"+ " " + fantom.name + " " + posX/25 + " " + posY/25 + " -> pos pacman" + pacX + " "+ pacy);
        Astar astar = new Astar(700/25, 775/25, posX/25, posY/25,pacX/25 , pacy/25, getWall(kernel.walls));
        Stack<Cell> cells = astar.process();
        //astar.displaySolution();
        PathToDirection translater = new PathToDirection(cells);
        //System.out.println("[SMARTAI] path calculated");
        directions = translater.translate(astar.grid);
        //directions.forEach(System.out::println);
    }

    private int[][] getWall(List<Wall> walls){
        int[][] wall = new int[walls.size()][2];
        int i = 0;
        for (Wall w: walls) {
            wall[i][0]= (int) w.getX() / 25;
            wall[i][1] = (int) w.getY() / 25;
            i++;
        }
        return wall;
    }

}
