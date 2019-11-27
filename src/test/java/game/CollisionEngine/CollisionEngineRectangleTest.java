package game.CollisionEngine;

import entity.Pacman;
import entity.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionEngineRectangleTest {

    @Test
    void isCollideSize25() {
        Engine e= new CollisionEngineRectangle();
        int size=25;
        Pacman p= new Pacman(0,0);
        p.size=size;
        Wall noContact= new Wall(100,100);
        noContact.size=size;
        assertEquals(false,e.isCollide(p,noContact));
        Wall Contact= new Wall(10,10);
        Contact.size=size;
        assertEquals(true,e.isCollide(p,Contact));
        Wall justContact = new Wall(size,size);
        justContact.size=size;
        assertEquals(true,e.isCollide(p,justContact));
        Wall barelyNoContact = new Wall(size+1,size+1);
        barelyNoContact.size=size;
        assertEquals(false,e.isCollide(p,barelyNoContact));
    }
}