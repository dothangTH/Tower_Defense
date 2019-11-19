package game;

import entity.enemy.Enemy;
import entity.enemy.basicEnemy;
import entity.map.Point;
import entity.map.map;
import entity.tower.Tower;
import entity.tower.normalTower;

import javax.sound.midi.Soundbank;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.*;

public class gameStage {
    public static void init() throws FileNotFoundException{
        Scanner input = new Scanner(System.in);
        String inputString = new String();
        map Map = new map();
        Enemy.path = Map.findRoad(new Point(0,0), new Point (4,4));
        Player player = new Player(200, 100);
        while (!player.isDefeated()) {
            inputString = input.next();

            if (inputString.equals("spawn")) {
                player.enemyList.add(new basicEnemy(new Point(0,0), new Point(4,4)));
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

            if (inputString.equals("move")) {
                for (int i = 0; i < Player.enemyList.size(); i++)
                    Player.enemyList.get(i).updateStatus();
            }

            if (inputString.equals("getlocation")) {
                System.out.println(Player.enemyList.get(0).getCurrentLocation().getX() + " " + Player.enemyList.get(0).getCurrentLocation().getY());
            }

            System.out.println("You have " + player.getHitPoint() + " HP left.");
        }
        System.out.println("You're defeated");
    }
}
