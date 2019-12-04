package Object;

import TowerDefense.Controller;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import Map.*;

import java.io.FileNotFoundException;

public class GameObject {
    private Point coordinate;
    private int X;
    private int Y;
    protected Image image;

    public GameObject(){}


    public void render(GraphicsContext gc){
        gc.drawImage(this.getImage(), this.getX(), this.getY(), Map.pixelPerBox, Map.pixelPerBox);
    }

    public boolean hover(int mouseX, int mouseY) throws FileNotFoundException {
        return (getX() <= mouseX && mouseX <= getX() + Controller.getInstance().gameStage.getMap().pixelPerBox)
                && (getY() <= mouseY && mouseY <= getY() + Controller.getInstance().gameStage.getMap().pixelPerBox);
    }

    public Point getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(Point coordinate) {
        this.coordinate = coordinate;
    }

    public int getX() {
        return X;
    }
    public void setX(int x) {
        X = x;
    }

    public int getY() {
        return Y;
    }
    public void setY(int y) {
        Y = y;
    }

    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
}
