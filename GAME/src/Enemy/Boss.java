package Enemy;

import Map.*;
import javafx.scene.image.Image;

import java.io.File;

public class Boss extends Enemy{
    private static Boss instance = null;

    private Boss() {
        super();
        type = "Boss";
        image = new Image(new File("Data/Enemy/Boss.png").toURI().toString());
        damage = 100;
        speed = 1;
        hitPoint = 1000;
        armor = 15;
        reward = 50;
    }

    public static Boss getInstance() {
        if(instance == null) {
            instance = new Boss();
        }
        return instance;
    }

    public static Boss clone(Point coordinate) throws CloneNotSupportedException {
        Boss boss = (Boss) getInstance().clone();
        boss.setCoordinate(coordinate);
        boss.setX(coordinate.getX() * Map.pixelPerBox);
        boss.setY(coordinate.getY() * Map.pixelPerBox);
        return boss;
    }
}
