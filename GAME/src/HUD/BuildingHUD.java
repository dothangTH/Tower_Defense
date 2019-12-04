package HUD;

import Map.Point;
import Object.*;
import Util.GameButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;

public class BuildingHUD extends GameObject {
    private Image image;
    private static BuildingHUD instance = null;
    private boolean show;
    private int price;
    private GameButton normal, smg, artillery, antiarmored, blaster;
    private Point targeting;

    private BuildingHUD(){
        image = new Image(new File("Data/HUD/BuildingHUD.png").toURI().toString());
        setX(380);
        setY(20);
        show = false;
        targeting = null;
        normal = new GameButton("Data/Button/Normal.png", getX() + 25, getY() + 337, "normal");
        smg = new GameButton("Data/Button/SMG.png", getX() + 115, getY() + 337, "smg");
        artillery = new GameButton("Data/Button/Artillery.png", getX() + 203, getY() + 337, "artillery");
        antiarmored = new GameButton("Data/Button/AntiArmored.png", getX() + 280, getY() + 337, "antiarmored");
        blaster = new GameButton("Data/Button/Blaster.png", getX() + 25, getY() + 422, "blaster");
    }

    public static BuildingHUD getInstance() {
        if (instance == null)
            instance = new BuildingHUD();
        return instance;
    }

    public void toggle() {
        show = !show;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, getX(), getY());
        normal.render(gc);
        smg.render(gc);
        artillery.render(gc);
        antiarmored.render(gc);
        blaster.render(gc);

        gc.setFill(Color.WHITESMOKE);
        gc.setFont(Font.font(null, FontWeight.BOLD, 20));
        gc.fillText("" + price, getX() + 195, getY() + 605);
    }

    public boolean onHover(int mouseX, int mouseY) {
        return (0 <= mouseX - getX() && mouseX - getX() <= ((int) image.getWidth()) &&
                0 <= mouseY - getY() && mouseY - getY() <= ((int) image.getHeight()));
    }

    public void mouseInput(String opcode, int mouseX, int mouseY) throws FileNotFoundException, CloneNotSupportedException {
        switch (opcode) {
            case "click":
                if (!onHover(mouseX, mouseY))
                    toggle();
                else {
                    normal.Click(mouseX, mouseY);
                    smg.Click(mouseX, mouseY);
                    artillery.Click(mouseX, mouseY);
                    antiarmored.Click(mouseX, mouseY);
                    blaster.Click(mouseX, mouseY);
                }
                break;

            case "hover":
                normal.Hover(mouseX, mouseY);
                smg.Hover(mouseX, mouseY);
                artillery.Hover(mouseX, mouseY);
                antiarmored.Hover(mouseX, mouseY);
                blaster.Hover(mouseX, mouseY);
                break;
        }
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isShow() {
        return show;
    }

    public void setTargeting(Point targeting) {
        this.targeting = targeting;
    }

    public Point getTargeting() {
        return targeting;
    }
}
