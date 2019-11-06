package pacmanproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import scenes.MainMenu;

public class App extends Application{
    
    SceneController sceneController;
    
    @Override
    public void start(Stage stage) {   
        Scene mainScene = new Scene(new Label("Chargement"), 640, 640);
        stage.setTitle("Pacman Project");
        stage.setScene(mainScene);
        
        
        sceneController = new SceneController(mainScene);
        
        sceneController.showMainMenu();

        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
