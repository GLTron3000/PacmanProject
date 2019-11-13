package scenes;

import entity.Fantom;
import entity.Pacman;
import entity.Wall;
import game.Kernel;
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
                kernel.step();

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

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
        kernel.entities.add(new Pacman(100,100));
        kernel.entities.add(new Fantom(10,10,"pedro"));
        kernel.entities.add(new Wall(200,100));
    }
    
}
