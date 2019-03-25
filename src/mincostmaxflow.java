import java.util.*;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 18/02/19
 */

public class mincostmaxflow {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfNodes = io.getInt();
        int numOfEdges = io.getInt();
        int source = io.getInt();
        int sink = io.getInt();

        ArrayList<Dijkstra.Edge>[] lookup = new ArrayList[numOfNodes];
        ArrayList<Dijkstra.Edge> allEdges = new ArrayList<>(numOfEdges);

        for(int i = 0; i < numOfNodes; i++) {
            lookup[i] = new ArrayList<>();
        }

        for(int i = 0; i < numOfEdges; i++) {
            int start = io.getInt();
            int end = io.getInt();
            int capacity = io.getInt();
            int cost = io.getInt();

            Dijkstra.Edge edge = new Dijkstra.Edge(start, end, cost, capacity);
            Dijkstra.Edge reverse = new Dijkstra.Edge(end, start, -cost, 0);
//            Edge reverse = new Edge(end, start, 0, Integer.MAX_VALUE);
            edge.reverse = reverse;
            reverse.reverse = edge;

            lookup[edge.start].add(edge);
            lookup[edge.end].add(reverse);
            allEdges.add(edge);
            allEdges.add(reverse);
        }

        new EdmondsKarp(allEdges, lookup, numOfNodes, source, sink);

        io.close();
    }

    static class EdmondsKarp {
        ArrayList<Dijkstra.Edge> allEdges;
        ArrayList<Dijkstra.Edge>[] edgeLookup;
        int numOfNodes;
        long totalFlow;
        int source;
        int sink;

        EdmondsKarp(ArrayList<Dijkstra.Edge> allEdges, ArrayList<Dijkstra.Edge>[] edgeLookup, int numOfNodes, int source, int sink) {
            this.numOfNodes = numOfNodes;
            this.allEdges = allEdges;
            this.edgeLookup = edgeLookup;
            this.totalFlow = 0;
            this.source = source;
            this.sink = sink;

            edmondsKarp();
        }

        private void edmondsKarp() {
            while(true) {
                // Do Dijkstra search (instead of BFS) to find the shortest path from source to sink
                Dijkstra dijkstra = new Dijkstra(edgeLookup, numOfNodes, source);
                dijkstra.runMinMax();
                List<Dijkstra.Edge> path = dijkstra.getPath(sink);

                // no valid path found by the search
                if(path == null) {
                    break;
                }

                long newFlow = Integer.MAX_VALUE;
                // check the max amount of flow possible for the found path
                for(Dijkstra.Edge edge : path) {
                    newFlow = Math.min(newFlow, edge.capacity - edge.flow);
                }
                // update node info
                for(Dijkstra.Edge edge : path) {
                    edge.flow += newFlow;
                    edge.reverse.flow -= newFlow;
                }

                totalFlow += newFlow;
            }


            // output for kattis
            long totalCost = 0;

            for(Dijkstra.Edge edge : allEdges) {
                if(edge.cost > 0) {
                    totalCost += edge.flow * edge.cost;
                }
            }

            io.println(totalFlow + " " + totalCost);
        }
    }
}
