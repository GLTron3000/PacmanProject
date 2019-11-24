package entity;

import static entity.Fantom.FantomState.NORMAL;
import game.Kernel;
import ia.IA;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Fantom extends Movable {
    String name;
    public double initX;
    public double initY;
    Image textureKillable;
    Image textureBackToLobby;
    IA ia;

    public enum FantomState{NORMAL, KILLABLE, BACKTOLOBBY}

    public FantomState fState;

    public Fantom(double x, double y, String name) {
        super(x, y, 1);
        initX=x;
        initY=y;
        type="Fantom";
        this.name = name;
        fState = FantomState.NORMAL;
        frameNumber = 1;
    }
    
    public void setKillable(){
        fState = FantomState.KILLABLE;
        frame = 0;
        reverseFrames = false;
    }
    
    public void setBackToLobby(){
        fState = FantomState.BACKTOLOBBY;
        frame = 0;
        reverseFrames = false;
    }
    
    public void setNormal(){
        fState = FantomState.NORMAL;
        frame = 0;
        reverseFrames = false;
    }
    
    public void setIA(IA ia){
        this.ia = ia;
    }
    
    public void computeMove(Kernel kernel){
        direction = ia.getMove(kernel, this);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fantom fantom = (Fantom) o;
        return Objects.equals(name, fantom.name);
    }


    @Override
    public String toString() {
        return "Fantom{" +
                "direction=" + direction +
                ", name='" + name + '\'' +
                ", x=" + x +
                ", y=" + y +
                ", type='" + type + '\'' +
                '}';
    }
    
    @Override
    public void draw(Canvas canvas){
        switch(fState){
            case NORMAL: super.draw(canvas); break;
            case KILLABLE: drawKillable(canvas); break;
            case BACKTOLOBBY: drawBackToLobby(canvas); break;
        }
    }
    
    private void drawKillable(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();   
        
        gc.setFill(Color.BLACK);
        gc.fillRect(lastDrawX, lastDrawY, size, size);
        lastDrawX = x;
        lastDrawY = y;
        
        if(textureKillable == null){
            gc.setFill(Color.WHITE);
            gc.fillRect(x, y, size, size);
            return;
        }  

        if(reverseFrames){
            if(frame == 0) reverseFrames = false;
            else frame--;
        }else{
            if(frame == 3) reverseFrames = true;
            else frame++;
        }
        
        // x_source y_source w_source h_source x_dest y_dest w_dest h_dest
        gc.drawImage(textureKillable, frame*size, 0, size, size, x, y, size, size);
    }
    
    private void drawBackToLobby(Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D(); 
        
        gc.setFill(Color.BLACK);
        gc.fillRect(lastDrawX, lastDrawY, size, size);
        lastDrawX = x;
        lastDrawY = y;
        
        if(textureBackToLobby == null){
            gc.setFill(Color.DARKBLUE);
            gc.fillRect(x, y, size, size);
            return;
        }  

        gc.drawImage(textureBackToLobby, x, y, size, size);
    }
    
    @Override
    public void loadTexture(){
        super.loadTexture();
        try {
            textureKillable = new Image(new FileInputStream("assets/Fantome/KillableFull.png"));
            //textureBackToLobby = new Image(new FileInputStream("assets/Fantome/"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Pacman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
