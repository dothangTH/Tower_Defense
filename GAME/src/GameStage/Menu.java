package GameStage;

import Object.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<GameButton> buttonList;
    private String type;
    private boolean show;

    public Menu() {
        type = "main";
        show = true;
        buttonList = new ArrayList<GameButton>();
        buttonList.add(new GameButton("Data/Button/Continue.png", 200, 100, "continue"));
        buttonList.add(new GameButton("Data/Button/NewGame.png", 200, 200, "newgame"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 200, 300, "settings"));
        buttonList.add(new GameButton("Data/Button/Quit.png", 200, 400, "quit"));
    }

    public void ingameMenu() {
        buttonList.clear();
        type = "ingame";
        buttonList.add(new GameButton("Data/Button/Continue.png", 200, 100, "igcontinue"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 200, 200, "handler"));
        buttonList.add(new GameButton("Data/Button/Home.png", 200, 300, "home"));
        buttonList.add(new GameButton("Data/Button/Quit.png", 200, 400, "quit"));
        show = true;
    }

    public void mainMenu() {
        buttonList.clear();
        type = "main";
        buttonList.add(new GameButton("Data/Button/Continue.png", 200, 100, "continue"));
        buttonList.add(new GameButton("Data/Button/NewGame.png", 200, 200, "newgame"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 200, 300, "settings"));
        buttonList.add(new GameButton("Data/Button/Quit.png", 200, 400, "quit"));
        show = true;
    }

    public void render(GraphicsContext gc) {
        if (show) {
            if (type == "ingame") {
                gc.setFill(Color.rgb(200, 200, 200, 0.5));
                gc.fillRect(0, 0, 600, 600);
            } else {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 600, 600);
            }

            for (GameButton gameButton : buttonList) {
                gameButton.render(gc);
            }
        }
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public boolean isShow() {
        return show;
    }

    public void mouseInput(int mouseX, int mouseY) throws FileNotFoundException {
        for (GameButton gameButton : buttonList) {
            gameButton.Click(mouseX, mouseY);
            if (gameButton.getName() == "home") return;
        }
    }
}
