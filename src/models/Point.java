package models;

/**
 * Created by ercastro on 12/3/16.
 */
public class Point {

    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Euclidean distance calculation to the given point
     * @param point Point
     */
    public double distance(Point point) {
        double q1 = x;
        double q2 = y;
        double p1 = point.x;
        double p2 = point.y;

        double xDiff = (q1 - p1);
        double yDiff = (q2 - p2);
        xDiff *= xDiff;
        yDiff *= yDiff;

        return Math.sqrt( xDiff + yDiff );
    }

    @Override
    public String toString() {
        return String.format("(%f, %f)", x, y);
    }
}
