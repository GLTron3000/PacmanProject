package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PacgumTest {

    @Test
    void onPick() {
        Pacgum pacgum = new Pacgum(0.0,25.0);
        pacgum.data.load("customLevel2.pml");
        int test = pacgum.data.pickables.size();
        pacgum.onPick();
        assertNotEquals(test,pacgum.data.pickables.size());
    }

    @Test
    void draw() {
    }
}