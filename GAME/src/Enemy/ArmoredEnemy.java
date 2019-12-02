package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class ArmoredEnemy extends Enemy{
    private static ArmoredEnemy instance = null;

    private ArmoredEnemy() {
        super();
        type = "Armored";
        image = new Image(new File("Data/Enemy/Armored.png").toURI().toString());
        damage = 5;
        speed = 1;
        hitPoint = 20;
        armor = 30;
        reward = 5;
    }

    public static ArmoredEnemy getInstance() {
        if(instance == null) {
            instance = new ArmoredEnemy();
        }
        return instance;
    }

    public static ArmoredEnemy clone(Point coordinate) throws CloneNotSupportedException {
        ArmoredEnemy enemy = (ArmoredEnemy) getInstance().clone();
        enemy.setCoordinate(coordinate);
        enemy.setX(coordinate.getX() * Map.pixelPerBox);
        enemy.setY(coordinate.getY() * Map.pixelPerBox);
        return enemy;
    }
}
