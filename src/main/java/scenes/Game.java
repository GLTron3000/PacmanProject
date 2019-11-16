package scenes;

import JSON.EntityJsonDeserialize;
import JSON.LevelData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import entity.Entity;
import entity.Fantom;
import entity.Pacman;
import entity.Wall;
import game.Kernel;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import pacmanproject.SceneController;

public class Game {
    SceneController sceneController;
    StackPane stackPane;
    
    Canvas canvas;
    Kernel kernel;
    
    AnimationTimer timer;
    GraphicsContext gc;
    
    public Game(SceneController sceneController) {
        stackPane = new StackPane();
        
        //28 * 32
        canvas = new Canvas(700, 800);
        kernel = new Kernel(canvas.getWidth(), canvas.getHeight());
        gc = canvas.getGraphicsContext2D();
        
        stackPane.getChildren().add(canvas);
        stackPane.getStyleClass().add("stackPane");
        stackPane.getStylesheets().add("file:src/main/css/gameStyle.css");
        this.sceneController = sceneController;
        
        entityInit();
        
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {               
                System.out.println(ke.getCode());
                
                switch (ke.getCode()){
                    case UP: kernel.pacman.goUp(); break;
                    case DOWN: kernel.pacman.goDown(); break;
                    case LEFT: kernel.pacman.goLeft(); break;
                    case RIGHT: kernel.pacman.goRight(); break;
                    case ESCAPE: sceneController.showMainMenu(); break;
                    case ENTER: break;
                    default: break;
                }
                ke.consume();

            }
        });
                
        // ! Provoque une fuite de mémoire
        // Ajouter -Dprism.order=sw
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                kernel.step();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.strokeText("Life : "+Integer.toString(kernel.pacman.life), canvas.getWidth()-40, 15);
                
                kernel.pacman.draw(gc);
                
                kernel.fantoms.forEach((entity) -> {
                    entity.draw(gc);
                });
                
                kernel.walls.forEach((entity) -> {
                    entity.draw(gc);
                });
                
                kernel.pickables.forEach((entity) -> {
                    entity.draw(gc);
                });
            }
        };
        

    }

    public StackPane getNode() {
        return stackPane;
    }
    
    public void start(){
        timer.start();
    }
    
    public void stop(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());

        timer.stop();
    }

    private void entityInit(){
        LevelData levelData = new LevelData();
        levelData.load("customLevel1.pml");

        kernel.pacman = levelData.pacman;
        kernel.fantoms = levelData.fantoms;
        kernel.walls = levelData.walls;
        kernel.pickables = levelData.pickables;
    }
    
}
