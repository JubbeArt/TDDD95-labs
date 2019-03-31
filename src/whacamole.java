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

            solve(moles);
        }

        io.close();

    }

    static void solve(List<Node> moles) {

        int maxDistance = 0;

        for(Node startMole : moles) {
            int distance = calcDistance(startMole, 0);

            maxDistance = Math.max(distance, maxDistance);

        }

        io.println(maxDistance);
    }

    static int calcDistance(Node mole, int distance) {
        distance++;

        for(Node child : mole.neighbors) {
            distance = Math.max(calcDistance(child, distance), distance);
        }

        return distance;
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
}

