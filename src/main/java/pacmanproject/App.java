package pacmanproject;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import scenes.Game;
import scenes.MainMenu;

public class App extends Application{
    Game game = new Game();
    MainMenu mainMenu = new MainMenu();

    @Override
    public void start(Stage stage) {      
        
    
        stage.setTitle("Pacman Project");
        stage.setScene(mainMenu.getScene());
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
