package HUD;

import GameStage.Player;
import Object.*;
import Tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.File;

public class PlayerHUD extends GameObject {
    private Image image;
    private static PlayerHUD instance = null;

    private PlayerHUD() {
        image = new Image(new File("Data/HUD/PlayerHUD.png").toURI().toString());
        setX(100);
        setY(10);
    }

    public static PlayerHUD getInstance() {
        if (instance == null)
            instance = new PlayerHUD();
        return instance;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY());
        gc.setStroke(Color.WHITESMOKE);
        gc.setFont(Font.font(null, FontWeight.MEDIUM, 16));
        gc.strokeText("" + Player.getInstance().getHitPoint(), 197, 32);
        gc.strokeText("" + Player.getInstance().getWallet(), 267, 32);
    }
}
