package fac.pacmanproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application{
    @Override
    public void start(Stage stage) {
        
        stage.setTitle("Pacman Project");
        stage.setScene(new Scene(new Label("------------------------------------------")));
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
