package ia;

import entity.Direction;

import java.util.ArrayList;

public class BestPath {

    ArrayList<State> open = new ArrayList();
    ArrayList<State> close = new ArrayList();


    /*
    @PARAM : Current direction of the fantom
     */
    public Direction pathfinding(Direction fDirection){

        while(!open.isEmpty()){
            State tmp = findBestElement();
            open.remove(tmp);
            close.add(tmp);
            if(tmp.heuristique==0)
                return tmp.firstDirection;

            ArrayList<State>successors = createSuccessors(fDirection);
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


    /*
    @PARAM : Current direction of the fantom

     */
    public ArrayList<State> createSuccessors(Direction fDirection){

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
        return null;
    }

    private State createDown() {
        return null;
    }

    private State createLeft() {
        return null;
    }

    private State createRight() {
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
}
