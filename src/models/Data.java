package models;

/**
 * Created by ercastro on 12/3/16.
 */
public class Data extends Point {

    private String classification;

    public Data(int x, int y) {
        super(x, y);
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getClassification() {
        return classification;
    }
}
