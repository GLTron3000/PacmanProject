package entity;

import game.Kernel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PacgumTest {

    @Test
    void onPick() {
        Pacgum pacgum = new Pacgum(0.0,25.0);
        Kernel k= new Kernel(45,20);
        k.pickables.add(pacgum);
        //pacgum.data.load("customLevel2.pml");

        int test = k.pickables.size();
        k.score = pacgum.onPick(k);
        assertNotEquals(test,k.pickables.size());
        assertEquals(10,k.score);
    }

    @Test
    void draw() {
    }
}