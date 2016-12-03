package models;

import java.util.Random;

/**
 * Created by ercastro on 12/3/16.
 */
public class Centroid extends Point {
    private String identifier;

    public Centroid(double x, double y, String identifier) {
        super(x, y);
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return String.format("%s @ (%f, %f)", identifier, x, y);
    }
}
