package models;

import java.util.Random;

/**
 * Created by ercastro on 12/3/16.
 */
public class Centroid extends Point {
    private String identifier;

    public Centroid(int x, int y, String identifier) {
        super(x, y);
        this.identifier = identifier;
    }
}
