package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class SpeedyEnemy extends Enemy{
    private static SpeedyEnemy instance = null;

    private SpeedyEnemy() {
        super();
        type = "Speedy";
        image = new Image(new File("Data/Enemy/Speedy.png").toURI().toString());
        damage = 5;
        speed = 3;
        hitPoint = 10;
        armor = 2;
        reward = 3;
    }

    public static SpeedyEnemy getInstance() {
        if(instance == null) {
            instance = new SpeedyEnemy();
        }
        return instance;
    }

    public static SpeedyEnemy clone(Point coordinate) throws CloneNotSupportedException {
        SpeedyEnemy enemy = (SpeedyEnemy) getInstance().clone();
        enemy.setCoordinate(coordinate);
        enemy.setX(coordinate.getX() * Map.pixelPerBox);
        enemy.setY(coordinate.getY() * Map.pixelPerBox);
        return enemy;
    }
}
