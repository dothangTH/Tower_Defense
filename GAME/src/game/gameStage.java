package game;

import entity.enemy.Enemy;
import entity.enemy.basicEnemy;
import entity.tower.Tower;
import entity.tower.normalTower;

import javax.sound.midi.Soundbank;
import java.sql.SQLOutput;
import java.util.*;

public class gameStage {
    public static void init() {
        Scanner input = new Scanner(System.in);
        String inputString = new String();
        Player player = new Player(200, 100);
        while (!player.isDefeated()) {
            inputString = input.next();
            System.out.println("You have " + player.getHitPoint() + " HP left.");

            if (inputString.equals("spawn")) {
                player.enemyStack.add(new basicEnemy(0,0));
                player.takeDamage(player.enemyStack.peek().getDamage());
                System.out.println("Spawn a basic enemy and it attacked your base, your HP decreased by " + player.enemyStack.pop().getDamage());
            }

            if (inputString.equals("buy")) {
                player.towerStack.add(new normalTower(0,0));
                if (player.spendable(player.towerStack.peek().getPrice()))
                {
                    System.out.println("You bought a normal tower");
                    player.spend(player.towerStack.peek().getPrice());
                }
                else {
                    player.towerStack.pop();
                    System.out.println("You don't have enough gold to buy this tower");
                }
            }

            if (inputString.equals("info")) {
                System.out.println("Damage: " + player.towerStack.peek().getDamage());
                System.out.println("Reload Time: " + player.towerStack.peek().getReloadTime());
                System.out.println("Range: " + player.towerStack.peek().getRange());
                System.out.println("Level: " + player.towerStack.peek().getLevel());
                System.out.println("Refund Value: " + player.towerStack.peek().getRefundValue());
            }

            if (inputString.equals("upgrade")) {
                if (player.towerStack.peek().upgradable()) {
                    System.out.println("Your tower has been upgraded");
                    player.towerStack.peek().upgrade();
                }
                else System.out.println("Your tower is not upgradable");
            }

            if (inputString.equals("sell")) {
                player.earn(player.towerStack.peek().getRefundValue());
                System.out.println("Sold your tower for " + player.towerStack.pop().getRefundValue() + " gold");
            }

            if (inputString.equals("wallet")) {
                System.out.println("You have " + player.getWallet() + " gold");
            }
        }
        System.out.println("You're defeated");
    }
}
