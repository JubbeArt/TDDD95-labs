/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 04/02/19
 */



public class Spiderman {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int N = io.getInt();
        int maxHeight = 1001;

        for(int n = 0; n < N; n++) {
            int M = io.getInt();
            heights = new int[M];
            bestMaxHeight = 2000;

            for(int i = 0; i < M; i++) {
                heights[i] = io.getInt();
            }

            solve(0, 0, 0, "");
            io.println(solution);
        }

        io.close();
    }

    static int[] heights;
    static String solution;
    static int bestMaxHeight;
    static int[][] cache;

    public static void solve(int maxHeight, int currentHeight, int index, String path) {
        if(index >= heights.length) {
            if(bestMaxHeight == 2000 && currentHeight != 0) {
                solution = "IMPOSSIBLE";
            } else if(maxHeight < bestMaxHeight) {
                solution = path;
                bestMaxHeight = maxHeight;
            }

            return;
        }

        int heightUp = currentHeight + heights[index];
        int heightDown = currentHeight - heights[index];

        solve(Math.max(maxHeight, heightUp), heightUp, index + 1, path + "U");

        // we are forced to up
        if(heightDown < 0) {
            return;
        }

        solve(maxHeight, heightDown, index + 1,  path + "D");
        return;
    }
}
