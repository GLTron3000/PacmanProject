package pacmanproject;

import javafx.scene.Scene;
import scenes.Game;
import scenes.MainMenu;

public class SceneController {
    private Scene scene;
    public Game game;
    public MainMenu mainMenu;

    public SceneController(Scene mainScene) {
        this.scene = mainScene;
        game = new Game(this);
        mainMenu = new MainMenu(this);
    }
    
    public void showGame(){
        scene.setRoot(game.getNode());
        game.start();
    }
    
    public void showMainMenu(){
        game.stop();
        scene.setRoot(mainMenu.getNode());
    }

    public Scene getScene() {
        return scene;
    }
}
