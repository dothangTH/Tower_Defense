package TowerDefense;

import HUD.Menu;
import GameStage.Stage;
import Util.Sound;
import javafx.scene.Scene;

import java.io.FileNotFoundException;

public class Controller {
    public Stage gameStage;
    public Menu menu;
    public int level = 1;
    public boolean exit;
    public boolean stageInitialized;
    private static Controller instance = null;

    public Sound mainTheme;
    public Sound igTheme;
    public Sound buildFX;
    public Sound clickFX;
    public Sound upgradeFX;
    public Sound sellFX;
    public Sound killFX;
    public Sound hitFX;

    public Controller() throws FileNotFoundException {
        instance = this;
        menu = new Menu();
        exit = false;
        stageInitialized = false;
        mainTheme = new Sound("Data/Sound/Theme/MainTheme.mp3");
        igTheme = new Sound("Data/Sound/Theme/InGameTheme.mp3");
        buildFX = new Sound("Data/Sound/FX/build.mp3");
        clickFX = new Sound("Data/Sound/FX/click.mp3");
        upgradeFX = new Sound("Data/Sound/FX/upgrade.mp3");
        sellFX = new Sound("Data/Sound/FX/sell.mp3");
        killFX = new Sound("Data/Sound/FX/kill.mp3");
        hitFX = new Sound("Data/Sound/FX/hit.mp3");
        mainTheme.playTheme();
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
