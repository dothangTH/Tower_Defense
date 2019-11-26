package Tower;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class ArtilleryTower extends Tower{
    private static ArtilleryTower instance = null;

    private ArtilleryTower() {
        super();
        type = "Artillery";
        penetrating = false;
        image = new Image (new File("Data/Tower/Artillery.png").toURI().toString());
        damage = 40;
        refundValue = 15;
        price = 30;
        upgradePrice = 20;
        reloadTime = 100;
        speed = 12;
        range = 400;
    }

    public static ArtilleryTower getInstance() {
        if(instance == null) {
            instance = new ArtilleryTower();
        }
        return instance;
    }

    public static ArtilleryTower clone(Point coordinate) throws CloneNotSupportedException {
        ArtilleryTower tower = (ArtilleryTower) getInstance().clone();
        tower.setCoordinate(coordinate);
        tower.setX(coordinate.getX() * Map.pixelPerBox);
        tower.setY(coordinate.getY() * Map.pixelPerBox);
        return tower;
    }
}
