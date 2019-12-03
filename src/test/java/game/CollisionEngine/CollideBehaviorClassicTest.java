package game.CollisionEngine;

import entity.Direction;
import entity.Pacman;
import entity.Wall;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
class CollideBehaviorClassicTest {
    CollideBehavior c= new CollideBehaviorClassic();
    

    @Test
    void collideMovableWallStandardSize() {

        double size=25;
        Pacman p = new Pacman(0,0);
        Wall up = new Wall(0,-25);
        Wall down = new Wall(0,25);
        Wall left = new Wall(-25,0);
        Wall right = new Wall(25,0);
        //UP
        p.direction=Direction.UP;
        c.collideMovableWall(p,down);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,right);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,left);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,up);
        assertEquals(p.getDirection(),Direction.STOP);
        //DOWN
        p.direction=Direction.DOWN;
        c.collideMovableWall(p,up);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,right);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,left);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,down);
        assertEquals(p.getDirection(),Direction.STOP);
        //LEFT
        p.direction=Direction.LEFT;
        c.collideMovableWall(p,up);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,right);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,down);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,left);
        assertEquals(p.getDirection(),Direction.STOP);
        //RIGHT
        p.direction=Direction.RIGHT;
        c.collideMovableWall(p,up);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,left);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,down);
        assertNotEquals(p.getDirection(),Direction.STOP);
        c.collideMovableWall(p,right);
        assertEquals(p.getDirection(),Direction.STOP);
    }

    @Test
    void collideMovableWallUP() {
        Pacman p = new Pacman(0,0);
        Wall up = new Wall(0,-25);
        p.size=25;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }

    }
    @Test
    void collideMovableWallDown() {
        Pacman p = new Pacman(0,0);
        Wall down = new Wall(0,25);
        p.size=25;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
    }

    @Test
    void collideMovableWallLeft() {
        Pacman p = new Pacman(0,0);
        Wall left = new Wall(-25,0);
        p.size=25;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
    }
    @Test
    void collideMovableWallRight() {
        Pacman p = new Pacman(0,0);
        Wall right = new Wall(25,0);
        p.size=25;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertEquals(p.getDirection(),Direction.STOP);
        }
    }
    @Test
    void collideMovableWallUPhalfSize() {
        Pacman p = new Pacman(0,0);
        Wall up = new Wall(0,-25);
        p.size=25.0/2;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setX(i);
            c.collideMovableWall(p,up);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }

    }
    @Test
    void collideMovableWallDownhalfSize() {
        Pacman p = new Pacman(0,0);
        Wall down = new Wall(0,12.5);
        p.size=25.0/2;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setX(i);
            c.collideMovableWall(p,down);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
    }

    @Test
    void collideMovableWallLefthalfSize() {
        Pacman p = new Pacman(0,0);
        Wall left = new Wall(-25,0);
        p.size=25.0/2;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setY(i);
            c.collideMovableWall(p,left);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
    }
    @Test
    void collideMovableWallRighthalfSize() {
        Pacman p = new Pacman(0,0);
        Wall right = new Wall(12.5,0);
        p.size=25.0/2;

        for(int i =0;i<25;i++){
            p.direction=Direction.UP;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.DOWN;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.LEFT;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertNotEquals(p.getDirection(),Direction.STOP);
        }
        for(int i =0;i<25;i++){
            p.direction=Direction.RIGHT;
            p.setY(i);
            c.collideMovableWall(p,right);
            assertEquals(p.getDirection(),Direction.STOP);
        }
    }

}
