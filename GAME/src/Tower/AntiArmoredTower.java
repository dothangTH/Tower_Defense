package Tower;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class AntiArmoredTower extends Tower{
    private static AntiArmoredTower instance = null;

    private AntiArmoredTower() {
        super();
        type = "AntiArmored";
        penetrating = true;
        image = new Image (new File("Data/Tower/AntiArmored.png").toURI().toString());
        damage = 10;
        refundValue = 10;
        price = 20;
        upgradePrice = 10;
        reloadTime = 30;
        speed = 10;
        range = 200;
    }

    public static AntiArmoredTower getInstance() {
        if(instance == null) {
            instance = new AntiArmoredTower();
        }
        return instance;
    }

    public static AntiArmoredTower clone(Point coordinate) throws CloneNotSupportedException {
        AntiArmoredTower tower = (AntiArmoredTower) getInstance().clone();
        tower.setCoordinate(coordinate);
        tower.setX(coordinate.getX() * Map.pixelPerBox);
        tower.setY(coordinate.getY() * Map.pixelPerBox);
        return tower;
    }
}
