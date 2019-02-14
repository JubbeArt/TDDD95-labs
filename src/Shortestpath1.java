import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class Shortestpath1 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int nodesLength = io.getInt();
            int edgesLength = io.getInt();
            int queriesLength = io.getInt();
            int startIndex = io.getInt();

            if(nodesLength == 0 && edgesLength == 0 && queriesLength == 0 && startIndex == 0){
                break;
            }

            Map<Integer, List<Edge>> edges = new HashMap<>();

            for(int i = 0; i < nodesLength; i++) {
                edges.put(i, new ArrayList<>());
            }

            for(int i = 0; i < edgesLength; i++) {
                int startNode = io.getInt();
                int endNode = io.getInt();
                int cost = io.getInt();

                edges.get(startNode).add(new Edge(startNode, endNode, cost));
                // add this to get non-directed graph
                //edges.get(endNode).add(new Dijkstra.BoardPlace(endNode, startNode, cost));
            }

            Dijkstra dijkstra = new Dijkstra(edges, nodesLength, startIndex);

            for(int query = 0; query < queriesLength; query++) {
                int length = dijkstra.getDistance(io.getInt());
                if(length != Integer.MAX_VALUE) {
                    io.println(length);
                } else {
                    io.println("Impossible");
                }
            }

            io.println();
        }
        io.close();
    }



    public static class Dijkstra {
        public Map<Integer, List<Edge>> edges;
        public int[] distances;
        public int[] parents;
        public int nodes;
        public int startIndex;

        Dijkstra(Map<Integer, List<Edge>> edges, int numberOfNodes, int startIndex) {
            this.nodes = numberOfNodes;
            this.edges = edges;
            this.distances = new int[nodes];
            this.parents = new int[nodes];
            this.startIndex = startIndex;
            dijkstra(startIndex);
        }

        private void dijkstra(int start) {
            PriorityQueue<Integer> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(index -> distances[index]));

            // set all distances to INF
            for(int i = 0; i < nodes; i++) {
                distances[i] = Integer.MAX_VALUE;
                parents[i] = -1;
            }

            distances[start] = 0; // distance to self is 0
            unvisitedNodes.add(start);

            while(!unvisitedNodes.isEmpty()) {
                int node = unvisitedNodes.poll();
                List<Edge> neighbors = edges.get(node);

                // Loop through all edges out of the current node
                for(Edge neighbor : neighbors) {
                    int distance = distances[node] + neighbor.cost;

                    // New shorter distance
                    if(distance < distances[neighbor.end]) {
                        distances[neighbor.end] = distance;
                        parents[neighbor.end] = node;
                        unvisitedNodes.add(neighbor.end);
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

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }
}
