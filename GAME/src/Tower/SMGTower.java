package Tower;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class SMGTower extends Tower{
    private static SMGTower instance = null;

    private SMGTower() {
        super();
        type = "SMG";
        penetrating = false;
        image = new Image (new File("Data/Tower/SMG.png").toURI().toString());
        damage = 4;
        refundValue = 8;
        price = 15;
        upgradePrice = 10;
        reloadTime = 10;
        speed = 10;
        range = 200;
    }

    public static SMGTower getInstance() {
        if(instance == null) {
            instance = new SMGTower();
        }
        return instance;
    }

    public static SMGTower clone(Point coordinate) throws CloneNotSupportedException {
        SMGTower tower = (SMGTower) getInstance().clone();
        tower.setCoordinate(coordinate);
        tower.setX(coordinate.getX() * Map.pixelPerBox);
        tower.setY(coordinate.getY() * Map.pixelPerBox);
        return tower;
    }
}
