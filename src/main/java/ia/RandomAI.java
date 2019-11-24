package ia;

import entity.Direction;
import entity.Fantom;
import entity.Pacman;
import entity.Wall;
import game.Kernel;

import java.rmi.dgc.Lease;
import java.util.Random;

import static entity.Direction.LEFT;
import static entity.Direction.STOP;
import static entity.Direction.UP;

public class RandomAI implements IA {

    Fantom f;
    Kernel kernel;
    int first=0;

    public RandomAI(Kernel kernel) {
        this.kernel = kernel;
    }

/*    @Override
    public Direction getMove(Kernel kernel, Fantom f) {

        this.f = f;
        if(first==0){
            first=1;
            //kernel.fantoms.get(f).direction=UP;
            return Direction.UP;
        }
        else {

            if (first == 1) {

                first = 50;
                int random = (int) (Math.random() * 2);
                switch (random) {
                    case 0:
                        return Direction.LEFT;

                    case 1:
                        return Direction.RIGHT;

                    default:
                        return LEFT;
                }

            }

            if (f.direction != STOP)
                return f.direction;

            int random;

            random = (int) (Math.random() * 4);
            System.out.println(" random : " + random);
            switch (random) {
                case 0:
                    checkNextMove(Direction.UP);
                    return Direction.UP;


                case 1:
                    checkNextMove(Direction.DOWN);
                    return Direction.DOWN;

                case 2:
                    checkNextMove(Direction.LEFT);
                    return Direction.LEFT;

                case 3:
                    checkNextMove(Direction.RIGHT);
                    return Direction.RIGHT;

                case 4:
                    break;
            }
        }

        return Direction.STOP;

    }*/


    private void checkNextMove(Direction d){

        Fantom nextFantom = new Fantom(f.getX(), f.getY(),"test");
        nextFantom.direction = d;

        for(Wall w : kernel.walls){
            if(kernel.engine.isCollide(nextFantom, w))
                kernel.collBeha.collideMovableWall(nextFantom, w);
        }

        if(nextFantom.direction != STOP){
            f.direction=d;
        }

        f.direction = Direction.STOP;
    }


    @Override
    public Direction getMove(Kernel kernel, Fantom fantom) {

        this.f = fantom;
        if(first==0){
            first=1;
            System.out.println("FIRST");
            //kernel.fantoms.get(f).direction=UP;
            return Direction.UP;
        }
        else {

            if (f.direction != STOP) {
                return f.direction;
            }


            if (first == 1) {

                first = 50;
                Random random = new Random();
                int test = random.nextInt(2);
                switch (test) {
                    case 0:
                        return Direction.LEFT;

                    case 1:
                        return Direction.RIGHT;

                    default:
                        return LEFT;
                }
            }


            Random random = new Random();
            int test = random.nextInt(4);

            switch (test) {
                case 0:
                    checkNextMove(Direction.UP);
                    return Direction.UP;


                case 1:
                    checkNextMove(Direction.DOWN);
                    return Direction.DOWN;

                case 2:
                    checkNextMove(Direction.LEFT);
                    return Direction.LEFT;

                case 3:
                    checkNextMove(Direction.RIGHT);
                    return Direction.RIGHT;

                default:
                    break;
            }

        }

        return Direction.UP;

    }
}
