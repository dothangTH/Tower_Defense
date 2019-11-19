package entity.enemy;

import entity.map.Point;

public class basicEnemy extends Enemy {
    private static double   _hitPoint    =   40;
    private static int      _speed       =   1;
    private static int      _reward      =   5;
    private static double   _armor       =   2.5;
    private static double   _damage      =   5;

    public basicEnemy(Point startingPointint, Point destination) {
        super(startingPointint, destination);
        this.hitPoint = _hitPoint;
        this.speed = _speed;
        this.armor = _armor;
        this.damage = _damage;
        this.reward = _reward;
        this.enemyType = type.BASIC;
    }
}
