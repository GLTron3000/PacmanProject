package scenes;

import JSON.LevelData;
import static entity.Direction.*;
import game.Kernel;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
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
    VBox pauseMenu;
    
    Canvas canvas;
    Kernel kernel;
    
    AnimationTimer timer;
    GraphicsContext gc;
    
    boolean endGame;
    EventHandler<KeyEvent> keyboardHandler;
    EventHandler<KeyEvent> keyboardPauseHandler;
    
    public Game(SceneController sceneController) {
        endGame = false;
        
        guiInit();
        
        this.sceneController = sceneController;
        
        entityInit();
        
        keyboardInit();
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
        
        timerInit();
    }

    public StackPane getNode() {
        return stackPane;
    }
    
    public void start(){
        timer.start();
    }
    
    public void pause(){
        timer.stop();
    }
    
    public void stop(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());

        timer.stop();
        sceneController.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
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
            case GAMEOVER: endGame = true; showEndMessage("GAME OVER"); timer.stop(); break;
            case VICTORY: endGame = true; showEndMessage("VICTOIRE !"); timer.stop(); break;
            case PAUSE: break;
        }
    }
    
    private void showEndMessage(String message){       
        Label messageLabel = new Label(message);
        messageLabel.setFont(new Font(120));
        messageLabel.setTextFill(Color.WHITE);
        
        Label helpLabel = new Label("Appuyer sur une touche pour continuer");
        helpLabel.setFont(new Font(25));
        helpLabel.setTextFill(Color.WHITE);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(messageLabel, helpLabel);
        
        
        stackPane.getChildren().add(vbox);
    }
    
    
    
    private void pauseGame(){
        pause();
        
        sceneController.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyboardPauseHandler);
        
        stackPane.getChildren().add(pauseMenu);
    }
    
    private void unPauseGame(){
        stackPane.getChildren().remove(pauseMenu);
        
        sceneController.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyboardPauseHandler);
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
        
        start();
    }
    
    private void entityInit(){
        LevelData levelData = new LevelData();
        levelData.load("level1.pml");

        kernel.pacman = levelData.pacman;
        kernel.fantoms = levelData.fantoms;
        kernel.walls = levelData.walls;
        kernel.pickables = new CopyOnWriteArrayList(levelData.pickables);
    }
    
    private void guiInit(){
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
        
        pauseMenuInit();
    }
    
    private void pauseMenuInit(){
        Label pauseLabel = new Label("Pause !");
        pauseLabel.setFont(new Font(100));
        pauseLabel.setTextFill(Color.WHITE);
        
        Label hintLabel = new Label("Appuyer sur echap pour continuer");
        hintLabel.setFont(new Font(35));
        hintLabel.setTextFill(Color.WHITE);
        
        Label hint2Label = new Label("ou sur enter pour quitter");
        hint2Label.setFont(new Font(35));
        hint2Label.setTextFill(Color.WHITE);
        
        pauseMenu = new VBox();
        pauseMenu.setAlignment(Pos.CENTER);
        pauseMenu.setSpacing(10);
        pauseMenu.getChildren().addAll(pauseLabel, hintLabel, hint2Label);
    }

    private void keyboardInit() {
        keyboardHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {     
                System.out.println(ke.getCode());

                if(endGame){
                    sceneController.showMainMenu();
                    endGame = false;
                }else{
                    moveInput(ke);
                }
                
                ke.consume();
            }
        };
        
        keyboardPauseHandler = new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {     
                System.out.println(ke.getCode());

                pauseInput(ke);
                
                ke.consume();
            }
        };
    }
    
    private void moveInput(KeyEvent ke){
        switch (ke.getCode()){
            case UP: kernel.pacman.goUp(); break;
            case DOWN: kernel.pacman.goDown(); break;
            case LEFT: kernel.pacman.goLeft(); break;
            case RIGHT: kernel.pacman.goRight(); break;
            case ESCAPE: pauseGame(); break;
            default: break;
        }
    }
    
    private void pauseInput(KeyEvent ke){
        switch (ke.getCode()){
            case ESCAPE: unPauseGame(); break;
            case ENTER: sceneController.showMainMenu(); break;
            default: break;
        }
    }

    private void timerInit() {
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
}
