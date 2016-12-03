package models;

/**
 * Created by ercastro on 12/3/16.
 */
public class Data extends Point {

    private String classification;

    public Data(double x, double y) {
        super(x, y);
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }

    @Override
    public String toString() {
        return String.format("(%f, %f): %s", x, y, classification);
    }
}
