import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class shortestpath1 {
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

            List<Dijkstra.Edge>[] edges = new ArrayList[nodesLength];

            for(int i = 0; i < nodesLength; i++) {
                edges[i] = new ArrayList<>();
            }

            for(int i = 0; i < edgesLength; i++) {
                int startNode = io.getInt();
                int endNode = io.getInt();
                int cost = io.getInt();

                edges[startNode].add(new Dijkstra.Edge(startNode, endNode, cost));
                // add this to get non-directed graph
                //edgeLookup.get(endNode).add(new BellmanFord.BoardPlace(endNode, startNode, capacity));
            }

            Dijkstra dijkstra = new Dijkstra(edges, nodesLength, startIndex);
            dijkstra.run();

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
}
