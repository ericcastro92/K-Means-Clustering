package models;

/**
 * Created by ercastro on 12/3/16.
 */
public class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Euclidean distance calculation to the given point
     * @param point Point
     */
    public double distance(Point point) {
        int q1 = point.x;
        int q2 = point.y;
        int p1 = point.x;
        int p2 = point.y;

        int xDiff = (q1 - p1);
        int yDiff = (q2 - p2);
        xDiff *= xDiff;
        yDiff *= yDiff;

        return Math.sqrt( xDiff + yDiff );
    }
}
