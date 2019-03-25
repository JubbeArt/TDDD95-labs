import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 18/02/19
 */

public class Dijkstra {
    public List<Edge>[] edges;
    public int[] distances;
    public Edge[] parents;
    public int nodes;
    public int startIndex;

    Dijkstra(List<Edge>[] edges, int numberOfNodes, int startIndex) {
        this.nodes = numberOfNodes;
        this.edges = edges;
        this.distances = new int[nodes];
        this.parents = new Edge[nodes];
        this.startIndex = startIndex;
//        dijkstra(startIndex);
    }

    public void run() {
        PriorityQueue<Integer> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(index -> distances[index]));

        // set all distances to INF
        for(int i = 0; i < nodes; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = null;
        }

        distances[startIndex] = 0; // distance to self is 0
        unvisitedNodes.add(startIndex);

        while(!unvisitedNodes.isEmpty()) {
            int node = unvisitedNodes.poll();
            List<Edge> neighbors = edges[node];

            // Loop through all edgeLookup out of the current node
            for(Edge neighbor : neighbors) {
                int distance = distances[node] + neighbor.cost;

                // New shorter distance
                if(distance < distances[neighbor.end]) {
                    distances[neighbor.end] = distance;
                    parents[neighbor.end] = neighbor;
                    unvisitedNodes.add(neighbor.end);
                }
            }
        }
    }

    // dijkstra for min cost max flow problem
    public void runMinMax() {
        PriorityQueue<Integer> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(index -> distances[index]));

        // set all distances to INF
        for(int i = 0; i < nodes; i++) {
            distances[i] = Integer.MAX_VALUE;
            parents[i] = null;
        }

        distances[startIndex] = 0; // distance to self is 0
        unvisitedNodes.add(startIndex);

        while(!unvisitedNodes.isEmpty()) {
            int node = unvisitedNodes.poll();
            List<Edge> neighbors = edges[node];

            // Loop through all edgeLookup out of the current node
            for(Edge neighbor : neighbors) {

                // node can take more items
                if(/*parents[neighbor.end] == null && neighbor.end != startIndex &&*/ neighbor.capacity > neighbor.flow) {
                    int distance = distances[node] + neighbor.cost;

                    // New shorter distance
                    if(distance < distances[neighbor.end]) {
                        distances[neighbor.end] = distance;
                        parents[neighbor.end] = neighbor;
                        unvisitedNodes.add(neighbor.end);
                    }
                }
            }
        }
    }


    // get distance to some node starting from the startIndex,
    // returns Integer.MAX_VALUE if no path was found
    public int getDistance(int toNode) {
        return distances[toNode];
    }

    // return list from start node to end node (including both)
    // return null if no path exsists
    public List<Edge> getPath(int toNode) {
        Edge current = parents[toNode];
        if(current == null) return null;

        List<Edge> path = new ArrayList<>();

        while(true) {
            path.add(0, current);
            Edge parent = parents[current.start];

            // found full path
            if(parent == null && current.start == startIndex) {
                break;
            } else if (parent == null) {
                return null; // no path
            }

            current = parent;
        }


        return path;
    }

    static class Edge {
        int start;
        int end;
        int cost;
        int capacity;
        int flow;
        Edge reverse;

        Edge(int start, int end, int cost) {
            this(start, end, cost, 0);
        }

        Edge(int start, int end, int cost, int capacity) {
            this.start = start;
            this.end = end;
            this.cost = cost;
            this.capacity = capacity;
            this.flow = 0;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }
}

