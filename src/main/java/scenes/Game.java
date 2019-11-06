package scenes;

import entity.Pacman;
import game.Kernel;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import pacmanproject.SceneController;

import static javafx.scene.input.KeyCode.*;

public class Game {
    SceneController sceneController;
    StackPane stackPane;
    Canvas canvas;
    Kernel kernel;

    public Game(SceneController sceneController) {
        stackPane = new StackPane();
        canvas = new Canvas();
        
        stackPane.getChildren().add(canvas);
        this.sceneController = sceneController;

        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                Pacman pacman = (Pacman) kernel.getPacman();
                switch (ke.getCode()){
                    case KP_UP: pacman.goUp(); break;
                    case KP_DOWN: pacman.goDown(); break;
                    case KP_LEFT: pacman.goLeft(); break;
                    case KP_RIGHT: pacman.goRight(); break;
                    case ESCAPE: break;
                    case ENTER: break;
                    default: break;
                }
                ke.consume();
            }
        });
    }

    public StackPane getNode() {
        return stackPane;
    }
    
    void refresh(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        kernel.entities.forEach((entity) -> {
            entity.draw(gc);
        });
    }

    private void entityInit(){
        //loadLevel
        kernel.entities.add(new Pacman(0,0));
    }
    
}
