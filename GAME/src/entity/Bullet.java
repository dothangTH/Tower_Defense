package entity;

import entity.enemy.Enemy;
import entity.tower.Tower;
import java.lang.Math;

import static java.lang.Math.sqrt;

public class Bullet {
    private double X,Y;
    private double movedDistance;
    private double velocity;
    private final double maximumDistance = 1000;
    public Tower tower;
    public Enemy target;

    public Bullet (Tower tower, Enemy target) {
        this.tower = tower;
        this.target = target;
        X = tower.getX();
        Y = tower.getY();
        movedDistance = 0;
        velocity = tower.getBulletSpeed();
    }

    public boolean reachTarget() {
        return ((X == target.getX()) && (Y == target.getY()));
    }

    public boolean reachMaximumDistance() {
        return (movedDistance == maximumDistance);
    }

    public void updateCoordinate() {
        double modular = sqrt((X - target.getX()) * (X - target.getX()) + (Y - target.getY()) * (Y - target.getY()));
        double velocityX = velocity / modular * (target.getX() - X);
        double velocityY = velocity / modular * (target.getY() - Y);
        X += velocityX;
        Y += velocityY;
        movedDistance += velocity;
    }
}

