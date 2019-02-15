import java.util.BitSet;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class Firefly {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfObstacles = io.getInt();
        int height = io.getInt();

//        rows = new BitSet[height];
        rows = new int[height];

        boolean isFloor = true;

        int bestLength = height + 1;
        int numberOfRows = 0;

        for(int o = 0; o < numOfObstacles; o++) {

            int obstacleHeight = io.getInt();

            if(isFloor) {
                // b to height
                for(int i = height - 1; i >= (height - obstacleHeight); i--) {
                    rows[i]++;

                    if(rows[i] < bestLength) {
                        bestLength = rows[i];
                        numberOfRows = 0;
                    }

                    if(rows[i] == bestLength) {
                        numberOfRows++;
                    }

                }
            } else {
                // a to height
                for(int i = 0; i < obstacleHeight; i++) {
                    rows[i]++;

                    if(rows[i] < bestLength) {
                        bestLength = rows[i];
                        numberOfRows = 0;
                    }

                    if(rows[i] == bestLength) {
                        numberOfRows++;
                    }

                }
            }

            isFloor = !isFloor;
        }


//        for(int i = 0; i < rows.length; i++) {
//            int obstablesInWay = rows[i];
//
//            if(obstablesInWay < best) {
//                best = obstablesInWay;
//                numberOfSolutions = 0;
//            }
//
//            if(obstablesInWay == best) {
//                numberOfSolutions++;
//            }
//        }


        io.println(bestLength + " " + numberOfRows);
//        print();
        io.close();
    }

    static int[] rows;

    static void print() {
//        for(BitSet row : rows) {
//            for(int i = 0; i < row.length(); i++) {
//                io.print(row.get(i) ? "X " : "  ");
//            }
//            io.println();
//        }
    }
}
