import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class DFS {
    int numOfNodes;
    List<List<Integer>> edges;
    int[] parents;
    int start;
    int end;

    DFS(int numOfNodes, List<List<Integer>> edges, int start, int end) {
        this.numOfNodes = numOfNodes;
        this.start = start;
        this.end = end;
        this.edges = edges;
        this.parents = new int[numOfNodes];
        Arrays.fill(parents, -1);
        this.DFS();
    }

    public void DFS() {
        if(start == end) {
            return;
        }

        Stack<Integer> s = new Stack<>();
        s.add(start);
        boolean[] visited = new boolean[numOfNodes];

        while(!s.isEmpty()) {
            int current = s.pop();

            if(current == end) {
                break;
            }

            if(visited[current])
                continue;

            visited[current] = true;

            for(int neighbor:  edges.get(current)) {
                parents[current] = neighbor;
                s.add(neighbor);
            }
        }
    }

    public List<Integer> getPath() {
        List<Integer> path = new ArrayList<>();
        int current = end;
        path.add(current);

        while(true) {
            int parent = parents[current];

            // no path found
            if(parent == -1 && current != start) {
                return null;
            }

            if(parent == -1) {
                return path;
            }

            current = parent;
            path.add(current);
        }
    }
}
