import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 16/02/19
 */

public class Aspenavenue {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int N = io.getInt();
            roadLength = io.getInt();
            roadWidth = io.getInt();

            trees = new int[N];
            solution = new double[N+1][N+1];

            for(int i = 0; i < N; i++) {
                trees[i] = io.getInt();
            }

            Arrays.sort(trees);
            solve();
        }

        io.close();
    }

    static int[] trees;
    static int roadWidth;
    static int roadLength;

    static double[][] solution;

    public static void solve() {
        io.println(Arrays.toString(trees));

    }
}
