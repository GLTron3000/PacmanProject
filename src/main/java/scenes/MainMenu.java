package scenes;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import pacmanproject.SceneController;

public class MainMenu {
    StackPane stackPane;
    VBox vboxHelp;
    SceneController sceneController;

    public MainMenu(SceneController sceneController) {
        stackPane = new StackPane();
        VBox vbox = new VBox();
        initHelp();
        
        Label title = new Label("PACMAN");
        title.getStyleClass().add("labelTitle");
        title.setPadding(new Insets(70));
        
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50));
        vbox.setSpacing(5);
        
        vbox.getStyleClass().add("vbox");
        
        vbox.getChildren().addAll(title, gameSingleButton(), gameSingleRandomButton(), levelBuilderButton(), helpButton(), exitButton());
        
        stackPane.getStylesheets().add(getClass().getClassLoader().getResource("css/menuStyle.css").toExternalForm());
        stackPane.setAlignment(Pos.CENTER);
        
        stackPane.getChildren().add(vbox);
        this.sceneController = sceneController;
        
    }
    
    private Button gameSingleButton(){
        Button button = new Button("Un joueur");
        
        button.setOnAction((ActionEvent event) -> {
            sceneController.showGame("levels/level1.pml");
        });
        
        return button;
    }
    
    private Button gameSingleRandomButton(){
        Button button = new Button("Un joueur (niveau aleatoire)");
        
        button.setOnAction((ActionEvent event) -> {
            Random random = new Random();
            File files = new File("levels/");
            ArrayList<String> fileList = new ArrayList(Arrays.asList(files.list()));
            fileList.remove("level1.pml");
            sceneController.showGame("levels/"+fileList.get(random.nextInt(fileList.size())));
        });
        
        return button;
    }
    
    private Button gameMultiButton(){
        Button button = new Button("Deux joueurs");
        
        button.setOnAction((ActionEvent event) -> {
            
        });
        
        return button;
    }
    
    private Button levelBuilderButton(){
        Button button = new Button("Editeur de niveau");
        
        button.setOnAction((ActionEvent event) -> {
            sceneController.showLevelBuilder();
        });
        
        return button;
    }
    
    private Button settingsButton(){
        Button button = new Button("Parametres");
        
        button.setOnAction((ActionEvent event) -> {
            
        });
        
        return button;
    }
    
    private Button helpButton(){
        Button button = new Button("Aide");
        
        button.setOnAction((ActionEvent event) -> {
            
            stackPane.getChildren().add(vboxHelp);
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

    public Pane getNode() {
        return stackPane;
    }
   
    private void initHelp(){
        Button exitHelpButton = new Button("Retour");
        
        exitHelpButton.setOnAction((ActionEvent event) -> {
            stackPane.getChildren().remove(vboxHelp);
        });
        
        Label hintLabel = new Label("Mouvements:");
        //hintLabel.setPadding(new Insets(70));
        Label hintLabel2 = new Label("(Z Q S D)");
        Label hintLabel3 = new Label("Powers Ups:");
        Label hintLabel4 = new Label("(E) WallBreacher [-200 score]");
        Label hintLabel5 = new Label("(A) Reducer");
        
        hintLabel.getStyleClass().add("labelHelp");
        hintLabel2.getStyleClass().add("labelHelp");
        hintLabel3.getStyleClass().add("labelHelp");
        hintLabel4.getStyleClass().add("labelHelp");
        hintLabel5.getStyleClass().add("labelHelp");
        
        vboxHelp = new VBox();
        vboxHelp.getStyleClass().add("vboxHelp");
        vboxHelp.setAlignment(Pos.CENTER);
        vboxHelp.getChildren().addAll(hintLabel, hintLabel2, hintLabel3, hintLabel4, hintLabel5, exitHelpButton);   
    }
}
