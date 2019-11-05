package entity.tower;

public class normalTower extends Tower {
    private static int      _price          =       30;
    private static int      _upgradePrice   =       20;
    private static double   _baseDamage     =       15.0;
    private static double   _range          =       300;
    private static double   _reloadTime     =       3;
    private static int      _refundValue    =       15;


    public normalTower(int _X, int _Y) {
        super(_X, _Y);
        this.price = _price;
        this.refundValue = _refundValue;
        this.upgradePrice = _upgradePrice;
        this.damage = _baseDamage;
        this.range = _range;
        this.reloadTime = _reloadTime;
        this.penetrable = false;
        this.towerType = type.NORMAL;
    }
}
