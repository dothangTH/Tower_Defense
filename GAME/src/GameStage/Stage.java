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
            if (enemyList.get(i).reachedEnd()) enemyList.remove(enemyList.get(i));
            else if (!enemyList.get(i).isAlive()) enemyList.remove(enemyList.get(i));
                else enemyList.get(i).move();
        }

        for (int i = towerList.size() - 1; i >= 0; i--) {
            towerList.get(i).attack();
        }

        for (int i = bulletList.size() - 1; i >= 0; i--) {
            if (bulletList.get(i).noTarget())
                bulletList.remove(i);

        }
    }

    public Map getMap() {
        return map;
    }
}
