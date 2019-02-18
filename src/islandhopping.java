import java.util.ArrayList;
import java.util.Comparator;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 17/02/19
 */

public class islandhopping {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfTest = io.getInt();


        for(int t = 0; t < numOfTest; t++) {
            int numOfNodes = io.getInt();

            ArrayList<Node> nodes = new ArrayList<>(numOfNodes);


            for(int i = 0; i < numOfNodes; i++) {
                nodes.add(new Node(io.getDouble(), io.getDouble()));
            }

            ArrayList<Edge> edges = new ArrayList<>(numOfNodes);

            for(int i = 0; i < numOfNodes; i++) {
                for(int j = i + 1; j < numOfNodes; j++) {
                    edges.add(new Edge(nodes.get(i), nodes.get(j)));
                }
            }

            new Kruskal(edges, numOfNodes);
        }
        io.close();
    }

    static class Kruskal {

        ArrayList<Edge> edges;
        int numOfNodes;
        UnionFind unionFind;
        double totalCost;

        Kruskal(ArrayList<Edge> edges, int numOfNodes) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.unionFind = new UnionFind(edges.size());
            kruskal();
        }

        private void kruskal() {
            edges.sort(Comparator.comparingDouble(edge -> edge.cost));

            for(Edge edge : edges) {
                if(!unionFind.same(edge.n1.index, edge.n2.index)) {
                    totalCost += edge.cost;
                    unionFind.union(edge.n1.index, edge.n2.index);
                }
            }

            io.println(totalCost);

        }
    }

    static class Node {
        double x;
        double y;
        int index;

        static int currentIndex = 0;

        Node(double x, double y) {
            this.x = x;
            this.y = y;
            this.index = currentIndex;
            currentIndex++;
        }

    }

    static class Edge {
        Node n1;
        Node n2;
        double cost;

        Edge(Node n1, Node n2) {
            this.n1 = n1;
            this.n2 = n2;
            this.cost = Math.sqrt( Math.pow(n1.x - n2.x, 2) + Math.pow(n1.y - n2.y, 2) );
        }

        @Override
        public boolean equals(Object obj) {
            Edge e = (Edge) obj;
            return this.n1 == e.n1 && this.n2 == e.n2 ||
                    this.n1 == e.n2 && this.n2 == e.n1;
        }
    }
}
