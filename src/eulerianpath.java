import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 19/02/19
 */

public class eulerianpath {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(true) {
            numOfNodes = io.getInt();
            numOfEdges = io.getInt();

            if(numOfNodes == 0 && numOfEdges == 0) {
                break;
            }

            nodes = new Node[numOfNodes];

            for(int i = 0; i < numOfNodes; i++) {
                nodes[i] = new Node(i);
            }

            allEdges = new ArrayList<>(numOfEdges);

            for (int i = 0; i < numOfEdges; i++) {
                int start = io.getInt();
                int end = io.getInt();

                Edge edge = new Edge(start, end, i);
                nodes[start].addEdge(edge);
                allEdges.add(edge);
            }

            solve();
        }

        io.close();
    }

    static Node[] nodes;
    static List<Edge> allEdges;
    static int numOfNodes;
    static int numOfEdges;
    static int edgesLeft;

    static void solve() {
        // do a quick check if graph has Euler Path (or an Euler circuit)
        int numOfOddDegrees = 0;

        Node start = nodes[0];

        for(int i = 0; i < numOfNodes; i++) {
            if(nodes[i].edges.size() % 2 == 1) {
                numOfOddDegrees++;
                // start on a odd degree node (if exist, otherwise just use first node)
                start = nodes[i];
            }
        }

        // if 2 => euler path, if 0 => euler circuit (same start and end)
        if(numOfOddDegrees != 2 && numOfOddDegrees != 0) {
            io.println("Impossible");
            return;
        }

        edgesLeft = numOfEdges;
        List<Integer> solution = new ArrayList<>();
//        solution.add(start.);

        Stack<Node> unvisitedNodes = new Stack<>();
        unvisitedNodes.add(start);

        Node current = start;

        while(!unvisitedNodes.isEmpty() && edgesLeft > 0) {

            if(current.edges.size() > 0) {
                unvisitedNodes.add(current);
                int endIndex = current.edges.remove(0).end;
                current = nodes[endIndex];
                edgesLeft--;
                continue;
            }

            // backtrack
            solution.add(current.index);
            current = unvisitedNodes.pop();
        }

        io.println(solution);
    }

    static class Node {
        int index;
        List<Edge> edges;

        Node(int index) {
            this.index = index;
            edges = new ArrayList<>();
        }

        void addEdge(Edge edge) {
            edges.add(edge);
        }

    }

    static class Edge {
        int start;
        int end;
        int id;
        boolean isUsedInSolution;

        Edge(int start, int end, int id) {
            this.start = start;
            this.end = end;
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("(%d -> %d)", start, end);
        }
    }
}
