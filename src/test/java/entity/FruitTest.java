package entity;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FruitTest {

    @Test
    void onPick() {
        Fruit fruit = new Fruit(0.0,25.0);
        int score = 0;
        CopyOnWriteArrayList<Pickable> pickables = new CopyOnWriteArrayList<>();
        pickables.add(fruit);
        int test = pickables.size();
        score = fruit.onPick(pickables,score);
        assertNotEquals(test,pickables.size());
        assertEquals(50,score);
    }

    @Test
    void draw() {
    }
}