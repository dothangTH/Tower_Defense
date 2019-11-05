package game;

import entity.enemy.Enemy;
import entity.tower.Tower;

import java.util.Stack;

public class Player {
    private static int wallet;
    private static int hitPoint;
    Stack<Enemy> enemyStack;
    Stack<Tower> towerStack;

    public static int getWallet() {
        return wallet;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Player(int _startingWallet, int _startingHitPoint) {
        enemyStack = new Stack<>();
        towerStack = new Stack<>();
        wallet = _startingWallet;
        hitPoint = _startingHitPoint;
    }

    public boolean spendable(int amount) {
        return (amount <= wallet);
    }

    public void earn (int amount) {
        wallet += amount;
    }

    public static void spend(int amount) {
        wallet -= amount;
    }

    public static void takeDamage(int amount) {
        hitPoint -= amount;
    }

    public boolean isDefeated() {
        return (hitPoint <= 0);
    }

    public void buyTower() {}
}
