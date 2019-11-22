package ia;

import entity.Direction;
import entity.Fantom;
import entity.Pacman;


//State of the greedy algorithm
public class State {

    double heuristique;
    Direction firstDirection;
    Fantom f;
    Pacman p;

    public State(Pacman pacman, Fantom fantom, Direction d){
        p=pacman;
        f=fantom;
        firstDirection = d;
    }

    void computeHeuristic(){
        heuristique = Math.abs(p.getX()-f.getX())+Math.abs(p.getY()-f.getY());
    }

    void computeHeuristic2(){
        heuristique = Math.sqrt(Math.pow( p.getX()+f.getX() , 2) + Math.pow(p.getY() + f.getY(), 2));
    }
}
