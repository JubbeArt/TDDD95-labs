import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/03/19
 */

public class freckles {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfCases = io.getInt();

        for(int Case = 0; Case < numOfCases; Case++) {
            int nodesLength = io.getInt();

            if(nodesLength == 0){
                break;
            }

            ArrayList<Node> nodes = new ArrayList<>(nodesLength);


            for(int i = 0; i < nodesLength; i++) {
                nodes.add(new Node(io.getDouble(), io.getDouble(), i));
            }

            PriorityQueue<Edge> edges = new PriorityQueue<>();

            for(int i = 0; i < nodesLength; i++) {
                for (int j = i + 1; j < nodesLength; j++) {
                    Node node1 = nodes.get(i);
                    Node node2 = nodes.get(j);

                    if (i != j) {
                        double distance = Math.sqrt((node1.x - node2.x) * (node1.x - node2.x) + (node1.y - node2.y) * (node1.y - node2.y));
                        edges.add(new Edge(Math.min(node1.index, node2.index), Math.max(node1.index, node2.index), distance));
                    }
                }
            }
            new Kruskal(edges, nodesLength);
        }
        io.close();
    }

    static class Kruskal {

        PriorityQueue<Edge> edges;
        int numOfNodes;
        UnionFind unionFind;
        double totalCost;

        Kruskal(PriorityQueue<Edge> edges, int numOfNodes) {
            this.numOfNodes = numOfNodes;
            this.edges = edges;
            this.unionFind = new UnionFind(numOfNodes);
            this.totalCost = 0;
            kruskal();
        }

        private void kruskal() {

            while(!edges.isEmpty()) {
                Edge edge = edges.poll();
                if(!unionFind.same(edge.start, edge.end)) {
                    totalCost += edge.cost;
                    unionFind.union(edge.start, edge.end);
                }
            }


            io.printf("%.2f\n", totalCost);

        }
    }

    static class Node {
        double x;
        double y;
        int index;

        Node(double x, double y, int index) {
            this.x = x;
            this.y = y;
            this.index = index;
        }
    }

    static class Edge implements Comparable<Edge> {
        int start;
        int end;
        double cost;

        Edge(int start, int end, double cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }


        public int compareTo(Edge other) {
            if(cost > other.cost) {
                return 1;
            } else if(cost < other.cost) {
                return -1;
            } else {
                return 0;
            }
        }
    }


}
