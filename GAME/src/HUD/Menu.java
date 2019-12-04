package HUD;

import Util.GameButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
        buttonList.add(new GameButton("Data/Button/Continue.png", 300, 300, "continue"));
        buttonList.add(new GameButton("Data/Button/NewGame.png", 300, 400, "newgame"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 300, 500, "settings"));
        buttonList.add(new GameButton("Data/Button/Quit.png", 300, 600, "quit"));
    }

    public void ingameMenu() {
        buttonList.clear();
        type = "ingame";
        buttonList.add(new GameButton("Data/Button/Continue.png", 300, 300, "igcontinue"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 300, 400, "handler"));
        buttonList.add(new GameButton("Data/Button/Home.png", 300, 500, "home"));
        show = true;
    }

    public void mainMenu() {
        buttonList.clear();
        type = "main";
        buttonList.add(new GameButton("Data/Button/Continue.png", 300, 300, "continue"));
        buttonList.add(new GameButton("Data/Button/NewGame.png", 300, 400, "newgame"));
        buttonList.add(new GameButton("Data/Button/Settings.png", 300, 500, "settings"));
        buttonList.add(new GameButton("Data/Button/Quit.png", 300, 600, "quit"));
        show = true;
    }

    public void render(GraphicsContext gc) {
        if (show) {
            if (type == "ingame") {
                gc.setFill(Color.rgb(200, 200, 200, 0.5));
                gc.fillRect(0, 0, 768, 768);
            } else {
                gc.setFill(Color.BLACK);
                gc.fillRect(0, 0, 768, 768);
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

    public void mouseInput(int mouseX, int mouseY) throws FileNotFoundException, CloneNotSupportedException {
        for (GameButton gameButton : buttonList) {
            gameButton.Click(mouseX, mouseY);
            if (gameButton.getName() == "home") return;
        }
    }
}
