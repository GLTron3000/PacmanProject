package ia;

import entity.Direction;


import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

class PathToDirection {
    private LinkedBlockingQueue<Direction> directions = new LinkedBlockingQueue<>();
    private Stack<Cell> cells;

    PathToDirection(Stack<Cell> cells) {
        this.cells = cells;
    }

    LinkedBlockingQueue<Direction> translate(Cell [][] wall){
        Cell c1 = cells.pop();
        Cell c2 = cells.pop();
        //System.out.println(" c1 : " + c1.toString() + "c2: " + c2.toString());
        int test = 0;

            do {
                //System.out.println(" c1 : " + c1.toString() + "c2: " + c2.toString());

                if (c1.x < c2.x && c1.y == c2.y){
                    //for(int i = 0; i < 25 ; i++)
                        directions.add(Direction.RIGHT);
                    //System.out.println(" Down add");
                }

                if (c1.x > c2.x && c1.y == c2.y) {
                    //for(int i = 0; i < 25 ; i++)
                        directions.add(Direction.LEFT);
                  //  System.out.println(" UP add");
                }

                if (c1.y < c2.y && c1.x == c2.x) {
                    //for(int i = 0; i < 25 ; i++)
                        directions.add(Direction.DOWN);
                    //System.out.println(" Right add");
                }

                if (c1.y > c2.y && c1.x == c2.x) {
                    //for(int i = 0; i < 25 ; i++)
                        directions.add(Direction.UP);
                    //System.out.println(" LEFT add");
                }

                if (c1.x > c2.x && c1.y > c2.y) { // diagonale haut-gauche
                    if(wall[c2.x][c1.y] == null){
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.LEFT);
                        }
                        for(int i = 0; i < 25 ; i++){
                            directions.add(Direction.UP);
                        }
                    }
                    else {
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.UP);
                        }
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.LEFT);
                        }
                    }
                }

                if (c1.x > c2.x && c1.y < c2.y) { // diagonale haut-droite
                    if(wall[c2.x][c1.y] == null){
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.LEFT);
                        }
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.DOWN);
                        }
                    }
                    else {
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.DOWN);
                            }
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.LEFT);
                        }
                    }
                }

                if (c1.x < c2.x && c1.y > c2.y) { // diagonale bas-gauche
                    if(wall[c1.x][c2.y] == null){
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.RIGHT);
                        }
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.UP);
                        }

                    }
                    else {
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.UP);
                        }
                        for(int i = 0; i < 25 ; i++){
                            directions.add(Direction.RIGHT);
                        }
                    }
                }

                if (c1.x < c2.x && c1.y < c2.y) {  // diagonale bas-droite
                    if(wall[c1.x][c2.y] == null){
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.RIGHT);
                        }
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.DOWN);
                        }
                    }
                    else {
                        for(int i = 0; i < 25 ; i++) {
                            directions.add(Direction.DOWN);
                            directions.add(Direction.RIGHT);
                        }

                    }
                }

                if(cells.isEmpty())
                    test = 1;
                else {
                    //System.out.println();
                    //displayCell();
                    //System.out.println();
                    c1 = c2;
                    c2 = cells.pop();
                }
            }while (test == 0);

        return directions;
    }

    void displayCell(){
        for (Cell c:cells
             ) {
            System.out.println(c.toString());
        }
    }
}
