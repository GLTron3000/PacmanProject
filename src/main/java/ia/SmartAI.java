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
    Direction last_dir;
    Direction nextDirection;

    public SmartAI() {
    }

    @Override
    public Direction getMove(Kernel kernel, Fantom fantom) {
        if(directions.isEmpty()) computeDirections(kernel, fantom);

        Direction direction = directions.peek();
              
        while(!checkDirection(direction, fantom, kernel)){
            if(directions.isEmpty()) computeDirections(kernel, fantom);
            direction = directions.poll();
            //System.out.println("[SMARTAI] Change to "+direction);
        }

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

        System.out.println("[SMARTAI] pos fantome" + posX + " " + posY + "\n" + " pos pacman" + pacX + " "+ pacy);
        Astar astar = new Astar(700, 800, posX, posY,pacX , pacy, getWall(kernel.walls));
        Stack<Cell> cells = astar.process();
        PathToDirection translater = new PathToDirection(cells);
        System.out.println("[SMARTAI] path calculated");
        directions = translater.translate(astar.grid);
        directions.forEach(System.out::println);
    }

    private int[][] getWall(List<Wall> walls){
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
