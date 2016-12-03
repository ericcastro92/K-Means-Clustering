import models.Centroid;
import models.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.jar.Pack200;

/**
 * Created by ercastro on 12/3/16.
 */
public class KMeans {

    // Flags
    private boolean dataSetLoaded = false;

    private ArrayList<Data> trainingSet;
    private ArrayList<Centroid> centroids;
    private int clusters = 2; // Tries to find 2 clusters by default

    private double xMin = Double.MAX_VALUE;
    private double yMin = Double.MAX_VALUE;
    private double xMax = Double.MIN_VALUE;
    private double yMax = Double.MIN_VALUE;


    public KMeans() {
        trainingSet = new ArrayList<>();
        centroids = new ArrayList<>();
    }

    public KMeans(int clusters) {
        this();
        this.clusters = clusters;
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

                // Find X an Y range
                xMin = x < xMin ? x : xMin;
                yMin = y < yMin ? y : yMin;
                xMax = x > xMax ? x : xMax;
                yMax = y > yMax ? y : yMax;

                trainingSet.add(new Data(x, y));
            } catch(Exception e) {
                printf("Bad input at %d\n", index);
                continue;
            }
        }

        dataSetLoaded = true;
    }

    public void findClusters() {
        if(!dataSetLoaded) {
            print("Data set not yet loaded");
            return;
        }

        HashMap<String, ArrayList<Data>> clusterMap = new HashMap<>();
        // Randomly plot centroids
        for(int i = 0; i < clusters; i++) {
            double x = ThreadLocalRandom.current().nextDouble(xMin, xMax);
            double y = ThreadLocalRandom.current().nextDouble(yMin, yMax);
            String id = "Cluster " + (i + 1);
            centroids.add(new Centroid(x, y, id));
            clusterMap.put(id, new ArrayList<>());
        }


        // Determine first cluster assignments
        for( Data data : trainingSet ) {
            String classification = null;
            double minDist = Double.MAX_VALUE;
            for(Centroid centroid : centroids) {
                double dist = data.distance(centroid);
                if(dist < minDist) {
                    minDist = dist;
                    classification = centroid.getIdentifier();
                }
            }
            data.setClassification(classification);
            clusterMap.get(classification).add(data);
        }

        int count = 0;
        while(count < 100) {
            // Recompute centroid placement
            for(Centroid centroid : centroids) {
                ArrayList<Data> cluster = clusterMap.get(centroid.getIdentifier());
                double avgX = 0;
                double avgY = 0;
                for(Data data : cluster) {
                    avgX += data.getX();
                    avgY += data.getY();
                }
                avgX /= cluster.size();
                avgY /= cluster.size();
                centroid.x = avgX;
                centroid.y = avgY;
                clusterMap.put(centroid.getIdentifier(), new ArrayList<>());
            }

            // Recompute cluster assignments
            for( Data data : trainingSet ) {
                String classification = null;
                double minDist = Double.MAX_VALUE;
                for(Centroid centroid : centroids) {
                    double dist = data.distance(centroid);
                    if(dist < minDist) {
                        minDist = dist;
                        classification = centroid.getIdentifier();
                    }
                }
                data.setClassification(classification);
                clusterMap.get(classification).add(data);
            }

            count++;
        }

        printTrainingSet();
        printCentroids();
    }

    /** -- Debugging Function -- **/

    public void printTrainingSet() {
        for(Data data : trainingSet) {
            print(data);
        }
    }

    public void printCentroids() {
        for(Centroid centroid : centroids ) {
            print(centroid);
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
        KMeans kMeans = new KMeans(3);
        kMeans.loadDataSet("/Users/ercastro/K-Means-Clustering/src/dataset.km");
        kMeans.findClusters();
    }
}
