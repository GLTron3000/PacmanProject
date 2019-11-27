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

        //Le fantome cherche à sortir de l'enclos pour le premier niveau
        if(first==0){
            //Si le fantome est trop à droite
            if(fantom.getX()<340){
                return Direction.RIGHT;
            }
            //Si le fantome est trop à gauche
            if(fantom.getX()>360){
                return Direction.LEFT;
            }
            //Si le fantome est positionné en dessous de la sortie
            if(fantom.getX()>340 && fantom.getX()<360) {

                first = 1;
                System.out.println("FIRST");
                return Direction.UP;
            }
        }
        else {

            if (f.direction != STOP) {
                return f.direction;
            }

            //Le fantome va vers la haut pour sortir de l'enclos et part a droite ou à gauche
            if (first == 1) {

                first = 2;
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


            //Le fantome n'est plus dans l'enclos et à une direction aléatoire
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
