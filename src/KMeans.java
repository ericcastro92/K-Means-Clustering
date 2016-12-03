import models.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.jar.Pack200;

/**
 * Created by ercastro on 12/3/16.
 */
public class KMeans {

    private ArrayList<Data> trainingSet;

    public KMeans() {
        trainingSet = new ArrayList<>();
    }

    public void loadDataSet(String path) {
        Scanner in = null;
        try {
            File dataSetFile = new File(path);
            in = new Scanner(dataSetFile);
        } catch (FileNotFoundException e) {
            print("File not found.");
            System.exit(1);
            return;
        }

        int index = 0;
        while(in.hasNext()) {
            index++;
            try {
                String buff = in.nextLine();
                if(buff.charAt(0) == '#') {
                    print(buff.substring(1));
                    continue;
                }

                String[] tuple = buff.split(" ");
                if(tuple.length != 2) {
                    throw new Exception();
                }

                double x = Double.parseDouble(tuple[0]);
                double y = Double.parseDouble(tuple[1]);
                trainingSet.add(new Data(x, y));
            } catch(Exception e) {
                printf("Bad input at %d\n", index);
                continue;
            }
        }

        printTrainingSet();
    }

    /** -- Debugging Function -- **/

    public void printTrainingSet() {
        for(Data data : trainingSet) {
            print(data);
        }
    }

    /** -- Convenience Functions -- **/

    public static void print(Object obj) {
        System.out.println(obj);
    }

    public static void printf(String format, Object... args) {
        System.out.printf(format, args);
    }

    /** -- MAIN FUNCTION -- **/

    public static void main(String[] args) {
        KMeans kMeans = new KMeans();
        kMeans.loadDataSet("/Users/ercastro/K-Means-Clustering/src/dataset.km");
    }
}
