package game;

import entity.enemy.Enemy;
import entity.tower.Tower;


import java.util.ArrayList;
import java.util.Scanner;


import java.util.Stack;

public class Player {
    private static int wallet;
    private static double hitPoint;

    public static ArrayList<Enemy> enemyList ;
    public static ArrayList<Tower> towerList;

    public static int getWallet() {
        return wallet;
    }

    public double getHitPoint() {
        return hitPoint;
    }

    public Player(int _startingWallet, double _startingHitPoint) {

        enemyList = new ArrayList<Enemy>();
        towerList = new ArrayList<Tower>();
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

    public static void takeDamage(double amount) {
        hitPoint -= amount;
    }

    public boolean isDefeated() {
        return (hitPoint <= 0);
    }
    
    public void buyTower() {}

}
