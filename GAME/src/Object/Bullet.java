package Object;

import Enemy.Enemy;
import HUD.TowerHUD;
import Map.Map;
import Tower.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;

public class Bullet extends GameObject implements Cloneable {

    private Enemy target;
    private int damage;
    private int speed;
    private boolean penetrating;
    public boolean isDestroyed;
    private static Bullet instance = null;
    private Image image;
    private int frame;
    private String type;

    private  Bullet() {
        setX(0);
        setY(0);
        isDestroyed = false;
        speed = 0;
        damage = 0;
        target = null;
        type = null;
        image = null;
        penetrating = false;
        frame = 1;
    }

    private static Bullet getInstance(){
        if (instance == null)
            instance = new Bullet();
        return instance;
    }

    public static Bullet clone(Tower tower, Enemy enemy) throws CloneNotSupportedException {
        Bullet bullet = (Bullet) getInstance().clone();
        bullet.setX(tower.getX());
        bullet.setY(tower.getY());
        bullet.penetrating = tower.isPenetrating();
        bullet.setSpeed(tower.getSpeed());
        bullet.setDamage(tower.getDamage());
        bullet.setImage(new Image(new File("Data/Bullet/" + tower.getType() + ".png").toURI().toString()));
        bullet.target = enemy;
        return bullet;
    }

    public boolean noTarget() {
        if (target == null) return true;
        return !target.isAlive();
    }

    public boolean reachTarget() {
        double distance = Math.sqrt((getX() - target.getX()) * (getX() - target.getX()) + (getY() - target.getY()) * (getY() - target.getY()));
        return (distance < Map.pixelPerBox / 4);
    }

    public void move() {
        double modular = Math.sqrt((getX() - target.getX()) * (getX() - target.getX()) + (getY() - target.getY()) * (getY() - target.getY()));
        double velocityX = speed / modular * (target.getX() - getX());
        double velocityY = speed / modular * (target.getY() - getY());
        setX(getX() + (int) velocityX);
        setY(getY() + (int) velocityY);
    }

    public void dealDamage() {
        int amount;
        if (penetrating)
            amount = getDamage();
        else
            amount = (int) (getDamage() * (1 - target.getArmor() * 1.0 / (target.getArmor() + 10)));
        target.takeDamage(amount);
        target = null;
    }

    public void destroy() {
        if (frame == 9) isDestroyed = true;
        else {
            String path = "Data/Effect/vanish" + frame + ".png";
            this.image = new Image(new File(path).toURI().toString());
            frame++;
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        if (!TowerHUD.getInstance().isShow() || getX() < TowerHUD.getInstance().getX() || getY() < TowerHUD.getInstance().getY())
            super.render(gc);
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }
}
