package GameStage;

import Enemy.*;
import HUD.BuildingHUD;
import HUD.PlayerHUD;
import HUD.TowerHUD;
import Map.*;
import Tower.*;
import Object.*;
import TowerDefense.Controller;
import Util.GameButton;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Stage {
    public ArrayList<Tower> towerList;
    public ArrayList<Enemy> enemyList;
    public ArrayList<Bullet> bulletList;
    public boolean pause;
    private GameButton pauseButton = new GameButton("Data/Button/Pause.png", 10, 10, "pause");
    private int level;

    private Scanner enemyFile;
    private long lastSpawn;

    private Map map;

    public Stage(int level) throws FileNotFoundException {
        this.level = level;
        map = new Map(level);
        pause = false;
        towerList = new ArrayList<Tower>();
        enemyList = new ArrayList<Enemy>();
        bulletList = new ArrayList<Bullet>();
        lastSpawn = System.currentTimeMillis();
        enemyFile = new Scanner(new File(map.getEnemyFile()));
    }

    public void render(GraphicsContext gc) {
        if (!Player.getInstance().isDefeated()) {
            map.render(gc);
            pauseButton.render(gc);
            PlayerHUD.getInstance().render(gc);
            for (Enemy enemy : enemyList) enemy.render(gc);
            for (Tower tower : towerList) tower.render(gc);
            for (Bullet bullet : bulletList) bullet.render(gc);
            if (TowerHUD.getInstance().isShow()) TowerHUD.getInstance().render(gc);
            if (BuildingHUD.getInstance().isShow()) BuildingHUD.getInstance().render(gc);
        } else {
            gc.setFill(Color.BLACK);
            gc.fillRect(0,0,768,768);

            gc.setFill(Color.WHITESMOKE);
            gc.setFont(Font.font(null, FontWeight.MEDIUM,48));
            gc.fillText("Defeated!!", 300, 300, 168);

            GameButton home = new GameButton("Data/Button/Home.png", 300, 600, "home");
            home.render(gc);
        }
    }

    public void update() throws FileNotFoundException, CloneNotSupportedException {
        if (!pause && !Player.getInstance().isDefeated()) {
            if (enemyFile.hasNextLine() && (lastSpawn + 2000 < System.currentTimeMillis())){
                lastSpawn = System.currentTimeMillis();
                this.spawnEnemy(enemyFile.nextLine());
            }
            for (int i = enemyList.size() - 1; i >= 0; i--) {
                if (enemyList.get(i).reachedEnd()) {
                    Player.getInstance().takeDamage(enemyList.get(i).getDamage());
                    enemyList.get(i).destroy();
                    Controller.getInstance().hitFX.playFX();
                }
                else if (!enemyList.get(i).isAlive()) {
                    Player.getInstance().earn(enemyList.get(i).getReward());
                    enemyList.remove(enemyList.get(i));
                    Controller.getInstance().killFX.playFX();
                }
                else enemyList.get(i).move();
            }

            for (int i = towerList.size() - 1; i >= 0; i--) {
                towerList.get(i).attack();
            }

            for (int i = bulletList.size() - 1; i >= 0; i--) {
                if (bulletList.get(i).isDestroyed)
                    bulletList.remove(i);
                else if (bulletList.get(i).noTarget())
                    bulletList.get(i).destroy();
                else if (bulletList.get(i).reachTarget()) {
                    bulletList.get(i).dealDamage();
                    bulletList.get(i).destroy();
                }
                else bulletList.get(i).move();
            }
        }
    }


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

    public void buildTower(String type, Point location) throws CloneNotSupportedException, FileNotFoundException {
        Controller.getInstance().buildFX.playFX();
        switch (type) {
            case "AntiArmored":
                if (AntiArmoredTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                    towerList.add(AntiArmoredTower.clone(location));
                    Player.getInstance().spend(AntiArmoredTower.getInstance().getPrice());
                }
                break;

            case "Artillery":
                if (ArtilleryTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                    towerList.add(ArtilleryTower.clone(location));
                    Player.getInstance().spend(ArtilleryTower.getInstance().getPrice());
                }
                break;

            case "Blaster":
                if (BlasterTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                    towerList.add(BlasterTower.clone(location));
                    Player.getInstance().spend(BlasterTower.getInstance().getPrice());
                }
                break;

            case "SMG":
                if (SMGTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                    towerList.add(SMGTower.clone(location));
                    Player.getInstance().spend(SMGTower.getInstance().getPrice());
                }
                break;

            default:
                if (NormalTower.getInstance().getPrice() <= Player.getInstance().getWallet()) {
                    towerList.add(NormalTower.clone(location));
                    Player.getInstance().spend(NormalTower.getInstance().getPrice());
                }
                break;
        }
    }

    public void mouseInput(String opcode, int mouseX, int mouseY) throws CloneNotSupportedException, FileNotFoundException {
        switch (opcode) {
            case "click":
                if (!Player.getInstance().isDefeated()) {
                    if (BuildingHUD.getInstance().isShow())
                        BuildingHUD.getInstance().mouseInput("click", mouseX, mouseY);
                    else
                    if (TowerHUD.getInstance().isShow())
                        TowerHUD.getInstance().mouseInput(mouseX, mouseY);
                    else
                    if (pauseButton.onHover(mouseX, mouseY))
                        pauseButton.Click(mouseX, mouseY);
                    else
                    if (map.getTileMap(mouseX / Map.pixelPerBox, mouseY / Map.pixelPerBox) == 1) {
                        if (!map.isOccupied(mouseX / Map.pixelPerBox, mouseY / Map.pixelPerBox)) {
                            BuildingHUD.getInstance().toggle();
                            BuildingHUD.getInstance().setTargeting(new Point(mouseX / Map.pixelPerBox, mouseY / Map.pixelPerBox));
                        } else {
                            for (Tower tower : towerList) tower.Click(mouseX, mouseY);
                        }
                    }
                } else {
                    GameButton home = new GameButton("Data/Button/Home.png", 300, 600, "home");
                    home.Click(mouseX, mouseY);
                }
                break;

            case "hover":
                if (!BuildingHUD.getInstance().isShow()) {
                    for (Tower tower : towerList)
                        tower.Hover(mouseX, mouseY);
                }
                else BuildingHUD.getInstance().mouseInput("hover", mouseX, mouseY);
                break;
        }
    }

    public Map getMap() {
        return map;
    }
}
