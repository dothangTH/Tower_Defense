package TowerDefense;

import GameStage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;

public class Controller {
    public Stage gameStage;
    private static Controller instance = null;

    public Controller() throws FileNotFoundException {
        instance = this;
        gameStage = new Stage(1);
    }

    public static Controller getInstance() throws FileNotFoundException {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public static void mouseController(Scene scene, GraphicsContext gc){
        scene.setOnMouseClicked(event -> {
            try {
                Controller.getInstance().gameStage.onClick((int)event.getX(), (int)event.getY(), gc);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
        scene.setOnMouseMoved(event -> {
            try {
                Controller.getInstance().gameStage.onHover((int)event.getX(), (int)event.getY(), gc);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
