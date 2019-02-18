import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class hidingplaces {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int n = io.getInt();

        for(int i = 0; i < n; i++) {
            visited = new boolean[8][8];
            maxMoves = 0;
            solution = new HashSet<>();
            moves = new HashMap<>();

            String start = io.getWord();
            int col = "abcdefgh".indexOf(start.charAt(0));
            int row = Character.getNumericValue(start.charAt(1)) - 1;

            solve(col, row);
//            io.println(solution);
//            io.println(maxMoves);

        }
        io.close();
    }

    static boolean[][] visited;
    static int maxMoves;
    static Set<BoardPlace> solution;
    static Map<BoardPlace, List<BoardPlace>> moves;

    static void solve(int startCol, int startRow) {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 8; col++) {
                BoardPlace current = new BoardPlace(col, row);
                moves.put(current, new ArrayList<>());

                // up
                addMove(current, col + 1, row + 2);
                addMove(current, col - 1, row + 2);

                // down
                addMove(current, col + 1, row - 2);
                addMove(current, col - 1, row - 2);

                // left
                addMove(current, col - 2, row + 1);
                addMove(current, col - 2, row - 1);

                // right
                addMove(current, col + 2, row + 1);
                addMove(current, col + 2, row - 1);

            }
        }

//
        Dijkstra dijkstra = new Dijkstra(moves, new BoardPlace(startCol, startRow));

        List<BoardPlace> solution = new ArrayList<>();

        for(int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                BoardPlace current = new BoardPlace(col, row);

                if(dijkstra.getDistance(current) == dijkstra.longestOptimalDistance) {
                    solution.add(current);
                }
            }
        }

        io.print(dijkstra.longestOptimalDistance + " ");
        solution.sort((p1, p2) -> {
            if(p1.row < p2.row) return 1;
            if(p1.row > p2.row) return -1;
            if(p1.col < p2.col) return -1;
            if(p1.col > p2.col) return 1;
            return 0;
        });

        for(BoardPlace place : solution) {
            io.print(place + " ");
        }

        io.println();
    }

    static void addMove(BoardPlace fromPlace, int toCol, int toRow) {
        if(validPlace(toCol, toRow)) {
            moves.get(fromPlace).add(new BoardPlace(toCol, toRow));
        }
    }

    static boolean validPlace(int col, int row) {
        return col >= 0 && col <= 7 && row >= 0 && row <= 7;

    }

    // "2D" dijkstra for boards
    public static class Dijkstra {
        public Map<BoardPlace, List<BoardPlace>> edges;
        public int[][] distances;
        public int longestOptimalDistance = 0;

        Dijkstra(Map<BoardPlace, List<BoardPlace>> edges, BoardPlace startIndex) {
            this.edges = edges;
            this.distances = new int[8][8];
            dijkstra(startIndex);
        }

        private void dijkstra(BoardPlace start) {
            PriorityQueue<BoardPlace> unvisitedNodes = new PriorityQueue<>(Comparator.comparingInt(this::getDistance));

            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }

            setDistance(start, 0);
            unvisitedNodes.add(start);

            while(!unvisitedNodes.isEmpty()) {
                BoardPlace place = unvisitedNodes.poll();
                List<BoardPlace> neighbors = edges.get(place);

                // Loop through all edgeLookup out of the current node
                for(BoardPlace neighbor : neighbors) {
                    int distance = getDistance(place) + 1;

                    // New shorter distance
                    if(distance < getDistance(neighbor)) {
                        setDistance(neighbor, distance);
                        unvisitedNodes.add(neighbor);

                        if(distance > longestOptimalDistance) {
                            longestOptimalDistance = distance;
                        }
                    }
                }
            }
        }

        public void printDistances() {
            io.println("[");
            for(int[] row: distances) {
                io.println("\t" + Arrays.toString(row));
            }

            io.println("]");
            io.println();
        }

        public int getDistance(BoardPlace place) {
            return distances[place.row][place.col];
        }

        public void setDistance(BoardPlace place, int distance) {
            distances[place.row][place.col] = distance;
        }
    }

    static class BoardPlace {
        int col;
        int row;

        BoardPlace(int col, int row) {
            this.col = col;
            this.row = row;
        }

        static String abc = "abcdefgh";

        @Override
        public String toString() {
            return abc.charAt(col) + "" + (row + 1);
        }

        @Override
        public int hashCode() {
            return col * 10 + row;
        }

        @Override
        public boolean equals(Object obj) {
            return col == ((BoardPlace)obj).col && row == ((BoardPlace)obj).row;
        }
    }

}
