package entity.map;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class map {
    private int width, height;
    public int pixelPerBox;
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

    public ArrayList<Point> findRoad(Point start, Point end){
        ArrayList<Point> road = new ArrayList<Point>();

        //Algorithm
        int[] DR = {0, 1, 0, -1};
        int[] DC = {1, 0, -1, 0};

        int pass[][] = new int[mapArray.length][mapArray.length];
        Point trace[][] = new Point[mapArray.length][mapArray.length];
        pass[start.getX()][start.getY()] = 1;

        Queue<Point> wait = new LinkedList<Point>();
        wait.add(start);

        while ( wait.peek() != null){
            Point p = wait.poll();
            int x = p.getX();
            int y = p.getY();
            for (int i = 0; i < 3; i++) {
                int u = x + DR[i];
                int v = y + DC[i];
                if (mapArray[u][v] == 0 && pass[u][v] != 1){
                    trace[u][v] = new Point(x, y);
                    if (u == end.getX() && v == end.getY()) break;
                    pass[u][v] = 1;
                    wait.add(new Point(u, v));
                }
            }
        }
        Stack<Point> something = new Stack<Point>();
        int x = end.getX();
        int y = end.getY();
        while ((x != start.getX()) || (y != start.getY())){
            something.push(new Point(x, y));
            x = trace[x][y].getX();
            y = trace[x][y].getY();
        }
        something.push(new Point(x, y));
        while (!(something.empty())){
            road.add(something.pop());
        }
        return road;
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
