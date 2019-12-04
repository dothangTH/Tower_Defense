package HUD;

import Object.*;
import Tower.Tower;
import Util.GameButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;


public class TowerHUD extends GameObject {
    private Image image;
    private static TowerHUD instance = null;
    private boolean show;
    private Tower tower;
    private GameButton upgrade, sell;

    private TowerHUD() {
        image = new Image(new File("Data/HUD/TowerHUD.png").toURI().toString());
        setX(380);
        setY(20);
        tower = null;
        show = false;
        upgrade = new GameButton("Data/Button/Upgrade.png", getX() + 25, getY() + 562, "upgrade");
        sell = new GameButton("Data/Button/Sell.png", getX() + 236, getY() + 621, "sell");
    }

    public static TowerHUD getInstance() {
        if (instance == null)
            instance = new TowerHUD();
        return instance;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY());

        gc.setStroke(Color.LIGHTGREY);
        gc.setFill(Color.WHITESMOKE);

        gc.strokeText("" + tower.getRange(), getX() + 155, getY() + 210);
        gc.strokeText(String.format("%.2f", 20 * 1.0 / tower.getReloadTime()), getX() + 155, getY() + 255);
        gc.strokeText("" + tower.getSpeed(), getX() + 155, getY() + 300);
        gc.strokeText("" + tower.getDamage(), getX() + 155, getY() + 345);

        if (tower.upgradable()) gc.strokeText("" + tower.getUpgradePrice(), getX() + 317, getY() + 592);

        gc.strokeText("" + tower.getRefundValue(), getX() + 305, getY() + 653);

        gc.setFont(Font.font(null, FontWeight.BOLD, 20));

        gc.fillText(tower.getType() + " Tower level " + tower.getLevel(), getX() + 20, getY() + 40);
    }

    public boolean onHover(int mouseX, int mouseY) {
        return (0 <= mouseX - getX() && mouseX - getX() <= ((int) image.getWidth()) &&
                0 <= mouseY - getY() && mouseY - getY() <= ((int) image.getHeight()));
    }

    public void mouseInput(int mouseX, int mouseY) throws FileNotFoundException, CloneNotSupportedException {
        if (!onHover(mouseX, mouseY))
            toggle();
        else {
            upgrade.Click(mouseX, mouseY);
            sell.Click(mouseX, mouseY);
        }
    }

    public boolean isShow() {
        return show;
    }

    public void toggle() {
        show = !show;
    }

    public void setTower(Tower tower) {
        this.tower = tower;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Tower getTower() {
        return tower;
    }
}
