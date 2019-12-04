package GameStage;

public class Player {
    private int hitPoint;
    private int wallet;
    private static Player instance = null;

    private Player() {
        hitPoint = 100;
        wallet = 50;
    }

    public static Player getInstance() {
        if (instance == null)
            instance = new Player();
        return instance;
    }

    public boolean isDefeated() {
        return (hitPoint <= 0);
    }

    public void earn(int amount) {
        wallet += amount;
    }

    public void spend(int amount) {
        if (amount <= wallet)
            wallet -= amount;
    }

    public void takeDamage(int amount) {
        hitPoint -= amount;
    }

    public void remove() {
        instance = null;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getWallet() {
        return wallet;
    }
}
