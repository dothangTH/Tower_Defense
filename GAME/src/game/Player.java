package game;

import entity.enemy.Enemy;
import entity.tower.Tower;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Player {
    private static int wallet;
    private static int hitPoint;
    Stack<Enemy> enemyList;
    ArrayList<Tower> towerList;

    public static int getWallet() {
        return wallet;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Player(int _startingWallet, int _startingHitPoint) {
        enemyList = new Stack<Enemy>();
        towerList = new ArrayList<Tower>();
        wallet = _startingWallet;
        hitPoint = _startingHitPoint;
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

/*    public static void main(String[] args) {
        Player test = new Player(1000, 100);
        test.earn(500);
        test.earn(400);
        Scanner input = new Scanner(System.in);

        while (test.getHitPoint() > 0){
            String player_input = input.nextLine();
            if (player_input.equals("hurt")){
                takeDamage(5);
                System.out.println("Hitpoint cua em la: " + test.getHitPoint());
            }
            else{
                System.out.println("Nhap lai cai gi di anh ei!!!");
            }
        }

        System.out.println("Done!");

    }*/
}
