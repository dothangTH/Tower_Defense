package Tower;

import Map.*;
import Enemy.*;
import Object.Bullet;
import TowerDefense.Controller;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public class BlasterTower extends Tower{
    private static BlasterTower instance = null;

    private BlasterTower() {
        super();
        type = "Blaster";
        penetrating = false;
        image = new Image (new File("Data/Tower/Blaster.png").toURI().toString());
        damage = 10;
        refundValue = 15;
        price = 30;
        upgradePrice = 20;
        reloadTime = 100;
        speed = 10;
        range = 200;
    }

    public static BlasterTower getInstance() {
        if(instance == null) {
            instance = new BlasterTower();
        }
        return instance;
    }

    public static BlasterTower clone(Point coordinate) throws CloneNotSupportedException {
        BlasterTower tower = (BlasterTower) getInstance().clone();
        tower.setCoordinate(coordinate);
        tower.setX(coordinate.getX() * Map.pixelPerBox);
        tower.setY(coordinate.getY() * Map.pixelPerBox);
        return tower;
    }

//    @Override
//    public void attack() throws FileNotFoundException, CloneNotSupportedException {
//        if (this.getCurrentReloadTime() <= 0) {
//            for (Enemy enemy : Controller.getInstance().gameStage.enemyList) {
//                double distance = Math.sqrt((getX() - enemy.getX()) * (getX() - enemy.getX()) + (getY() - enemy.getY()) * (getY() - enemy.getY()));
//                if (distance < range) Controller.getInstance().gameStage.bulletList.add(Bullet.clone(this, enemy));
//            }
//            setCurrentReloadTime(reloadTime);
//        } else
//            setCurrentReloadTime(getCurrentReloadTime() - 1);
//    }
}
