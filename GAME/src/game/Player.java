package game;

public class Player {
    private static int wallet;
    private static int hitPoint;

    public static int getWallet() {
        return wallet;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Player(int _startingWallet, int _startingHitPoint) {
        wallet = _startingWallet;
        hitPoint = _startingHitPoint;
    }

    public void earn (int amount) {
        wallet += amount;
    }

    public static void spend(int amount) {
        wallet -= amount;
    }

    public static void takeDamage(int amount) {
        hitPoint -= amount;
    }

    public boolean isDefeated() {
        return (hitPoint <= 0);
    }
}
