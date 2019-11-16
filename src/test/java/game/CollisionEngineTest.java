package game;

import entity.Entity;
import entity.Pacman;
import entity.Wall;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionEngineTest {

    @Test
    void outOfBoard() {
    }

    @Test
    void isCollide() {
        CollisionEngine collisionEngine= new CollisionEngine();

        //net collision
        Entity wall = new Wall(10,10);
        Entity pacman = new Pacman(40,10);


        assertTrue(collisionEngine.isCollide(wall,pacman));

        // pas de collsion
        Entity wall2 = new Wall(33,54);
        Entity pacman2 = new Pacman(98,26);
        assertFalse(collisionEngine.isCollide(wall2,pacman2));

        //se touche mais ne se rentre pas dedans
        Entity wall3 = new Wall(30,50);
        Entity pacman3 = new Pacman(80,50);
        assertFalse(collisionEngine.isCollide(wall3,pacman3));

        //se rentre dedans de 1 pixel
        Entity wall4 = new Wall(30,50);
        Entity pacman4 = new Pacman(79,50);
        assertTrue(collisionEngine.isCollide(wall4,pacman4));
    }

    @Test
    void distanceEntities() {
        CollisionEngine col= new CollisionEngine();

        Entity wall = new Wall(10,10);
        Entity pacman = new Pacman(40,10);

        assertEquals(30,col.distanceEntities(wall,pacman),0.001);

        Entity wall2 = new Wall(3,4);
        Entity pacman2 = new Pacman(4,3);

        assertEquals(Math.sqrt(2),col.distanceEntities(wall2,pacman2),0.001);

        Entity wall3 = new Wall(33,54);
        Entity pacman3 = new Pacman(98,26);
        assertEquals(70.774,col.distanceEntities(wall3,pacman3),0.001);
    }
}