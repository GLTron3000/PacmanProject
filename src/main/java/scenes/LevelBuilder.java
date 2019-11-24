package scenes;

import JSON.LevelData;
import entity.Entity;
import entity.Fantom;
import entity.Fruit;
import entity.Pacgum;
import entity.Pacman;
import entity.Pickable;
import entity.Wall;
import java.io.File;
import java.util.ArrayList;
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
import javafx.stage.FileChooser;
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
    
    double pickableOffset = 7;
    
    int fantomCounter = 0;
    
    ArrayList<Entity> entities;
    
    EventHandler<KeyEvent> keyboardHandler;

    public LevelBuilder(SceneController sceneController) {
        stackPane = new StackPane();
        
        //28 * 32
        canvas = new Canvas(700, 800);

        gc = canvas.getGraphicsContext2D();
        
        Label titleLabel = new Label("Editeur de niveau");
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setFont(new Font(40));
        
        
        Label helpLabel = new Label("P: Pacman | F: Fantom | W: Wall | R: Fruit | G: Pacgum | D: supprimer | Enter: sauvegarder | L: charger | Echap: quitter");
        helpLabel.setFont(new Font(15));
        helpLabel.setTextFill(Color.WHITE);
        
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(titleLabel, helpLabel, canvas);
        
        stackPane.getChildren().add(vbox);
        stackPane.getStyleClass().add("stackPane");
        stackPane.getStylesheets().add("file:src/main/css/levelBuilderStyle.css");
        stackPane.setAlignment(Pos.CENTER);
        this.sceneController = sceneController;
        
        entities = new ArrayList<>();
        
        keyboardHandler = (KeyEvent ke) -> {
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
                case R: if(!checkEntityPresence()) entities.add(new Fruit(x+pickableOffset, y+pickableOffset)); break;
                case G: if(!checkEntityPresence()) entities.add(new Pacgum(x+pickableOffset, y+pickableOffset)); break;
                case L: loadLevel(); break;
                case D: deleteEntity(); break;
                default: break;
            }
            ke.consume();
        };
                
        sceneController.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
                
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
                    entity.draw(canvas);
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
        sceneController.getScene().removeEventFilter(KeyEvent.KEY_PRESSED, keyboardHandler);
    }
    
    private void deleteEntity(){
        Entity entityToDelete = null;
        for(Entity entity : entities){
            if(entity.getX() == x && entity.getY() == y) entityToDelete = entity;
            else if(entity.getX() == x+pickableOffset && entity.getY() == y+pickableOffset) entityToDelete = entity;
        }
        
        if(entityToDelete != null) entities.remove(entityToDelete);
    }
    
    private boolean checkEntityPresence(){
        for(Entity entity : entities){
            if(entity.getX() == x && entity.getY() == y) return true;
            else if(entity.getX() == x+pickableOffset && entity.getY() == y+pickableOffset) return true;
        }
        return false;
    }
    
    private void writeLevel(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un niveau");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PacmanLevel", "*.pml"));
        File file = fileChooser.showSaveDialog(sceneController.stage);
        
        if(file == null) return;
        
        LevelData levelData = new LevelData();
        
        Pacman pacman = null;
        ArrayList<Fantom> fantoms = new ArrayList<>();
        ArrayList<Pickable> pickables = new ArrayList<>();
        ArrayList<Wall> walls = new ArrayList<>();
        
        for(Entity entity : entities){
            if(entity instanceof Pacman){
                entity.setTexturePath("assets/Pacman/PacmanFull.png");
                pacman = (Pacman) entity;
            }
            else if(entity instanceof Fruit){
                entity.setTexturePath("assets/Pickable/Fruit.png");
                pickables.add((Pickable) entity);
            }else if(entity instanceof Pacgum){
                entity.setTexturePath("assets/Pickable/Pacgum.png");
                pickables.add((Pickable) entity);
            }
            else if(entity instanceof Fantom) fantoms.add((Fantom) entity);
            else if(entity instanceof Wall) walls.add((Wall) entity);
            
        }
        
        setWallTexture(walls);
        setFantomTextureAndIA(fantoms);
        
        levelData.save(pacman, fantoms, pickables, walls, file.getPath());
    }
    
    private void loadLevel(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Charger un niveau");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PacmanLevel", "*.pml"));
        File file = fileChooser.showOpenDialog(sceneController.stage);
        
        if(file == null) return;
        
        LevelData levelData = new LevelData();
        levelData.load(file.getPath());
        
        entities.clear();
        
        entities.addAll(levelData.fantoms);
        entities.addAll(levelData.pickables);
        entities.addAll(levelData.walls);
        entities.add(levelData.pacman);
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
    
    private boolean isThereWallLeft(ArrayList<Wall> walls, double x, double y){
        return walls.stream().anyMatch((wall) -> (wall.getX() == x-wall.getSize() && wall.getY() == y));
    }
    
    private boolean isThereWallRight(ArrayList<Wall> walls, double x, double y){
        return walls.stream().anyMatch((wall) -> (wall.getX() == x+wall.getSize() && wall.getY() == y));
    }
    
    private boolean isThereWallUp(ArrayList<Wall> walls, double x, double y){
        return walls.stream().anyMatch((wall) -> (wall.getX() == x && wall.getY() == y-wall.getSize()));
    }
    
    private boolean isThereWallDown(ArrayList<Wall> walls, double x, double y){
        return walls.stream().anyMatch((wall) -> (wall.getX() == x && wall.getY() == y+wall.getSize()));
    }
    
    private void setWallTexture(ArrayList<Wall> walls){
        
        walls.forEach(wall -> {
            double x = wall.getX();
            double y = wall.getY();
            
            String textureName="a";
            
            if(isThereWallUp(walls, x, y)) textureName += "U";
            if(isThereWallDown(walls, x, y)) textureName += "D";
            if(isThereWallLeft(walls, x, y)) textureName += "L";
            if(isThereWallRight(walls, x, y)) textureName += "R";
            
            wall.setTexturePath("assets/Wall/"+textureName+".png");
        });
    }
    
    private void setFantomTextureAndIA(ArrayList<Fantom> fantoms){
        int counter = 0;
        
        for(Fantom fantom : fantoms){
            switch(counter){
                case 0: fantom.setTexturePath("assets/Fantome/BlueFull.png"); break;
                case 1: fantom.setTexturePath("assets/Fantome/OrangeFull.png"); break;
                case 2: fantom.setTexturePath("assets/Fantome/PinkFull.png"); break;
                case 3: fantom.setTexturePath("assets/Fantome/RedFull.png"); break;
                default: fantom.setTexturePath("assets/Fantome/BlueFull.png"); break;
            }
            counter++;
        }
    }
}
