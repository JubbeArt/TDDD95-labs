/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 30/03/19
 */

public class gokigen {
    static Kattio io = new Kattio(System.in, System.out);

    static final char EMPTY = '\u0000';
    static final char OUT_OF_BOUNDS = 'x';

    public static void main(String[] args) {
        int boardSize = io.getInt();

        circles = new char[boardSize+1][boardSize+1];
        board = new char[boardSize][boardSize];

        for(int i = 0; i <= boardSize; i++) {
            circles[i] = io.getWord().toCharArray();
        }

        solve();
        io.close();
    }

    static char[][] circles;
    static char[][] board;

    static void solve() {
        while(true) {
            boolean didSomething = false;

            // check all circles
            for (int i = 0; i < circles.length; i++) {
                for (int j = 0; j < circles[i].length; j++) {
                    if (circles[i][j] != '.') {
                        boolean changed = checkCircle(i, j);

                        if(changed) didSomething = true;
                    }
                }
            }

            // check all empty spots on board
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    if(board[i][j] == EMPTY) {

                    }
                }
            }

            if(!didSomething) {
                break;
            }
        }

        printBoard();
        printCircles();
    }

    static boolean checkCircle(int x, int y) {
        io.println(circles[x][y]);
        int circle = Character.getNumericValue(circles[x][y]);
        int freeSpots = 0;
        int takenSpots = 0;





        return false;
    }



    static void printCircles() {
        io.println("Circles");

        for(int i = 0; i < circles.length; i++) {
            for(int a = 0; a < circles[i].length; a++) {
                io.print(circles[i][a]);
            }

            io.println();
        }

        io.println();
    }

    static void printBoard() {
        io.println("Board");

        for(int i = 0; i < board.length; i++) {
            for(int a = 0; a < board[i].length; a++) {
                io.print(board[i][a]);
            }

            io.println();
        }

        io.println();
    }


}
