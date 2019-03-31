import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 01/04/19
 */

public class chopwood {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numberOfNodes = io.getInt();

        int[] nodes = new int[numberOfNodes];

        for(int i = 0; i < numberOfNodes; i++) {
            nodes[i] = io.getInt() - 1;
        }

        solve(nodes);

        io.close();
    }

    static public void solve(int[] nodes) {
        if(nodes[nodes.length - 1] != nodes.length) {
            io.println("Error");
            return;
        }

        int[] neighbors = new int[nodes.length+1];

        for(int i = 0; i < nodes.length; i++) {
            neighbors[nodes[i]]++;
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for(int i = 0; i < nodes.length; i++) {
            if(neighbors[i] == 0) {
                queue.add(i);
            }
        }

        if(queue.isEmpty()) {
            io.println("Error");
            return;
        }

        for(int i = 0; i < nodes.length; i++) {
            if(queue.isEmpty()) {
                io.println("Error");
                return;
            }

            int current = queue.poll();
            io.println(current + 1);
            neighbors[nodes[i]]--;

            if(neighbors[nodes[i]] == 0) {
                queue.add(nodes[i]);
            }
        }

    }
}

