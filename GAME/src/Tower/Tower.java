package Tower;

import Enemy.Enemy;
import Object.*;
import Map.*;
import TowerDefense.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;

public abstract class Tower extends GameObject implements UpgradableObject, Cloneable{
    private Point coordinate;
    private int X;
    private int Y;
    private int currentReloadTime;
    private final double DMGRATE        =   1.2;
    private final double RLDRATE        =   0.8;
    private final double UPGRDRATE      =   1.5;
    private final double RFNDRATE       =   1.4;
    private final double RNGRATE        =   1.2;
    private final int    MAXLVL         =   4;

    protected int damage;
    protected boolean penetrating;
    protected int reloadTime;
    protected int refundValue;
    protected int upgradePrice;
    protected int price;
    protected int speed;
    protected int range;
    protected int level;
    protected String type;

    protected Tower() {
        this.coordinate = new Point(0,0);
        this.X = 0;
        this.Y = 0;
        this.currentReloadTime = 0;
        this.level = 1;
    }

    public boolean upgradable() {
        return this.level < MAXLVL;
    }

    @Override
    public void upgrade() {
        if (upgradable()) {
            this.damage *= DMGRATE;
            this.reloadTime *= RLDRATE;
            this.refundValue *= RFNDRATE;
            this.upgradePrice *= UPGRDRATE;
            this.range *= RNGRATE;
            this.level += 1;
        }
    }

    @Override
    public void onHover(int mouseX, int mouseY, Object caller) throws FileNotFoundException {
        if (hover(mouseX, mouseY)){
            renderRange();
        }
    }

    @Override
    public void onClick(int mouseX, int mouseY, Object caller) {

    }

    public void renderRange(){

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

    public boolean onHover(int mouseX, int mouseY) {
        return true;
    }

    public void renderRange(GraphicsContext gc) {

    }

    public boolean isPenetrating() {
        return penetrating;
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

    public int getCurrentReloadTime() {
        return currentReloadTime;
    }

    public void setCurrentReloadTime(int currentReloadTime) {
        this.currentReloadTime = currentReloadTime;
    }
}
