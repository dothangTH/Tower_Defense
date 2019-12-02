package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class BusterEnemy extends Enemy{
    private static BusterEnemy instance = null;

    private BusterEnemy() {
        super();
        type = "Buster";
        image = new Image(new File("Data/Enemy/Buster.png").toURI().toString());
        damage = 15;
        speed = 1;
        hitPoint = 20;
        armor = 0;
        reward = 4;
    }

    public static BusterEnemy getInstance() {
        if(instance == null) {
            instance = new BusterEnemy();
        }
        return instance;
    }

    public static BusterEnemy clone(Point coordinate) throws CloneNotSupportedException {
        BusterEnemy enemy = (BusterEnemy) getInstance().clone();
        enemy.setCoordinate(coordinate);
        enemy.setX(coordinate.getX() * Map.pixelPerBox);
        enemy.setY(coordinate.getY() * Map.pixelPerBox);
        return enemy;
    }
}
