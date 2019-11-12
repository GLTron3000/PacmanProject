package scenes;

import entity.Pacman;
import game.Kernel;
import java.util.Timer;
import java.util.TimerTask;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import pacmanproject.SceneController;

public class Game {
    SceneController sceneController;
    StackPane stackPane;
    Canvas canvas;
    Kernel kernel;
    Group entities;
    AnimationTimer timer;
    GraphicsContext gc;

    public Game(SceneController sceneController) {
        stackPane = new StackPane();
        canvas = new Canvas(500, 500);
        entities = new Group();
        kernel = new Kernel();
        gc = canvas.getGraphicsContext2D();
        
        stackPane.getChildren().add(canvas);
        this.sceneController = sceneController;
        
        entityInit();
        
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                Pacman pacman = (Pacman) kernel.getPacman();
                
                System.out.println(ke.getCode());
                
                
                switch (ke.getCode()){
                    case UP: pacman.goUp(); break;
                    case DOWN: pacman.goDown(); break;
                    case LEFT: pacman.goLeft(); break;
                    case RIGHT: pacman.goRight(); break;
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
                Pacman pacman = (Pacman) kernel.getPacman();
                if(kernel.collisionEngine.outOfBorad(pacman , canvas))
                    pacman.stop();
                kernel.step();
                
                gc.strokeRect(0, 0, 10, 10);
                gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
                
                

                kernel.entities.forEach((entity) -> {
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
        //loadLevel
        kernel.entities.add(new Pacman(50,50));
    }
    
}
