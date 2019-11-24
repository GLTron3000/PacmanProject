package ia;

import entity.Direction;
import entity.Fantom;
import game.Kernel;

public class RandomAI implements IA {
    @Override
    public Direction getMove(Kernel kernel, Fantom fantom) {
        Direction direction;
        int random;
        for (; ;) {
            random = (int) (Math.random() * 4);
            System.out.println(" random : " + random);
            switch (random) {
                case 0:
                    direction = Direction.UP;
                    return direction;

                case 1:
                    direction = Direction.DOWN;
                    return direction;

                case 2:
                    direction = Direction.LEFT;
                    return direction;

                case 3:
                    direction = Direction.RIGHT;
                    return direction;

                case 4:
                    break;
            }
        }
    }
}