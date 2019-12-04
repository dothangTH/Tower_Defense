package Tower;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class NormalTower extends Tower{
    private static NormalTower instance = null;

    private NormalTower() {
        super();
        type = "Normal";
        penetrating = false;
        image = new Image (new File("Data/Tower/Normal.png").toURI().toString());
        damage = 5;
        refundValue = 5;
        price = 10;
        upgradePrice = 5;
        reloadTime = 20;
        speed = 10;
        range = 2 * Map.pixelPerBox;
    }

    public static NormalTower getInstance() {
        if(instance == null) {
            instance = new NormalTower();
        }
        return instance;
    }

    public static NormalTower clone(Point coordinate) throws CloneNotSupportedException {
        NormalTower tower = (NormalTower) getInstance().clone();
        tower.setCoordinate(coordinate);
        tower.setX(coordinate.getX() * Map.pixelPerBox);
        tower.setY(coordinate.getY() * Map.pixelPerBox);
        return tower;
    }
}
