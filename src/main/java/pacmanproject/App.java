package pacmanproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application{
    Scene mainScene;
    SceneController sceneController;
    
    @Override
    public void start(Stage stage) {      
        mainScene = new Scene(new Label("Chargement"), 640, 640);
        sceneController = new SceneController(mainScene);
        
        sceneController.showMainMenu();
        
        
        stage.setTitle("Pacman Project");
        
        stage.setScene(mainScene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
