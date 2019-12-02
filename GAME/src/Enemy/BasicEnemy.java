package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class BasicEnemy extends Enemy {
    private static BasicEnemy instance = null;

    private BasicEnemy() {
        super();
        type = "Basic";
        image = new Image(new File("Data/Enemy/Basic.png").toURI().toString());
        damage = 5;
        speed = 1;
        hitPoint = 20;
        armor = 3;
        reward = 3;
    }

    public static BasicEnemy getInstance() {
        if(instance == null) {
            instance = new BasicEnemy();
        }
        return instance;
    }

    public static BasicEnemy clone(Point coordinate) throws CloneNotSupportedException {
        BasicEnemy enemy = (BasicEnemy) getInstance().clone();
        enemy.setCoordinate(coordinate);
        enemy.setX(coordinate.getX() * Map.pixelPerBox);
        enemy.setY(coordinate.getY() * Map.pixelPerBox);
        return enemy;
    }
}
