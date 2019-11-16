package JSON;

import entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LevelDataTest {
    String path="testCustomlvl1.pml";
    @Test
    void save() {
        LevelData level = new LevelData();

        List<Wall> walls = new ArrayList<>();
        List<Fantom> fantoms = new ArrayList<>();
        List<Pickable> pickables = new ArrayList<>();
        Pacman pacman = new Pacman(12,15);

        walls.add(new Wall(150,12));
        walls.add(new Wall(135,18));
        fantoms.add(new Fantom(45,96,"inky"));
        fantoms.add(new Fantom(45,74,"Blinky"));
        pickables.add(new Pacgum(12,57));
        pickables.add(new Pacgum(36,87));
        level.save(pacman, fantoms, pickables, walls, path);

    }

    @Test
    void load() {
        LevelData level = new LevelData();
        List<Wall> walls = new ArrayList<>();
        List<Fantom> fantoms = new ArrayList<>();
        List<Pickable> pickables = new ArrayList<>();
        walls.add(new Wall(150,12));
        walls.add(new Wall(135,18));
        fantoms.add(new Fantom(45,96,"inky"));
        fantoms.add(new Fantom(45,74,"Blinky"));
        pickables.add(new Pacgum(12,57));
        pickables.add(new Pacgum(36,87));
        Pacman pacman = new Pacman(12,15);


        level.load(path);
        assertEquals(level.pacman,pacman);
        assertEquals(level.walls,walls);
        assertEquals(level.fantoms,fantoms);
        assertEquals(level.pickables,pickables);
    }
}