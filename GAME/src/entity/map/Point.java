package entity.map;

public class Point {
    private int X, Y;

    public Point(int x, int y) {
        X = x;
        Y = y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean equals(Point other) {
        return ((this.X == other.getX()) && (this.Y == other.getY()));
    }
}
