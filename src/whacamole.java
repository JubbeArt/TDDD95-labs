import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 31/03/19
 */

public class whacamole {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while (true) {
            int boardSize = io.getInt();
            int maxDistance = io.getInt();
            int numberOfMoles = io.getInt();

            if(boardSize == 0 && maxDistance == 0 && numberOfMoles == 0) {
                break;
            }

            List<Node> moles = new ArrayList<>(numberOfMoles);


            for (int i = 0; i < numberOfMoles; i++) {
                moles.add(i, new Node(io.getInt(), io.getInt(), io.getInt()));
            }

            for(int i = 0; i < numberOfMoles; i++) {
                for(int j = 0; j < numberOfMoles; j++) {
                    if(i == j) continue;

                    Node n1 = moles.get(i);
                    Node n2 = moles.get(j);
                    if(n1.time < n2.time && n1.distance(n2) <= maxDistance) {
                        n1.neighbors.add(n2);
                    }
                }
            }

            solve()
        }

        io.close();

    }

    static void solve() {

        
    }

    static class Node {
        int x;
        int y;
        int time;
        List<Node> neighbors;

        Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
            neighbors = new ArrayList<>();
        }

        double distance(Node other) {
            return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
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

