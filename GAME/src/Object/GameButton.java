package Object;

import TowerDefense.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.io.File;
import java.io.FileNotFoundException;

public class GameButton extends GameObject implements ClickableObject{
    private Image image;
    private String name;

    public GameButton(String path, int X, int Y, String name) {
        this.name = name;
        image = new Image(new File(path).toURI().toString());
        setX(X);
        setY(Y);
    }

    @Override
    public void Click(int mouseX, int mouseY) throws FileNotFoundException {
        if (onHover(mouseX, mouseY))
            switch (name) {
                case "newgame":
                    System.out.println("NewGame");
                    Controller.getInstance().menu.setShow(false);
                    Controller.getInstance().level = 1;
                    Controller.getInstance().initStage();
                    break;

                case "pause":
                    Controller.getInstance().gameStage.pause = true;
                    Controller.getInstance().menu.ingameMenu();
                    break;

                case "igcontinue":
                    Controller.getInstance().gameStage.pause = false;
                    Controller.getInstance().menu.setShow(false);
                    break;

                case "home":
                    Controller.getInstance().stageInitialized= false;
                    Controller.getInstance().menu.mainMenu();
                    break;

                case "quit":
                    Controller.getInstance().exit = true;
            };
    }

    @Override
    public void Hover(int mouseX, int mouseY) {
    }

    @Override
    public boolean onHover(int mouseX, int mouseY) {
        return (0 <= mouseX - getX() && mouseX - getX() <= ((int) image.getWidth()) &&
                0 <= mouseY - getY() && mouseY - getY() <= ((int) image.getHeight()));
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY());
    }

    public String getName() {
        return name;
    }
}
