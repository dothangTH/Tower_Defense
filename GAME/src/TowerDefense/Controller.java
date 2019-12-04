package TowerDefense;

import HUD.Menu;
import GameStage.Stage;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class Controller {
    public Stage gameStage;
    public Menu menu;
    public int level = 1;
    public boolean exit;
    public boolean stageInitialized;
    private static Controller instance = null;

    public Controller() throws FileNotFoundException {
        instance = this;
        menu = new Menu();
        exit = false;
        stageInitialized = false;
    }

    public void initStage() throws FileNotFoundException {
        gameStage = new Stage(level);
        stageInitialized = true;
    }

    public static Controller getInstance() throws FileNotFoundException {
        if (instance == null)
            instance = new Controller();
        return instance;
    }

    public void mouseEvent(Scene theScene) {
        if (menu.isShow())
            theScene.setOnMouseClicked(e -> {
                try {
                    menu.mouseInput((int) e.getX(), (int) e.getY());
                } catch (FileNotFoundException | CloneNotSupportedException ex) {
                    ex.printStackTrace();
                }
            });
        else {
            theScene.setOnMouseClicked(e -> {
                try {
                    gameStage.mouseInput("click", (int) e.getX(), (int) e.getY());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });

            theScene.setOnMouseMoved(e -> {
                try {
                    gameStage.mouseInput("hover", (int) e.getX(), (int) e.getY());
                } catch (CloneNotSupportedException ex) {
                    ex.printStackTrace();
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
