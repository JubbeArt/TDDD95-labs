import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 17/02/19
 */

public class minspantree {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int nodesLength = io.getInt();
            int edgesLength = io.getInt();

            if(nodesLength == 0 && edgesLength == 0){
                break;
            }

            ArrayList<Edge> edges = new ArrayList<>(edgesLength);


            for(int i = 0; i < edgesLength; i++) {
                int start = io.getInt();
                int end = io.getInt();

                edges.add(new Edge(Math.min(start, end), Math.max(start, end), io.getInt()));
            }

            new Kruskal(edges, nodesLength);
        }
        io.close();
    }

    static class Kruskal {

        ArrayList<Edge> edges;
        int numOfNodes;
        UnionFind unionFind;
        int totalCost;

        Kruskal(ArrayList<Edge> edges, int numOfNodes) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.unionFind = new UnionFind(numOfNodes);
            kruskal();
        }

        private void kruskal() {
            if(numOfNodes == 1) {
                io.println(0);
                return;
            }

            edges.sort(Comparator.comparingInt(edge -> edge.cost));

            ArrayList<Edge> solution = new ArrayList<>();

            for(Edge edge : edges) {
                if(!unionFind.same(edge.start, edge.end)) {
                    solution.add(edge);
                    totalCost += edge.cost;
                    unionFind.union(edge.start, edge.end);
                }
            }

            if(solution.size() != numOfNodes - 1) {
                io.println("Impossible");
                return;
            }

            io.println(totalCost);
            solution.sort((e1, e2) -> {
                if(e1.start == e2.start) return e1.end - e2.end;
                return e1.start - e2.start;
            });

            for(Edge edge : solution) {
                io.println(edge.start + " " + edge.end);
            }

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
