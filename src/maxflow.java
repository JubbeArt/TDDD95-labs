import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 17/02/19
 */

public class maxflow {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfNodes = io.getInt();
        int numOfEdges = io.getInt();
        int source = io.getInt();
        int sink = io.getInt();

        ArrayList<Edge>[] lookup = new ArrayList[numOfNodes];
        ArrayList<Edge> allEdges = new ArrayList<>(numOfEdges);

        for(int i = 0; i < numOfNodes; i++) {
            lookup[i] = new ArrayList<>();
        }

        for(int i = 0; i < numOfEdges; i++) {
            int start = io.getInt();
            int end = io.getInt();
            int capacity = io.getInt();

            Edge edge = new Edge(start, end, capacity);
            Edge reverse = new Edge(end, start, 0);
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
        ArrayList<Edge> allEdges;
        ArrayList<Edge>[] edgeLookup;
        int numOfNodes;
        long totalFlow;
        int source;
        int sink;

        EdmondsKarp(ArrayList<Edge> allEdges, ArrayList<Edge>[] edgeLookup, int numOfNodes, int source, int sink) {
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

                // Do BFS to find the shortest path from source to sink
                Queue<Integer> unsearchedNodes = new ArrayDeque<>();
                unsearchedNodes.add(source);

                Edge[] parents  = new Edge[numOfNodes];

                while(!unsearchedNodes.isEmpty()) {
                    int current = unsearchedNodes.poll();

                    for(Edge edge : edgeLookup[current]) {
                        // new available path found (that can take more items)
                        if(parents[edge.end] == null && edge.end != source && edge.capacity > edge.flow) {
                            parents[edge.end] = edge;
                            unsearchedNodes.add(edge.end);
                        }
                    }
                }

                // no valid path found by the BFS
                if(parents[sink] == null) {
                    break;
                }

                long newFlow = Integer.MAX_VALUE;

                // check the max amount of flow possible for the found path
                for(Edge edge = parents[sink]; edge != null; edge = parents[edge.start]) {
                    newFlow = Math.min(newFlow, edge.capacity - edge.flow);
                }

                // update node info
                for(Edge edge = parents[sink]; edge != null; edge = parents[edge.start]) {
                    edge.flow += newFlow;
                    edge.reverse.flow -= newFlow;

                }

                totalFlow += newFlow;
            }


            // output for kattis
            int numberOfNodes = 0;

            for(Edge edge : allEdges) {
                if(edge.flow > 0) numberOfNodes++;
            }

            io.println(numOfNodes + " " + totalFlow + " " + numberOfNodes);

            for(Edge edge : allEdges) {
                if(edge.flow > 0) {
                    io.println(edge.start + " " + edge.end + " " + edge.flow);
                }
            }
        }
    }

    static class Edge {
        int start;
        int end;
        long capacity;
        long flow;
        Edge reverse;

        Edge(int start, int end, long capacity) {
            this.start = start;
            this.end = end;
            this.capacity = capacity;
            this.flow = 0;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }


}
