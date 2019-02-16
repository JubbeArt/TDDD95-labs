import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class shortestpath2 {
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

            ArrayList<Edge>[] neighbors = new ArrayList[nodesLength];

            for(int i = 0; i < nodesLength; i++) {
                neighbors[i] = new ArrayList<>();
            }

            for(int i = 0; i < edgesLength; i++) {
                neighbors[io.getInt()].add(new Edge(io.getInt(), io.getInt(), io.getInt(), io.getInt()));
                // add this to get non-directed graph
                //neighbors.get(endNode).add(new Dijkstra.BoardPlace(endNode, startNode, cost));
            }


            Dijkstra dijkstra = new Dijkstra(neighbors, nodesLength, startIndex);

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
        public ArrayList<Edge>[] neighbors;
        public int[] distances;
        public int[] parents;
        public int nodes;
        public int startIndex;

        Dijkstra(ArrayList<Edge>[] neighbors, int numberOfNodes, int startIndex) {
            this.nodes = numberOfNodes;
            this.neighbors = neighbors;
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
                List<Edge> neighbors = this.neighbors[node];

                // Loop through all neighbors out of the current node
                for(Edge neighbor : neighbors) {

                    if(neighbor.getNextAvaliableTime(distances[node]) == Integer.MAX_VALUE) continue;
                    int distance = Math.max(distances[node], neighbor.getNextAvaliableTime(distances[node])) + neighbor.cost;

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
        int end;
        int startTime; // t0
        int period; // P
        int cost; // d

        Edge(int end, int startTime, int period, int cost) {
            this.end = end;
            this.startTime = startTime;
            this.period = period;
            this.cost = cost;
        }

        int getNextAvaliableTime(int current) {
            if(current <= startTime) return startTime;
            if(period != 0) return (int)Math.ceil((current - startTime) / (double) period) * period + startTime;
            return Integer.MAX_VALUE;
        }
    }
}
