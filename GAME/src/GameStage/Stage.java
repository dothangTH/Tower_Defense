package GameStage;

import Enemy.*;
import Map.*;
import Tower.*;
import Object.*;
import TowerDefense.Controller;
import javafx.scene.canvas.GraphicsContext;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Stage {
    public ArrayList<Tower> towerList;
    public ArrayList<Enemy> enemyList;
    public ArrayList<Bullet> bulletList;
    private int level;

    private Map map;

    public Stage(int level) throws FileNotFoundException {
        this.level = level;
        map = new Map(level);
        towerList = new ArrayList<Tower>();
        enemyList = new ArrayList<Enemy>();
        bulletList = new ArrayList<Bullet>();
    }

    public void render(GraphicsContext gc) {
        map.render(gc);
        for (Enemy enemy : enemyList) enemy.render(gc);
        for (Tower tower : towerList) tower.render(gc);
        for (Bullet bullet : bulletList) bullet.render(gc);
    }

    public void update() throws FileNotFoundException, CloneNotSupportedException {
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            if (enemyList.get(i).reachedEnd()) {
                Player.getInstance().takeDamage(enemyList.get(i).getDamage());
                enemyList.remove(enemyList.get(i));
            }
            else if (!enemyList.get(i).isAlive()) {
                Player.getInstance().earn(enemyList.get(i).getReward());
                enemyList.remove(enemyList.get(i));
            }
                else enemyList.get(i).move();
        }

        for (int i = towerList.size() - 1; i >= 0; i--) {
            towerList.get(i).attack();
        }

        for (int i = bulletList.size() - 1; i >= 0; i--) {
            if (bulletList.get(i).noTarget())
                bulletList.remove(i);
            else if (bulletList.get(i).reachTarget()) {
                bulletList.get(i).dealDamage();
                bulletList.remove(i);
            } else bulletList.get(i).move();
        }
    }

//    public void onClick(int mouseX, int mouseY, GraphicsContext gc) throws FileNotFoundException {
//        map.onClick(mouseX, mouseY);
//        for (Tower tower : towerList) tower.onClick(mouseX, mouseY, gc);
//    }
//
//    public void onHover(int mouseX, int mouseY, GraphicsContext gc) throws FileNotFoundException {
//        for (Tower tower : towerList) tower.onHover(mouseX, mouseY, gc);
//    }

    public void spawnEnemy(String type) throws CloneNotSupportedException {
        switch (type) {
            case "Armored":
                enemyList.add(ArmoredEnemy.clone(map.getStart()));
                break;

            case "Boss":
                enemyList.add(Boss.clone(map.getStart()));
                break;

            case "Buster":
                enemyList.add(BusterEnemy.clone(map.getStart()));
                break;

            case "Speedy":
                enemyList.add(SpeedyEnemy.clone(map.getStart()));
                break;

            case "Tank":
                enemyList.add(TankEnemy.clone(map.getStart()));
                break;

            default:
                enemyList.add(BasicEnemy.clone(map.getStart()));
                break;
        }
    }

    public void buildTower(String type, Point location) throws CloneNotSupportedException {
        switch (type) {
            case "AntiArmored":
                towerList.add(AntiArmoredTower.clone(location));
                break;

            case "Artillery":
                towerList.add(ArtilleryTower.clone(location));
                break;

            case "Blaster":
                towerList.add(BlasterTower.clone(location));
                break;

            case "SMG":
                towerList.add(SMGTower.clone(location));
                break;

            default:
                towerList.add(NormalTower.clone(location));
                break;
        }
    }

    public void mouseInput(String opcode, int mouseX, int mouseY) {
        switch (opcode) {
            case "click":
                for (Tower tower : towerList)
                    tower.Click(mouseX, mouseY);
                break;
            case "hover":
                for (Tower tower : towerList)
                    tower.Hover(mouseX, mouseY);
                break;
        }
    }

    public Map getMap() {
        return map;
    }
}
