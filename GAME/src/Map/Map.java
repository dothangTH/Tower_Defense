package Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Map {
    private int height;
    private int width ;
    private Point start;
    private Point end;
    private Image image;
    private int[][] tileMap;
    private boolean[][] occupied;
    private String enemyFile;
    public static int pixelPerBox;
    public ArrayList<Point> path;

    public final int SCREENWIDTH = 768;
    public final int SCREENHEIGHT = 768;

    public Map(int level) throws FileNotFoundException {
        this.image = new Image(new File("Data/Map/Level" + level + "/Image.png").toURI().toString());
        loadTileMap("Data/Map/Level" + level + "/TileMap.txt");
        path = findPath();
        enemyFile = "Data/Map/Level" + level + "/EnemyFile.txt";
    }

    public ArrayList<Point> findPath(){
        ArrayList<Point> road = new ArrayList<Point>();
        int pass[][] = new int[height][width];
        Point trace[][] = new Point[height][width];

        int[] DR = {0, 1, 0, -1};
        int[] DC = {1, 0, -1, 0};
        pass[start.getX()][start.getY()] = 1;

        Queue<Point> wait = new LinkedList<Point>();
        wait.add(start);

        while ( wait.peek() != null){
            Point p = wait.poll();
            int x = p.getX();
            int y = p.getY();
            for (int i = 0; i < 4; i++) {
                int u = x + DR[i];
                int v = y + DC[i];
                if (((u >=0 )&&(v>=0)) && (tileMap[u][v] == 0 && pass[u][v] != 1)){
                    trace[u][v] = new Point(x, y);
                    if (u == end.getX() && v == end.getY()) break;
                    pass[u][v] = 1;
                    wait.add(new Point(u, v));
                }
            }
        }

        Stack<Point> stack = new Stack<Point>();
        int x = end.getX();
        int y = end.getY();
        while ((x != start.getX()) || (y != start.getY())){
            stack.push(new Point(x, y));
            int u = trace[x][y].getX();
            int v = trace[x][y].getY();
            x = u;
            y = v;
        }
        stack.push(new Point(x, y));
        while (!(stack.empty())){
            road.add(stack.pop());
        }
        return road;
    }

    public void loadTileMap(String tileMapPath) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(tileMapPath));
        start = new Point(scanner.nextInt(), scanner.nextInt());
        end = new Point(scanner.nextInt(), scanner.nextInt());
        height = scanner.nextInt();
        width = scanner.nextInt();
        pixelPerBox = SCREENWIDTH/width;
        tileMap = new int[height][width];
        occupied = new boolean[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileMap[j][i] = scanner.nextInt();
                occupied[j][i] = false;
            }
        }
    }

    public boolean isOccupied(int x, int y) {
        return occupied[x][y];
    }

    public void setOccupied(boolean occupied, int X, int Y) {
        this.occupied[X][Y] = occupied;
    }

    public void render(GraphicsContext gc){
        gc.drawImage(this.getImage(), 0, 0,
                this.getWidth()*pixelPerBox,
                this.getHeight()*pixelPerBox);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Image getImage() {
        return image;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public int getTileMap(int X, int Y) {
        return tileMap[X][Y];
    }

    public String getEnemyFile() {
        return enemyFile;
    }

    public void setEnemyFile(String enemyFile) {
        this.enemyFile = enemyFile;
    }
}
