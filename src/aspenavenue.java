import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class aspenavenue {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        numOfTrees = io.getInt();
        roadLength = io.getInt();
        roadWidth = io.getInt();
        trees = new ArrayList<>();

        for(int i = 0; i < numOfTrees; i++) {
            trees.add(new Point(0, io.getInt()));
        }

        trees.sort(Comparator.comparingDouble(point -> point.y));

        halfTrees = Math.floorDiv(numOfTrees, 2);
        distances = new double[halfTrees + 1][halfTrees + 1];

        solve();

        io.close();
    }

    static int numOfTrees;
    static int roadLength;
    static int roadWidth;
    static List<Point> trees;
    static int halfTrees;
    static double[][] distances;

    static void solve() {
        distances[0][0] = 0;

        double treeDifference = (double) roadLength / (halfTrees - 1);

        for(int i = 0; i < halfTrees; i++) {
            double y = i * treeDifference;
            distances[i + 1][0] = distances[i][0] + trees.get(i).distance(0,  y);
        }

        for(int i = 0; i < halfTrees; i++) {
            double y = i * treeDifference;
            distances[0][i + 1] = distances[0][i] + trees.get(i).distance(roadWidth,  y);
        }

        for(int i = 0; i < halfTrees; i++) {
            for(int j = 0; j < halfTrees; j++) {
                double y1 = i * treeDifference;
                double y2 = j * treeDifference;
                double distance1 = distances[i][j + 1] + trees.get(i + j + 1).distance(0, y1);
                double distance2 = distances[i + 1][j] + trees.get(i + j + 1).distance(roadWidth, y2);
                distances[i + 1][j + 1] = Math.min(distance1, distance2);
            }
        }

        io.printf("%.10f\n", distances[halfTrees][halfTrees]);
    }

    static class Point {
        final double x;
        final double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double distance(double x, double y) {
            return Math.sqrt((this.x - x) * (this.x - x) + (this.y - y) * (this.y - y));
        }
    }
}
