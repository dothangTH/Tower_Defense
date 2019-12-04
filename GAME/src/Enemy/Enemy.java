package Enemy;

import GameStage.Player;
import HUD.TowerHUD;
import TowerDefense.*;
import Map.*;
import Object.*;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public abstract class Enemy extends GameObject implements Cloneable, MovableObject {
    private int X;
    private int Y;
    private Point coordinate;

    protected String type;
    protected int damage;
    protected int speed;
    protected int armor;
    protected int hitPoint;
    protected int reward;


    protected Enemy() {
        this.coordinate = new Point(0,0);
        this.X = 0;
        this.Y = 0;
    }

    public void move() throws FileNotFoundException {
        Point nextStep = null;
        if (!reachedEnd()) {
            nextStep = Controller.getInstance().gameStage.getMap().path.get(Controller.getInstance().gameStage.getMap().path.indexOf(coordinate) + 1);
        }
        X += (nextStep.getX() - coordinate.getX()) * speed;
        Y += (nextStep.getY() - coordinate.getY()) * speed;
        boolean reachedNextStep = (Math.abs(nextStep.getX() * Map.pixelPerBox - X) < speed && Math.abs(nextStep.getY() * Map.pixelPerBox - Y) < speed);
        if (reachedNextStep) coordinate = nextStep;

    }

    public static Enemy getInstance() {
        return null;
    }

    public boolean reachedEnd() throws FileNotFoundException {
        return coordinate.equals(Controller.getInstance().gameStage.getMap().getEnd());
    }

    public boolean isAlive() {
        return hitPoint > 0;
    }

    public void destroy() throws FileNotFoundException {
        Controller.getInstance().gameStage.enemyList.remove(this);
    }

    public void takeDamage(int amount) {
        hitPoint -= amount;
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!TowerHUD.getInstance().isShow() || getX() < TowerHUD.getInstance().getX() || getY() < TowerHUD.getInstance().getY())
            super.render(gc);
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public void setX(int x) {
        X = x;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public void setY(int y) {
        Y = y;
    }

    public int getArmor() {
        return armor;
    }

    public int getReward() {
        return reward;
    }

    public int getDamage() {
        return damage;
    }

    public Point getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }
}
