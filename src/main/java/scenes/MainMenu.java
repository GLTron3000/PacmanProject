package scenes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainMenu {
    Scene scene; 

    public MainMenu() {
        VBox vbox = new VBox();
        
        Label title = new Label("PACMAN");
        title.setPadding(new Insets(70));
        
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));
        vbox.setSpacing(5);
        
        vbox.setStyle("-fx-background-color: transparent;");
        
        vbox.getChildren().addAll(title, gameSingleButton(), gameMultiButton(), settingsButton(), exitButton());
        vbox.getStylesheets().add("file:src/main/css/menuStyle.css");
        scene = new Scene(vbox, 640, 640, Color.DARKCYAN);
    }
    
    private Button gameSingleButton(){
        Button button = new Button("Un joueur");
        
        button.setOnAction((ActionEvent event) -> {
            
        });
        
        return button;
    }
    
    private Button gameMultiButton(){
        Button button = new Button("Deux joueurs");
        
        button.setOnAction((ActionEvent event) -> {
            
        });
        
        return button;
    }
    
    private Button settingsButton(){
        Button button = new Button("Parametres");
        
        button.setOnAction((ActionEvent event) -> {
            
        });
        
        return button;
    }
    
    private Button exitButton(){
        Button button = new Button("Quitter");
        
        button.setOnAction((ActionEvent event) -> {
            Platform.exit();
            System.exit(0);
        });
        
        return button;
    }

    public Scene getScene() {
        return scene;
    }
   
}
