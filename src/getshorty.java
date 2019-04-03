import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class getshorty {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(true) {
            int numOfIntersections = io.getInt(); // n
            int numOfCorridors = io.getInt(); // m

            if(numOfCorridors == 0 && numOfIntersections == 0) break;

            edges = new ArrayList<>(numOfIntersections);
            nodeSizes = new ArrayList<>();

            for(int i = 0; i < numOfIntersections; i++) {
                edges.add(new ArrayList<>());
                nodeSizes.add(0.0d);
            }

            nodeSizes.set(0, 1.0d);

            for(int i = 0; i < numOfCorridors; i++) {
                int x = io.getInt();
                int y = io.getInt();
                double factor = io.getDouble();
                edges.get(x).add(new Edge(x, y,  factor));
                edges.get(y).add(new Edge(y, x,  factor));
            }

            dijkstra();
        }

        io.close();
    }

    static List<Double> nodeSizes;
    static public List<List<Edge>> edges;

    static void dijkstra() {
//        PriorityQueue<Integer> unvisitedNodes = new PriorityQueue<>((first, second) -> Double.compare(nodeSizes.get(second), nodeSizes.get(first)));
        PriorityQueue<Integer> unvisitedNodes = new PriorityQueue<>(Comparator.comparingDouble(index -> -nodeSizes.get(index)));

        unvisitedNodes.add(0);

        while(!unvisitedNodes.isEmpty()) {
            int node = unvisitedNodes.poll();

            // Loop through all edgeLookup out of the current node
            for(Edge neighbor : edges.get(node)) {

                double toSize = nodeSizes.get(neighbor.end);
                double newSize = nodeSizes.get(node) * neighbor.factor;

                if(newSize > toSize) {
                    unvisitedNodes.remove(neighbor.end);
                    nodeSizes.set(neighbor.end, newSize);
                    unvisitedNodes.add(neighbor.end);
                }
            }
        }

        io.printf("%.4f\n", nodeSizes.get(nodeSizes.size() - 1));
    }

    static class Edge {
        int start;
        int end;
        double factor;

        Edge(int start, int end, double factor) {
            this.start = start;
            this.end = end;
            this.factor = factor;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }
}
