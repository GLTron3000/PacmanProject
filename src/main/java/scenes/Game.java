package scenes;

import JSON.LevelData;
import game.Kernel;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pacmanproject.SceneController;

public class Game {
    SceneController sceneController;
    StackPane stackPane;
    Label lifeLabel;
    Label scoreLabel;
    
    Canvas canvas;
    Kernel kernel;
    
    AnimationTimer timer;
    GraphicsContext gc;
    
    boolean endGame;
    
    public Game(SceneController sceneController) {
        endGame = false;
        stackPane = new StackPane();
        
        //28 * 32
        canvas = new Canvas(700, 800);
        kernel = new Kernel(canvas.getWidth(), canvas.getHeight());
        gc = canvas.getGraphicsContext2D();
        
        lifeLabel = new Label();
        lifeLabel.setTextFill(Color.WHITE);
        lifeLabel.setFont(new Font(35));
        
        scoreLabel = new Label();
        scoreLabel.setFont(new Font(100));
        scoreLabel.setTextFill(Color.WHITE);
                
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(scoreLabel, lifeLabel, canvas);
        
        stackPane.getChildren().add(vbox);
        stackPane.getStyleClass().add("stackPane");
        stackPane.getStylesheets().add("file:src/main/css/gameStyle.css");
        this.sceneController = sceneController;
        
        entityInit();
        
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {     
                System.out.println(ke.getCode());
                
                if(endGame){
                    sceneController.showMainMenu();
                }else{
                    switch (ke.getCode()){
                        case UP: kernel.pacman.goUp(); break;
                        case DOWN: kernel.pacman.goDown(); break;
                        case LEFT: kernel.pacman.goLeft(); break;
                        case RIGHT: kernel.pacman.goRight(); break;
                        case ESCAPE: sceneController.showMainMenu(); break;
                        case ENTER: break;
                        default: break;
                    }
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
                
                lifeLabel.setText(kernel.pacman.life+" vies");
                scoreLabel.setText(""+kernel.score);

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                drawAllEntity();
               
                checkState();
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
    
    private void drawAllEntity(){
        kernel.pickables.forEach((entity) -> {
            entity.draw(gc);
        });

        kernel.pacman.draw(gc);

        kernel.fantoms.forEach((entity) -> {
            entity.draw(gc);
        });

        kernel.walls.forEach((entity) -> {
            entity.draw(gc);
        });
    }
    
    private void checkState(){
        switch(kernel.gameState){
            case PLAY: break;
            case GAMEOVER: endGame = true; drawGameOver(); timer.stop(); break;
            case VICTORY: endGame = true; drawVictory(); timer.stop(); break;
            case PAUSE: break;
        }
    }
    
    private void drawGameOver(){
        canvas.getGraphicsContext2D().strokeText("GAME OVER", canvas.getHeight()/2, canvas.getWidth()/2);
    }
    
    private void drawVictory(){
        canvas.getGraphicsContext2D().strokeText("VICTOIRE", canvas.getHeight()/2, canvas.getWidth()/2);
    }
}
