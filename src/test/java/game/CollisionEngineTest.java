package game;

import entity.Direction;
import entity.Entity;
import entity.Fantom;
import entity.Movable;
import entity.Pacman;
import entity.Wall;
import javafx.scene.canvas.Canvas;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CollisionEngineTest {

    @Test
    void outOfBoard() {
        CollisionEngine collisionEngine = new CollisionEngine();
        
        Canvas canvas = new Canvas(70, 80);
        Pacman pacman = new Pacman(40,40);
        
        //pacman ne touche aucun bord
        assertFalse(collisionEngine.outOfBoard(pacman, canvas.getWidth(), canvas.getHeight()));
        
        Pacman pacman2 = new Pacman(0,40);
        
        //pacman touche un mur mais ne se déplace pas dans sa direction
        pacman2.goUp();
        assertFalse(collisionEngine.outOfBoard(pacman2, canvas.getWidth(), canvas.getHeight()));
        
        //pacman touche un mur et se déplace dans sa direction
        pacman2.goLeft();
        assertTrue(collisionEngine.outOfBoard(pacman2, canvas.getWidth(), canvas.getHeight()));
        
    }

    @Test
    void isCollide() {
        CollisionEngine collisionEngine= new CollisionEngine();

        //net collision
        Entity wall = new Wall(10,10);
        Entity pacman = new Pacman(20,10);


        assertTrue(collisionEngine.isCollide(wall,pacman));

        // pas de collision
        Entity wall2 = new Wall(33,54);
        Entity pacman2 = new Pacman(98,26);
        assertFalse(collisionEngine.isCollide(wall2,pacman2));

        //se touche mais ne se rentre pas dedans size =25
        Entity wall3 = new Wall(30,50);
        Entity pacman3 = new Pacman(55,50);
        assertFalse(collisionEngine.isCollide(wall3,pacman3));

        //se rentre dedans de 1 pixel size =25
        Entity wall4 = new Wall(30,50);
        Entity pacman4 = new Pacman(54,50);
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
    
    @Test
    void collidePacmanFantom(){
        CollisionEngine collisionEngine = new CollisionEngine();
        
        Pacman pacman = new Pacman(40,40);
        //Entity fant = new Fantom(30,40,"Pinky");
        int result = 2;
        //collisionEngine.collidePacmanFantom(pacman);
        
        assertEquals(pacman.life,result);
        
        //collisionEngine.collidePacmanFantom(pacman);
         result = 1;
        
        assertEquals(pacman.life,result);
        
        
    }
}