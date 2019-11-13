package entity.enemy;

import entity.Bullet;
import entity.map.Point;
import entity.map.map;
import entity.tower.*;
import game.Player;

import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Enemy {
    public enum type{BASIC, SPEEDY, TANKER, ARMORED, BUSTER, BOSS}

    private double X, Y;
    protected double hitPoint;
    protected double armor;
    public int reward;
    protected double damage;
    protected double speed;
    protected type enemyType;

    public Enemy(int _X, int _Y) {
        X = _X;
        Y = _Y;
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public double getDamage() {
        return damage;
    }

    public double getSpeed() {
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

    public void isAtDestination() {
        Player.takeDamage(damage);
    }

    public void takeDamage(Bullet incomingBullet) {
        if (incomingBullet.tower.canPenetrate())
            hitPoint -= incomingBullet.tower.getDamage();
        else
            hitPoint -= incomingBullet.tower.getDamage() * (1 - armor * 1.0 / (armor + 10));
    }

    public void singleMove(Point p1, Point p2, double step, double speed, map map){ }

    public void move(Point start, Point end, double step, double speed, map map){
        ArrayList<Point> road = map.findRoad(start, end);
        for (int i = 0; i < road.size()-1; i++) {
            this.singleMove(road.get(i), road.get(i+1), step, speed, map);
        }
    }
}
