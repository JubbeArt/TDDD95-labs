/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 31/03/19
 */

public class spiderman {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numberOfTests = io.getInt();

        for(int test = 0; test < numberOfTests; test++) {
            int numberOfHeights = io.getInt();
            heights = new int[numberOfHeights];
            move = new String[40][1001];
            cache = new int[40][1001];

            for(int i = 0; i < numberOfHeights; i++) {
                heights[i] = io.getInt();
            }

            for(int i = 0; i < 40; i++) {
                for(int h = 0; h < 1001; h++) {
                    move[i][h] = "";
                    cache[i][h] = -1;
                }
            }

            int dist = getDistance(0, 0);

            if(dist == Integer.MAX_VALUE) {
                io.println("IMPOSSIBLE");
            } else {
                dist = 0;
                String path = "";

                for(int i = 0; i < heights.length; i++) {
                    path += move[i][dist];

                    if(move[i][dist].equals("U")) {
                        dist += heights[i];
                    } else {
                        dist -= heights[i];
                    }
                }

                io.println(path);

            }
        }

        io.close();
    }

    static int[] heights;
    static String solution;

    static String[][] move;
    static int[][] cache;

    public static int getDistance(int currentHeight, int index) {
        // underground
        if(currentHeight < 0) {
            return Integer.MAX_VALUE;
        } else if(index == heights.length - 1 && heights[index] != currentHeight) {
            return Integer.MAX_VALUE;
        } else if(index == heights.length -1) {
            move[index][currentHeight] = "D";
            cache[index][currentHeight] = 0;
            return 0;
        } else if (cache[index][currentHeight] != -1) {
            return cache[index][currentHeight];
        }


        int heightDown = getDistance(currentHeight - heights[index], index + 1);
        int heightUp = getDistance(currentHeight + heights[index], index + 1);

        if(heightDown < heightUp) {
            move[index][currentHeight] = "D";
            cache[index][currentHeight] = Math.max(heightDown, currentHeight);

        } else {
            move[index][currentHeight] = "U";
            cache[index][currentHeight] = Math.max(heightUp, currentHeight);
        }

        return cache[index][currentHeight];
    }
}
