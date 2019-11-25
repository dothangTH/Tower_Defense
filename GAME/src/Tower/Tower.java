package Tower;

import Enemy.Enemy;
import Object.*;
import Map.*;
import TowerDefense.Controller;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Tower extends GameObject implements UpgradableObject, Cloneable{
    private Point coordinate;
    private int X;
    private int Y;
    private int currentReloadTime;

    protected int damage;
    protected int reloadTime;
    protected int refundValue;
    protected int upgradePrice;
    protected int price;
    protected int speed;
    protected int range;
    protected String type;

    protected Tower() {
        this.coordinate = new Point(0,0);
        this.X = 0;
        this.Y = 0;
        this.currentReloadTime = 0;
    }

    public static Tower getInstance() {
        return null;
    }

    @Override
    public void upgrade() {

    }

    public Enemy findAnEnemy() throws FileNotFoundException {
        for (Enemy enemy : Controller.getInstance().gameStage.enemyList) {
            double distance = Math.sqrt((getX() - enemy.getX()) * (getX() - enemy.getX()) + (getY() - enemy.getY()) * (getY() - enemy.getY()));
            if (distance < range) return enemy;
        }
        return null;
    }

    public void attack() throws FileNotFoundException, CloneNotSupportedException {
        if (findAnEnemy() != null) {
            if (currentReloadTime <= 0) {
                Controller.getInstance().gameStage.bulletList.add(Bullet.clone(this, findAnEnemy()));
                currentReloadTime = reloadTime;
            } else
                currentReloadTime -= 1;
        }
    }

    public Point getCoordinate() {
        return coordinate;
    }

    @Override
    public int getX() {
        return X;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void setX(int x) {
        X = x;
    }

    @Override
    public void setY(int y) {
        Y = y;
    }

    @Override
    public int getY() {
        return Y;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }
}
