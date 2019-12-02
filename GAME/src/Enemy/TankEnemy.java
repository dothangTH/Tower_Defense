package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class TankEnemy extends Enemy{
    private static TankEnemy instance = null;

    private TankEnemy() {
        super();
        type = "Tank";
        image = new Image(new File("Data/Enemy/Tank.png").toURI().toString());
        damage = 5;
        speed = 1;
        hitPoint = 100;
        armor = 5;
        reward = 5;
    }

    public static TankEnemy getInstance() {
        if(instance == null) {
            instance = new TankEnemy();
        }
        return instance;
    }

    public static TankEnemy clone(Point coordinate) throws CloneNotSupportedException {
        TankEnemy enemy = (TankEnemy) getInstance().clone();
        enemy.setCoordinate(coordinate);
        enemy.setX(coordinate.getX() * Map.pixelPerBox);
        enemy.setY(coordinate.getY() * Map.pixelPerBox);
        return enemy;
    }
}
