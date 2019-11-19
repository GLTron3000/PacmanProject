package entity;

import game.Kernel;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    void onPick() {
        Fruit fruit = new Fruit(0.0,25.0);
        Kernel k= new Kernel(10,10);
        int score = 0;
        k.pickables.add(fruit);
        int test = k.pickables.size();
        fruit.onPick(k);
        assertNotEquals(test,k.pickables.size());
        assertEquals(50,k.score);
    }

    @Test
    void draw() {
    }
}