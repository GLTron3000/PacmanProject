package pacmanproject;

public class Launcher {
    public static void main(String[] args) {
        System.setProperty("quantum.multithreaded", "false");
        App.main(args);
    }
}
