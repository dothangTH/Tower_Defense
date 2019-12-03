package TowerDefense;

import GameStage.Stage;
import javafx.scene.Scene;

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

    public void mouseEvent(Scene theScene) {
        theScene.setOnMouseClicked(e -> {
            try {
                gameStage.mouseInput("click",(int) e.getX(),(int) e.getY());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });
        theScene.setOnMouseMoved(e -> {
            try {
                gameStage.mouseInput("hover",(int) e.getX(),(int) e.getY());
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
