package scenes;

import JSON.LevelData;

import entity.Fruit;
import entity.Wall;
import game.Kernel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
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
    Label timerLabel;
    Label powerUpReductorLabel;
    VBox pauseMenu;
    
    Canvas canvas;
    Kernel kernel;
    
    AnimationTimer kernelTimer;
    Timer chronoTimer;
    Timer animationTimer;
    GraphicsContext gc;
    
    boolean endGame;
    EventHandler<KeyEvent> keyboardHandler;
    EventHandler<KeyEvent> keyboardPauseHandler;
    
    private final long[] frameTimes = new long[100];
    private int frameTimeIndex = 0;
    private boolean arrayFilled = false;
    
    public Game(SceneController sceneController) {
        endGame = false;
        
        guiInit();
        
        this.sceneController = sceneController;
        
        entityInit();
        
        keyboardInit();
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
        
        kernelTimerInit(); 
        
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        drawAllEntity();
        
        animationTimerInit();
    }

    public StackPane getNode() {
        return stackPane;
    }
    
    public void start(){
        chronoInit();
        kernelTimer.start();
    }
    
    public void pause(){
        kernelTimer.stop();
        chronoTimer.cancel();
        animationTimer.cancel();
    }
    
    public void stop(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());

        kernelTimer.stop();
        chronoTimer.cancel();
        animationTimer.cancel();
        sceneController.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
    }
    
    private void drawAllEntity(){
        drawPickables();
        drawMovable();

        kernel.walls.forEach((entity) -> {
            entity.draw(canvas);
        });
    }
    
    private void drawMovable(){
        kernel.pacman.draw(canvas);

        kernel.fantoms.forEach((entity) -> {
            entity.draw(canvas);
        });
    }
    
    private void drawPickables(){
        kernel.pickables.forEach((entity) -> {
            entity.draw(canvas);
        });
    }
    
    private void checkState(){
        switch(kernel.gameState){
            case PLAY: break;
            case GAMEOVER: endGame = true; showEndMessage("GAME OVER"); kernelTimer.stop(); chronoTimer.cancel(); break;
            case VICTORY: endGame = true; showEndMessage("VICTOIRE !"); kernelTimer.stop(); chronoTimer.cancel(); break;
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
        levelData.load("customLevel1.pml");

        kernel.pacman = levelData.pacman;
        kernel.fantoms = new CopyOnWriteArrayList(levelData.fantoms);
        kernel.walls = levelData.walls;
        kernel.pickables = new CopyOnWriteArrayList(levelData.pickables);
        
        kernel.pacman.loadTexture();
        kernel.pickables.forEach(pickable -> pickable.loadTexture());
        kernel.fantoms.forEach(fantom -> fantom.loadTexture());
        kernel.walls.forEach(wall -> wall.loadTexture());
        
        kernel.setFantomIA();
    }
    
    private void guiInit(){
        stackPane = new StackPane();
        
        //28 * 32
        canvas = new Canvas(700, 775);
        kernel = new Kernel(canvas.getWidth(), canvas.getHeight());
        gc = canvas.getGraphicsContext2D();
        
        lifeLabel = new Label();
        lifeLabel.setTextFill(Color.WHITE);
        lifeLabel.setFont(new Font(35));
        
        scoreLabel = new Label();
        scoreLabel.setFont(new Font(100));
        scoreLabel.setTextFill(Color.WHITE);
        
        timerLabel = new Label();
        timerLabel.setFont(new Font(50));
        timerLabel.setTextFill(Color.WHITE);
        
        powerUpReductorLabel = new Label();
        powerUpReductorLabel.setFont(new Font(35));
        powerUpReductorLabel.setTextFill(Color.WHITE);
                
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(scoreLabel, timerLabel, lifeLabel, canvas, powerUpReductorLabel);
        
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
            case Z: kernel.pacman.goUp(); break;
            case S: kernel.pacman.goDown(); break;
            case Q: kernel.pacman.goLeft(); break;
            case D: kernel.pacman.goRight(); break;
            case A: kernel.activateReductorPowerUp(); break;
            case E: kernel.activateWallPowerUp(); break;
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

    private void kernelTimerInit() {
        // ! Provoque une fuite de mémoire
        // Ajouter -Dprism.order=sw
        kernelTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                kernel.step();
                
                lifeLabel.setText(kernel.pacman.getLife()+" vies");
                scoreLabel.setText(""+kernel.score);
                timerLabel.setText(kernel.timer+" s");
                powerUpReductorLabel.setText(kernel.pacman.getPowerUpReductor()+" Reductor PowerUp");
                        
                checkState();
                
                long oldFrameTime = frameTimes[frameTimeIndex] ;
                frameTimes[frameTimeIndex] = now ;
                frameTimeIndex = (frameTimeIndex + 1) % frameTimes.length ;
                if (frameTimeIndex == 0) {
                    arrayFilled = true ;
                }
                if (arrayFilled) {
                    long elapsedNanos = now - oldFrameTime ;
                    long elapsedNanosPerFrame = elapsedNanos / frameTimes.length ;
                    double frameRate = 1_000_000_000.0 / elapsedNanosPerFrame ;
                    //System.out.println(String.format("%.3f fps", frameRate));
                }
            }
        };
    }

    private void chronoInit() {
        chronoTimer = new Timer();
        
        chronoTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(kernel.timer != 0) kernel.timer--;
            }
        }, 1, 1000);
    }
    
    private void animationTimerInit() {
        animationTimer = new Timer();
        
        animationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                drawPickables();
                drawMovable();
                //gc.clearRect(0, 0, canvas.getHeight(), canvas.getWidth());
                //gc.setFill(Color.BLACK);
                //gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                //drawAllEntity();
            }
        }, 1, 50);
    }
    
}
