package entity.enemy;

import entity.tower.*;
import game.Player;

public class Enemy {
    public enum type{BASIC, SPEEDY, TANKER, ARMORED, BURSTER, BOSS}

    private int X, Y;
    protected double hitPoint;
    protected double armor;
    public int reward;
    protected int damage;
    protected int speed;
    protected type enemyType;

    public Enemy(int _X, int _Y) {
        X = _X;
        Y = _Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public int getReward() {
        return reward;
    }

    public boolean isAlive() {
        return (hitPoint > 0);
    }

    public void isAtDestination() {
        Player.takeDamage(damage);
    }

    public void takeDamage(Tower attackingTower) {
        if (attackingTower.canPenetrate())
            hitPoint -= attackingTower.getDamage();
        else
            hitPoint -= attackingTower.getDamage() * (1 - armor * 1.0 / (armor + 10));
    }
}
