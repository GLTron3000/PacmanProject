package scenes;

import game.Kernel;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

public class Game {
    Scene scene; 
    Canvas canvas;
    Kernel kernel;

    public Game() {
        StackPane stackPane = new StackPane();
        canvas = new Canvas();
        
        stackPane.getChildren().add(canvas);
        scene = new Scene(stackPane, 640, 640);
    }

    public Scene getScene() {
        return scene;
    }
    
    void refresh(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        kernel.entities.forEach((entity) -> {
            entity.draw(gc);
        });
    }
    
}
