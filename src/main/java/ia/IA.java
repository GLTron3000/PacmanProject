package ia;

import entity.Direction;
import entity.Fantom;
import game.Kernel;

public interface IA {
     Direction getMove(Kernel kernel, Fantom fantom);
}
