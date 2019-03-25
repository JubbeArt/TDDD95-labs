import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 19/02/19
 */

public class eulerianpath2 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(true) {
            numOfNodes = io.getInt();
            numOfEdges = io.getInt();

            if(numOfNodes == 0 && numOfEdges == 0) {
                break;
            }

            edges = new ArrayList<>(numOfNodes);
            edgeUsed = new ArrayList<>(numOfNodes);

            for(int i = 0; i < numOfNodes; i++) {
                edges.set(i, new ArrayList<>());
                edgeUsed.set(i, new ArrayList<>());
            }

            for (int i = 0; i < numOfEdges; i++) {
                int start = io.getInt();
                int end = io.getInt();

                edges.get(start).add(end);
                edgeUsed.get(start).add(false);
                numOfEdgesOut[start]++;
                numOfEdgesIn[end]++;
            }

            List<Integer> solution = solve();

            if(solution == null) {
                io.println("Impossible");
            } else {
                for(int i : solution) {
                    io.print(i + " ");
                }
                io.println();
            }
        }

        io.close();
    }

    static int[] numOfEdgesIn;
    static int[] numOfEdgesOut;
    static int numOfNodes;
    static int numOfEdges;
    static List<List<Integer>> edges;
    static List<List<Boolean>> edgeUsed;

    /**
     * Steps of this algorithms
     *
     * 1. find start node and end node by looking at node degree (number of edges in and out)
     * 2. find ONE path from start to end with either BFS or DFS
     * 3. take every other path (these will be cycles) that does not include an edge in the path found in the previous step
     * 4. add paths from step 2 and 3 to get full solution
     */

    static List<Integer> solve() {
        int start = -1;
        int end = -1;

        // graph validation
        for(int i = 0; i < numOfNodes; i++) {
            NodeType type = calculateNodeType(i);

            if(type == NodeType.invalid) return null;
            if(type == NodeType.start) {
                // two start points => invalid graph
                if(start != -1) return null;
                start = i;
            }
            if(type == NodeType.end) {
                // multiple end points
                if(end != -1) return null;
                end = i;
            }
        }

        // one is set, but not the other => invalid graph
        if(start == -1 && end != -1 || start != -1 && end == -1) {
            return null;
        }

        // no odd degree nodes => euler circuit => just pick any start/end node
        if(start == -1 && end == -1) {
            start = 0;
            end = 0;
        }


        DFS dfs = new DFS(numOfNodes, edges, start, end);
        List<Integer> startToEnd = dfs.getPath();

        // no path from start to end
        if(startToEnd == null) {
            return null;
        }

        // now just take all other possible paths, this should be possible since all paths left are cycles
        int edgesLeft = numOfEdges - startToEnd.size();
        List<Integer> solution = new ArrayList<>();

        while(true) {
//            if() {
//
//            }
break;
        }

        return null;
    }

    enum NodeType {
        start,  // start node will always have 1 more "out edge" than "in edge"
        end,    // end node will always have 1 more "in edge" than "out edge"
        normal, // normal will always have equal in and out edges
        invalid // rest of the cases, if one of these nodes exist, than there is no solution
    }

    // note that there is a special case of this (an euler circuit) in which all nodes have equal
    // in and out edges, this will be handled by the algorithm


    static NodeType calculateNodeType(int i) {
        if(numOfEdgesIn[i] == numOfEdgesOut[i]) {
            return NodeType.normal;
        } else if(numOfEdgesOut[i] + 1 == numOfEdgesIn[i]) {
            return NodeType.start;
        } else if(numOfEdgesIn[i] + 1 == numOfEdgesOut[i]) {
            return NodeType.end;
        } else {
            return NodeType.invalid;
        }
    }
}
