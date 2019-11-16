package scenes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Entity;
import entity.Fantom;
import entity.Fruit;
import entity.Pacgum;
import entity.Pacman;
import entity.Wall;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class LevelBuilder {
    SceneController sceneController;
    StackPane stackPane;
    Canvas canvas;

    AnimationTimer timer;
    GraphicsContext gc;
    
    double x;
    double y;
    double taille = 25;
    
    int fantomCounter = 0;
    int saveCounter = 0;
    
    ArrayList<Entity> entities;

    public LevelBuilder(SceneController sceneController) {
        stackPane = new StackPane();
        
        //28 * 32
        canvas = new Canvas(700, 800);

        gc = canvas.getGraphicsContext2D();
        
        Label titleLabel = new Label("Editeur de niveau");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(new Font(25));
        
        
        Label helpLabel = new Label("P: Pacman | F: Fantom | W: Wall | R: Fruit | G: Pacgum | D: supprimer | Enter: sauvegarder | Echap: quitter");
        helpLabel.setFont(new Font(15));
        helpLabel.setTextFill(Color.WHITE);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(titleLabel, helpLabel, canvas);
        
        stackPane.getChildren().add(vbox);
        stackPane.getStyleClass().add("stackPane");
        stackPane.getStylesheets().add("file:src/main/css/gameStyle.css");
        stackPane.setAlignment(Pos.CENTER);
        this.sceneController = sceneController;
        
        entities = new ArrayList<>();
                
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                                
                switch (ke.getCode()){
                    case UP: moveUp(); break;
                    case DOWN: moveDown(); break;
                    case LEFT: moveLeft(); break;
                    case RIGHT: moveRight(); break;
                    case ESCAPE: sceneController.showMainMenu(); break;
                    case ENTER: writeLevel(); break;
                    case F: if(!checkEntityPresence()) entities.add(new Fantom(x, y, fantomCounter+"")); fantomCounter++; break;
                    case P: if(!checkEntityPresence()) entities.add(new Pacman(x, y)); break;
                    case W: if(!checkEntityPresence()) entities.add(new Wall(x, y)); break;
                    case R: if(!checkEntityPresence()) entities.add(new Fruit(x, y)); break;
                    case G: if(!checkEntityPresence()) entities.add(new Pacgum(x, y)); break;
                    case D: deleteEntity(); break;
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

                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setStroke(Color.WHITE);
                gc.strokeRect(0, 0, canvas.getWidth(), canvas.getHeight());

                entities.forEach((entity) -> {
                    entity.draw(gc);
                });
                
                gc.setStroke(Color.RED);
                gc.strokeRect(x, y, taille, taille);
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
    
    private void deleteEntity(){
        Entity entityToDelete = null;
        for(Entity entity : entities){
            if(entity.getX() == x && entity.getY() == y) entityToDelete = entity;
        }
        
        if(entityToDelete != null) entities.remove(entityToDelete);
    }
    
    private boolean checkEntityPresence(){
        for(Entity entity : entities){
            if(entity.getX() == x && entity.getY() == y) return true;
        }
        return false;
    }
    
    private void writeLevel(){
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();
        final String json = gson.toJson(entities);
        
        saveCounter++;
        File file = new File("customLevel"+saveCounter+".pml");
        try {
            Files.write(file.toPath(), json.getBytes());
        } catch (IOException ex) {
            Logger.getLogger(LevelBuilder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void moveUp(){
        if(y != 0) y-=taille;
    }
    
    void moveDown(){
        if(y != canvas.getHeight()-taille) y+=taille;
    }
    
    void moveLeft(){
        if(x != 0) x-=taille;
    }
    
    void moveRight(){
        if(x != canvas.getWidth()-taille) x+=taille;
    }

}
