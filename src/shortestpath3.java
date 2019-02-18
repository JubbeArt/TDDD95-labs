import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 16/02/19
 */

public class shortestpath3 {
    static Kattio io = new Kattio(System.in, System.out);

    static final int MAX_INT = Integer.MAX_VALUE - 2001;
    static final int MIN_INT = Integer.MIN_VALUE + 2001;

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int nodesLength = io.getInt();
            int edgesLength = io.getInt();
            int queriesLength = io.getInt();
            int startIndex = io.getInt();

            if(nodesLength == 0 && edgesLength == 0 && queriesLength == 0 && startIndex == 0){
                break;
            }

            ArrayList<Edge> edges = new ArrayList<>(edgesLength);


            for(int i = 0; i < edgesLength; i++) {
                edges.add(new Edge(io.getInt(), io.getInt(), io.getInt()));
                // add this to get non-directed graph
                //edgeLookup.get(endNode).add(new BellmanFord.BoardPlace(endNode, startNode, capacity));
            }

            BellmanFord bellmanFord = new BellmanFord(edges, nodesLength, startIndex);

            for(int query = 0; query < queriesLength; query++) {
                int index = io.getInt();

                if(bellmanFord.isNegativeCycle(index)) {
                    io.println("-Infinity");
                    continue;
                }

                int distance = bellmanFord.getDistance(index);
                if(distance != MAX_INT) {
                    io.println(distance);
                } else {
                    io.println("Impossible");
                }
            }

            io.println();
        }
        io.close();
    }


    public static class BellmanFord {
        ArrayList<Edge> edges;
        int[] distances;
        int[] parents;
        int numOfNodes;
        int startIndex;
        boolean[] isNegativeCycle;

        BellmanFord(ArrayList<Edge> edges, int numOfNodes, int startIndex) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.distances = new int[numOfNodes];
            this.parents = new int[numOfNodes];
            this.isNegativeCycle = new boolean[numOfNodes];
            this.startIndex = startIndex;
            bellmanFord(startIndex);
        }

        private void bellmanFord(int start) {

            // set all distances to INF
            for(int i = 0; i < numOfNodes; i++) {
                distances[i] = MAX_INT;
                parents[i] = -1;
            }

            distances[start] = 0; // distance to self is 0

            for(int i = 1; i < numOfNodes; i++) {
                for(Edge edge : edges) {
                    int newDistance = distances[edge.start] + edge.cost;
                    if(newDistance < distances[edge.end] && distances[edge.start] != MAX_INT) {
                        distances[edge.end] = newDistance;
                        parents[edge.end] = edge.start;
                    }
                }
            }

            // check for negative weights
            for(int i = 1; i < numOfNodes; i++) {
                for (Edge edge : edges) {
                    // cant reach
                    if(distances[edge.start] == MAX_INT) continue;

                    // start node is part of negative cycle => end node is as well
                    if(isNegativeCycle[edge.start]) {
                        isNegativeCycle[edge.end] = true;
                        distances[edge.end] = MIN_INT;
                    }
                    // we still get cheaper routs => end node is part of negative cycle
                    else if (distances[edge.start] + edge.cost < distances[edge.end]) {
                        isNegativeCycle[edge.end] = true;
                        distances[edge.end] = MIN_INT;
                    }
                }
            }
        }

        boolean isNegativeCycle(int index) {
            return isNegativeCycle[index];
        }

        int getDistance(int toNode) {
            return distances[toNode];
        }

        // return list from start node to end node (including both)
        // return null if no path exsists
        public List<Integer> getPath(int toNode) {
            List<Integer> nodes = new ArrayList<>();

            int currentNode = toNode;

            while(true) {
                nodes.add(currentNode);
                int parent = parents[currentNode];

                // found full path
                if(parent == -1 && currentNode == startIndex) {
                    break;
                }

                // didnt find path
                if(parent == -1) {
                    return null;
                }

                currentNode = parent;
            }


            return nodes;
        }
    }

    static class Edge {
        int start;
        int end;
        int cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}
