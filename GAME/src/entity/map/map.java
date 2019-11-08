package entity.map;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class map {
    private int width, height;
    private String nameOfMap;
    private String mapPath;
    private int[][] mapArray;
    private Image[][] mapImage;

    public map(){
        this.width = 5;
        this.height = 5;
    }

    public void mapInitialize(String tileMapPath, ArrayList<Image> imageArrayList) throws FileNotFoundException {
        mapArray = new int[this.getHeight()][this.getWidth()];
        loadMapArray(tileMapPath);
        loadMapImage(imageArrayList);
    }

    public void loadMapArray(String tileMapPath) throws FileNotFoundException {
        Scanner s = new Scanner(new File(tileMapPath));
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                mapArray[i][j] = s.nextInt();
            }
        }
    }

    public void loadMapImage(ArrayList<Image> imageArrayList){
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                mapImage[i][j] = imageArrayList.get(mapArray[i][j]);
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getMapPath() {
        return mapPath;
    }

    public void setMapPath(String mapPath) {
        this.mapPath = mapPath;
    }
}
