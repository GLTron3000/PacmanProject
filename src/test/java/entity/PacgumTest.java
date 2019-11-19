package entity;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PacgumTest {

    @Test
    void onPick() {
        Pacgum pacgum = new Pacgum(0.0,25.0);
        int score = 0;
        //pacgum.data.load("customLevel2.pml");
        CopyOnWriteArrayList<Pickable> pickables = new CopyOnWriteArrayList<>();
        pickables.add(pacgum);
        int test = pickables.size();
        score = pacgum.onPick(pickables,score);
        assertNotEquals(test,pickables.size());
        assertEquals(10,score);
    }

    @Test
    void draw() {
    }
}