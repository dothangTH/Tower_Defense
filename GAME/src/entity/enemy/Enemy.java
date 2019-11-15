package entity.enemy;

import entity.Bullet;
import entity.map.*;
import entity.tower.*;
import game.Player;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

abstract public class Enemy {
    public enum type{BASIC, SPEEDY, TANKER, ARMORED, BUSTER, BOSS}

    private int X, Y;
    private Point currentLocation;
    private Point startingPoint, destination;
    protected double hitPoint;
    protected double armor;
    public int reward;
    protected double damage;
    protected int speed;
    protected type enemyType;
    private ArrayList<Point> path;

    public Enemy(Point startingPoint, Point destination, int _X, int _Y) {
        X = _X;
        Y = _Y;
        this.startingPoint = startingPoint;
        currentLocation = startingPoint;
        this.destination = destination;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public double getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }

    public double distanceToTower(Tower tower) {
        double result = sqrt((X - tower.getX()) * (X - tower.getX()) + (Y - tower.getY()) * (Y- tower.getY()));
        return result;
    }

    public boolean isAlive() {
        return (hitPoint > 0);
    }

    public boolean isAtDestination() {
        return (currentLocation.equals(destination));
    }

    public void takeDamage(Bullet incomingBullet) {
        if (incomingBullet.tower.canPenetrate())
            hitPoint -= incomingBullet.tower.getDamage();
        else
            hitPoint -= incomingBullet.tower.getDamage() * (1 - armor * 1.0 / (armor + 10));
    }

    //public void singleMove(Point p1, Point p2, double step, double speed, map map){ }

    public void move(/*Point start, Point end, double step, double speed, map map*/){
        Point nextStep = path.get(path.indexOf(currentLocation) + 1);
        X += (nextStep.getX() - currentLocation.getX()) * speed;
        Y += (nextStep.getY() - currentLocation.getY()) * speed;
        boolean reachedNextStep = (((X / map.pixelPerBox) == nextStep.getX()) || ((Y / map.pixelPerBox) == nextStep.getY()));
        if (reachedNextStep) currentLocation = nextStep;
    }

    public void updateStatus() {
        if (!this.isAlive()) {
            Player.enemyList.remove(this);
            return;
        }

        if (this.isAtDestination()) {
            Player.takeDamage(this.getDamage());
            Player.enemyList.remove(this);
            return;
        }

        move();
    }
}
