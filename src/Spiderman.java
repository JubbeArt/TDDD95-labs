/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 04/02/19
 */



public class Spiderman {
    static Kattio io = new Kattio(System.in, System.out);

    static final int UP = 1;
    static final int DOWN = -1;

    public static void main(String[] args) {
        int N = io.getInt();
        int maxHeight = 1001;

        for(int n = 0; n < N; n++) {
            int M = io.getInt();
            heights = new int[M];

            for(int i = 0; i < M; i++) {
                heights[i] = io.getInt();
            }


            cache = new int[M][maxHeight + 1];
            solution = new int[M+1][maxHeight + 1];

            for(int i = 0; i < M; i++) {
                for(int h = 0; h <= maxHeight; h++) {
                    cache[i][h] = -1;
                }
            }

            int sum = 0;

            for(int i = 0; i < heights.length; i++) {
                sum += heights[i];
            }

            if(sum % 2 != 0) {
                io.println("IMPOSSIBLE");
                continue;
            }

            solve(0, 0, 0);

            int height = 0;
            String optimalSolution = "";
            for(int i = 0; i < M; i++ ) {
                optimalSolution += solution[i][height] == UP ? "U" : "D";

                if(solution[i][height] == UP) {
                    height += heights[i];
                } else {
                    height -= heights[i];
                }
            }

            if(height == 0) {
                io.println(optimalSolution);
            } else {
                io.println("IMPOSSIBLE");
            }

        }

        io.close();
    }

    static int[] heights;
    static int[][] solution;
    static int[][] cache;

    public static int solve(int maxHeight, int currentHeight, int index) {
        if(index >= heights.length) {
            return maxHeight;
        }

        if(cache[index][currentHeight] != -1) {
            return cache[index][currentHeight];
        }

        int heightUp = currentHeight + heights[index];
        int heightDown = currentHeight - heights[index];
        int nextIndex = index + 1;

        int maxHeightUp = solve(Math.max(maxHeight, heightUp), heightUp, nextIndex);

        // we are forced to up
        if(heightDown < 0) {
            solution[index][currentHeight] = UP;
            cache[index][currentHeight] = maxHeightUp;
            return maxHeightUp;
        }

        int maxHeightDown = solve(maxHeight, heightDown, nextIndex);

        int shortestMaxHeight = Math.min(maxHeightDown, maxHeightUp);
        if(cache[index][currentHeight] != -1 && maxHeight < cache[index][currentHeight]) {
            cache[index][currentHeight] = shortestMaxHeight;
        }

        solution[index][currentHeight] = maxHeightDown <= maxHeightUp ? DOWN : UP;

        return shortestMaxHeight;
    }
}
