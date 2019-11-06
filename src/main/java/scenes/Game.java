package scenes;

import game.Kernel;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import pacmanproject.SceneController;

public class Game {
    SceneController sceneController;
    StackPane stackPane;
    Canvas canvas;
    Kernel kernel;

    public Game(SceneController sceneController) {
        stackPane = new StackPane();
        canvas = new Canvas();
        
        stackPane.getChildren().add(canvas);
        this.sceneController = sceneController;
    }

    public StackPane getNode() {
        return stackPane;
    }
    
    void refresh(){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        kernel.entities.forEach((entity) -> {
            entity.draw(gc);
        });
    }
    
}
