package pacmanproject;

import javafx.scene.Scene;
import scenes.Game;
import scenes.MainMenu;

public class SceneController {
    private Scene scene;
    public Game game;
    public MainMenu mainMenu;

    public SceneController(Scene mainScene) {
        game = new Game(this);
        mainMenu = new MainMenu(this);
        this.scene = mainScene;
    }
    
    public void showGame(){
        scene.setRoot(game.getNode());
    }
    
    public void showMainMenu(){
        System.out.println(scene);
        System.out.println(mainMenu.getNode());
        scene.setRoot(mainMenu.getNode());
    }
    
}
