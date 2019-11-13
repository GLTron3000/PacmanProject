package pacmanproject;

import javafx.scene.Scene;
import scenes.Game;
import scenes.LevelBuilder;
import scenes.MainMenu;

public class SceneController {
    private Scene scene;
    public Game game;
    public MainMenu mainMenu;
    public LevelBuilder levelBuilder;

    public SceneController(Scene mainScene) {
        this.scene = mainScene;
        mainMenu = new MainMenu(this);
    }
    
    public void showGame(){
        game = new Game(this);
        scene.setRoot(game.getNode());
        game.start();
    }
    
    public void showMainMenu(){
        if(game != null) game.stop();
        if(levelBuilder != null) levelBuilder.stop();
        scene.setRoot(mainMenu.getNode());
    }
    
    public void showLevelBuilder(){
        levelBuilder = new LevelBuilder(this);
        scene.setRoot(levelBuilder.getNode());
        levelBuilder.start();
    }

    public Scene getScene() {
        return scene;
    }
}
