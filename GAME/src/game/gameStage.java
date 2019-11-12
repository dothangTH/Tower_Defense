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
                player.enemyList.add(new basicEnemy(0,0));
                player.takeDamage(player.enemyList.get(0).getDamage());
                //System.out.println("Spawn a basic enemy and it attacked your base, your HP decreased by " + player.enemyList.get().getDamage());
            }

            if (inputString.equals("buy")) {
                player.towerList.add(new normalTower(0,0));
                if (player.spendable(player.towerList.get(0).getPrice()))
                {
                    System.out.println("You bought a normal tower");
                    player.spend(player.towerList.get(0).getPrice());
                }
                else {
                    player.towerList.remove(0);
                    System.out.println("You don't have enough gold to buy this tower");
                }
            }

            if (inputString.equals("info")) {
                System.out.println("Damage: " + player.towerList.get(0).getDamage());
                System.out.println("Reload Time: " + player.towerList.get(0).getReloadTime());
                System.out.println("Range: " + player.towerList.get(0).getRange());
                System.out.println("Level: " + player.towerList.get(0).getLevel());
                System.out.println("Refund Value: " + player.towerList.get(0).getRefundValue());
            }

            if (inputString.equals("upgrade")) {
                if (player.towerList.get(0).upgradable()) {
                    System.out.println("Your tower has been upgraded");
                    player.towerList.get(0).upgrade();
                }
                else System.out.println("Your tower is not upgradable");
            }

            if (inputString.equals("sell")) {
                player.earn(player.towerList.get(0).getRefundValue());
                System.out.println("Sold your tower for " + player.towerList.remove(0).getRefundValue() + " gold");
            }

            if (inputString.equals("wallet")) {
                System.out.println("You have " + player.getWallet() + " gold");
            }
        }
        System.out.println("You're defeated");
    }
}
