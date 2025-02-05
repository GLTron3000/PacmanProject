package pacmanproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application{
    
    SceneController sceneController;
    
    @Override
    public void start(Stage stage) {   
        Scene mainScene = new Scene(new Label("Chargement"), 1000, 1000);
        
        stage.setTitle("Pacman Project");
        stage.setScene(mainScene);
        
        stage.setOnCloseRequest(e -> {
            if(sceneController.game != null) sceneController.game.stop();
            Platform.exit();
            System.exit(0);
        });
        
        
        sceneController = new SceneController(mainScene, stage);
        
        sceneController.showMainMenu();

        stage.show();

    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
