package Util;

import GameStage.Player;
import HUD.BuildingHUD;
import HUD.TowerHUD;
import Tower.*;
import TowerDefense.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Object.*;

import java.io.File;
import java.io.FileNotFoundException;

public class GameButton extends GameObject implements ClickableObject {
    private Image image;
    private String name;

    public GameButton(String path, int X, int Y, String name) {
        this.name = name;
        image = new Image(new File(path).toURI().toString());
        setX(X);
        setY(Y);
    }

    @Override
    public void Click(int mouseX, int mouseY) throws FileNotFoundException, CloneNotSupportedException {
        if (onHover(mouseX, mouseY)) {
            switch (name) {
                case "continue":
                    Controller.getInstance().mainTheme.stop();
                    Controller.getInstance().clickFX.playFX();
                    Controller.getInstance().igTheme.playTheme();
                    Controller.getInstance().menu.setShow(false);
                    Controller.getInstance().level = 3;
                    Controller.getInstance().initStage();
                    break;

                case "newgame":
                    Controller.getInstance().mainTheme.stop();
                    Controller.getInstance().clickFX.playFX();
                    Controller.getInstance().igTheme.playTheme();
                    Controller.getInstance().menu.setShow(false);
                    Controller.getInstance().level = 1;
                    Controller.getInstance().initStage();
                    break;

                case "pause":
                    Controller.getInstance().clickFX.playFX();
                    Controller.getInstance().igTheme.pause();
                    Controller.getInstance().gameStage.pause = true;
                    Controller.getInstance().menu.ingameMenu();
                    break;

                case "igcontinue":
                    Controller.getInstance().clickFX.playFX();
                    Controller.getInstance().igTheme.playTheme();
                    Controller.getInstance().gameStage.pause = false;
                    Controller.getInstance().menu.setShow(false);
                    break;

                case "home":
                    Controller.getInstance().clickFX.playFX();
                    Controller.getInstance().igTheme.stop();
                    Controller.getInstance().mainTheme.playTheme();
                    Controller.getInstance().stageInitialized = false;
                    Player.getInstance().remove();
                    Controller.getInstance().menu.mainMenu();
                    break;

                case "quit":
                    Controller.getInstance().exit = true;
                    break;

                case "upgrade":
                    if (TowerHUD.getInstance().getTower().getUpgradePrice() <= Player.getInstance().getWallet()) {
                        TowerHUD.getInstance().getTower().upgrade();
                        Controller.getInstance().upgradeFX.playFX();
                    }
                    break;

                case "sell":
                    TowerHUD.getInstance().getTower().sell();
                    TowerHUD.getInstance().toggle();
                    break;

                case "normal":
                    if (NormalTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                        Controller.getInstance().gameStage.buildTower("Normal", BuildingHUD.getInstance().getTargeting());
                        Controller.getInstance().gameStage.getMap().setOccupied(true, BuildingHUD.getInstance().getTargeting().getX(), BuildingHUD.getInstance().getTargeting().getY());
                        BuildingHUD.getInstance().toggle();
                    }
                    break;

                case "smg":
                    if (SMGTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                        Controller.getInstance().gameStage.buildTower("SMG", BuildingHUD.getInstance().getTargeting());
                        Controller.getInstance().gameStage.getMap().setOccupied(true, BuildingHUD.getInstance().getTargeting().getX(), BuildingHUD.getInstance().getTargeting().getY());
                        BuildingHUD.getInstance().toggle();
                    }
                    break;

                case "artillery":
                    if (ArtilleryTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                        Controller.getInstance().gameStage.buildTower("Artillery", BuildingHUD.getInstance().getTargeting());
                        Controller.getInstance().gameStage.getMap().setOccupied(true, BuildingHUD.getInstance().getTargeting().getX(), BuildingHUD.getInstance().getTargeting().getY());
                        BuildingHUD.getInstance().toggle();
                    }
                    break;

                case "antiarmored":
                    if (AntiArmoredTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                        Controller.getInstance().gameStage.buildTower("AntiArmored", BuildingHUD.getInstance().getTargeting());
                        Controller.getInstance().gameStage.getMap().setOccupied(true, BuildingHUD.getInstance().getTargeting().getX(), BuildingHUD.getInstance().getTargeting().getY());
                        BuildingHUD.getInstance().toggle();
                    }
                    break;

                case "blaster":
                    if (BlasterTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                        Controller.getInstance().gameStage.buildTower("Blaster", BuildingHUD.getInstance().getTargeting());
                        Controller.getInstance().gameStage.getMap().setOccupied(true, BuildingHUD.getInstance().getTargeting().getX(), BuildingHUD.getInstance().getTargeting().getY());
                        BuildingHUD.getInstance().toggle();
                    }
                    break;
            }
            ;
        }
    }

    @Override
    public void Hover(int mouseX, int mouseY) {
        if (onHover(mouseX, mouseY)) {
            switch (name) {
                case "normal":
                    BuildingHUD.getInstance().setPrice(NormalTower.getInstance().getPrice());
                    break;

                case "smg":
                    BuildingHUD.getInstance().setPrice(SMGTower.getInstance().getPrice());
                    break;

                case "artillery":
                    BuildingHUD.getInstance().setPrice(ArtilleryTower.getInstance().getPrice());
                    break;

                case "antiarmored":
                    BuildingHUD.getInstance().setPrice(AntiArmoredTower.getInstance().getPrice());
                    break;

                case "blaster":
                    BuildingHUD.getInstance().setPrice(BlasterTower.getInstance().getPrice());
                    break;
            }
        }
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
