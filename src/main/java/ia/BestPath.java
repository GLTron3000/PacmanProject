package ia;

import entity.Direction;
import entity.Pacman;
import entity.Wall;
import game.CollisionEngine.CollideBehavior;
import game.CollisionEngine.Engine;

import java.util.ArrayList;

import static entity.Direction.*;


public class BestPath {

    public ArrayList<State> open = new ArrayList<>();
    private ArrayList<State> close = new ArrayList<>();
    private double x;
    private double y;
    private Direction fDirection;
    ArrayList<Wall> walls;
    Engine engine;
    CollideBehavior collBeha;


    public BestPath(double x, double y, Direction fDirection, ArrayList<Wall> walls, Engine engine, CollideBehavior collBeha) {
        this.x = x;
        this.y = y;
        this.fDirection = fDirection;
        this.walls = walls;
        this.engine = engine;
        this.collBeha = collBeha;
    }

    /*
    Only for tests
     */

    public BestPath(Direction fDirection) {
        this.fDirection = fDirection;
    }

    public Direction pathfinding(){

        open.add(new State(fDirection));
        while(!open.isEmpty()){
            State tmp = findBestElement();
            open.remove(tmp);
            close.add(tmp);
            if(tmp.heuristique==0)
                return tmp.firstDirection;

            ArrayList<State>successors = createSuccessors();
            for(State s : successors){

                if(!close.contains(s)) {
                    s.computeHeuristic();
                    open.add(s);
                }
                //faire le 4b
            }
        }
        return null;
    }


    public ArrayList<State> createSuccessors(){

        ArrayList<State> tmp = new ArrayList<>();
        switch(fDirection){

            case UP: tmp.add(createLeft());
                     tmp.add(createUp());
                     tmp.add(createRight());
                     break;

            case DOWN: tmp.add(createLeft());
                       tmp.add(createUp());
                       tmp.add(createRight());
                       break;

            case LEFT: tmp.add(createDown());
                       tmp.add(createLeft());
                       tmp.add(createUp());
                       break;


            case RIGHT: tmp.add(createUp());
                        tmp.add(createRight());
                        tmp.add(createDown());
                        break;
        }
        return tmp;
    }

    private State createUp() {
        if(checkNextMove()==true){
            return new State(UP);
        }
        return null;
    }

    private State createDown() {
        if(checkNextMove()==true){
            return new State(DOWN);
        }
        return null;
    }

    private State createLeft() {
        if(checkNextMove()==true){
            return new State(LEFT);
        }
        return null;
    }

    private State createRight() {
        if(checkNextMove()==true){
            return new State(RIGHT);
        }
        return null;
    }


    //Find the State with the best heuristic in "open"
    public State findBestElement(){

        State tmp = open.get(0);

        for(int i=1;i<open.size();i++){
            if(open.get(i).heuristique<tmp.heuristique){
                tmp=open.get(i);
            }
        }
        return tmp;
    }

    /*
    Check if it's possible to move in "fDirection"
     */
    private boolean checkNextMove(){

        Pacman nextPacman = new Pacman(x, y);
        nextPacman.direction = fDirection;

        for(Wall w : walls){
            if(engine.isCollide(nextPacman, w)) collBeha.collideMovableWall(nextPacman, w);
        }

        if(nextPacman.direction == fDirection){
            return true;
        }
        else return false;
    }


}
