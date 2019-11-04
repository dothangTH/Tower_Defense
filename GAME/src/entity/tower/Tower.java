package entity.tower;

import entity.enemy.Enemy;

import java.util.List;

public class Tower {
    public enum type{NORMAL, SMG, ARTILLERY, ANTIARMORED, BLASTER}
    private final int maxLevel = 4;
    private final double powerMultiplyingRate = 1.2;
    private final double reloadTimeDecreasingRate = 0.8;
    private final double upgradePriceIncreasingRate = 1.5;
    private final double rangeIncreasingRate = 1.2;

    protected int price;
    protected int upgradePrice;
    protected double damage;
    protected int refundValue;
    protected double range;
    protected double reloadTime;
    private int X;
    private int Y;
    private int level;

    private List<Enemy> targetedEnemy;

    public Tower(int _X, int _Y) {
        X = _X;
        Y = _Y;
        level = 1;
    }

    public boolean upgradable() {
        return (level < maxLevel);
    }

    public void upgrade() {
        if (upgradable()) {
            Player.getPlayer().spend(upgradePrice);

            damage *= powerMultiplyingRate;
            reloadTime *= reloadTimeDecreasingRate;
            range *= rangeIncreasingRate;
            upgradePrice *= upgradePriceIncreasingRate;

            level++;
        }
    }
}
