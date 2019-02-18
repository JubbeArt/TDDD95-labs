import java.util.ArrayList;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 17/02/19
 */

public class allpairspath {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int nodesLength = io.getInt();
            int edgesLength = io.getInt();
            int queriesLength = io.getInt();

            if(nodesLength == 0 && edgesLength == 0 && queriesLength == 0){
                break;
            }

            ArrayList<Edge> edges = new ArrayList<>(nodesLength);


            for(int i = 0; i < edgesLength; i++) {
                edges.add(new Edge(io.getInt(), io.getInt(), io.getInt()));
                // add this to get non-directed graph
                //edgeLookup.get(endNode).add(new BellmanFord.BoardPlace(endNode, startNode, capacity));
            }

            FloydWarshall floydWarshall = new FloydWarshall(edges, nodesLength);

            for(int query = 0; query < queriesLength; query++) {
                int start = io.getInt();
                int end = io.getInt();
                int distance = floydWarshall.getDistance(start, end);

                if(distance == Integer.MIN_VALUE) {
                    io.println("-Infinity");
                } else if (distance == Integer.MAX_VALUE) {
                    io.println("Impossible");
                } else {
                    io.println(distance);
                }
            }

            io.println();
        }
        io.close();
    }

    static class FloydWarshall {

        ArrayList<Edge> edges;
        int[][] distances;
        int numOfNodes;

        FloydWarshall(ArrayList<Edge> edges, int numOfNodes) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.distances = new int[numOfNodes][numOfNodes];
            floydWarshall();
        }

        private void floydWarshall() {

            // set all distances to INF except distance to self
            for(int i = 0; i < numOfNodes; i++) {
                for(int j = 0; j < numOfNodes; j++) {
                    if(i == j) {
                        distances[i][j] = 0;
                    } else {
                        distances[i][j] = Integer.MAX_VALUE;
                    }
                }
            }

            // Set distance for all direct paths
            for(Edge edge : edges) {
                // edgeLookup to itself which costs > 0
                if(edge.start == edge.end && edge.cost > 0) continue;
                // might exists 2 edgeLookup between same nodes => make sure you take cheapest
                distances[edge.start][edge.end] = Math.min(edge.cost, distances[edge.start][edge.end]);
            }

            for(int k = 0; k < numOfNodes; k++) {
                for(int i = 0; i < numOfNodes; i++) {
                    for(int j = 0; j < numOfNodes; j++) {
                        // new distance contains MAX_VALUE => ignore it
                        if(distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE) continue;

                        int newDistance = distances[i][k] + distances[k][j];

                        if(newDistance < distances[i][j]) {
                            distances[i][j] = newDistance;
                        }
                    }
                }
            }


            // check for negative cycles
            for(int k = 0; k < numOfNodes; k++) {
                for(int i = 0; i < numOfNodes; i++) {
                    for(int j = 0; j < numOfNodes; j++) {
                        // new distance contains MAX_VALUE => ignore it
                        if(distances[i][k] == Integer.MAX_VALUE || distances[k][j] == Integer.MAX_VALUE) continue;

                        // new distance will pass through node "k", which includes a negative cycle
                        if(distances[k][k] < 0) {
                            distances[i][j] = Integer.MIN_VALUE;
                        }
                    }
                }
            }
        }

        int getDistance(int start, int end) {
            return distances[start][end];
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
