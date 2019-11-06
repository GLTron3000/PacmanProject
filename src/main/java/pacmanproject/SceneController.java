package pacmanproject;

import javafx.scene.Scene;
import scenes.Game;
import scenes.MainMenu;

public class SceneController {
    private final Scene mainScene;
    public Game game = new Game(this);
    public MainMenu mainMenu = new MainMenu(this);

    public SceneController(Scene mainScene) {
        this.mainScene = mainScene;
    }
    
    public void showGame(){
        mainScene.setRoot(game.getNode());
    }
    
    public void showMainMenu(){
        mainScene.setRoot(mainMenu.getNode());
    }
    
}
