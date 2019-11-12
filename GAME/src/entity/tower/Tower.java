package entity.tower;

import entity.Bullet;
import entity.enemy.Enemy;
import game.Player;

import java.util.ArrayList;
import java.util.List;

abstract public class Tower{
    public enum type{NORMAL, SMG, ARTILLERY, ANTIARMORED, BLASTER}
    private final int maxLevel = 4;
    private final double powerMultiplyingRate = 1.2;
    private final double reloadTimeDecreasingRate = 0.8;
    private final double upgradePriceIncreasingRate = 1.5;
    private final double refundValueIncreasingRate = 1.4;
    private final double rangeIncreasingRate = 1.2;

    protected int price;
    protected int upgradePrice;
    protected double damage;
    protected int refundValue;
    protected double range;
    protected double reloadTime;
    private double X;
    private double Y;
    private int level;
    protected double bulletSpeed;
    protected boolean penetrable;
    protected type towerType;
    protected List<Bullet> bulletList;

    public Tower(int _X, int _Y) {
        X = _X;
        Y = _Y;
        level = 1;
        bulletList = new ArrayList<Bullet>();
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }

    public int getPrice() {return price;}

    public int getUpgradePrice() {
        return upgradePrice;
    }

    public double getDamage() {
        return damage;
    }

    public double getReloadTime() {return reloadTime;}

    public double getRange() {return range;}

    public int getLevel() {return level;}

    public double getBulletSpeed() {
        return bulletSpeed;
    }

    public int getRefundValue() {return refundValue;}

    public boolean canPenetrate() {
        return penetrable;
    }

    public boolean upgradable() {
        return ((level < maxLevel) && (Player.getWallet() >= upgradePrice));
    }

    public void upgrade() {
        if (upgradable()) {
            Player.spend(getUpgradePrice());

            damage *= powerMultiplyingRate;
            reloadTime *= reloadTimeDecreasingRate;
            range *= rangeIncreasingRate;
            upgradePrice *= upgradePriceIncreasingRate;
            refundValue *= refundValueIncreasingRate;

            level++;
        }
    }

    public void sell() {
        for (int i = 0; i < Player.towerList.size(); i++)
            if (Player.towerList.get(i).equals(this)) {
                Player.earn(this.getRefundValue());
                Player.towerList.remove(i);
                return;
            }
    }

    abstract public void attack();
}
