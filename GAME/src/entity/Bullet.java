package entity;

import entity.enemy.Enemy;
import entity.tower.Tower;

public class Bullet {
    int X,Y;
    public Tower tower;
    public Enemy target;

    public Bullet (Tower tower) {
        this.tower = tower;
        X = tower.getX();
        Y = tower.getY();
    }

    public boolean reachTarget() {
        return ((X == target.getX()) && (Y == target.getY()));
    }
}
