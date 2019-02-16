/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 12/02/19
 */

public class gold {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int W = io.getInt();
        int H = io.getInt();

        board = new char[H][W];
        safe = new boolean[H][W];
        visited = new boolean[H][W];

        int playerX = -1;
        int playerY = -1;

        for(int y = 0; y < H; y++) {
            String row = io.getWord();

            for(int x = 0; x < W; x++) {
                board[y][x] = row.charAt(x);

                if(row.charAt(x) == 'P') {
                    playerX = x;
                    playerY = y;
                }
            }
        }

        solve(playerX, playerY);
        io.println(goldsFound);
        io.close();
    }


    static char[][] board;
    static boolean[][] safe;
    static boolean[][] visited;
    static int goldsFound = 0;

    static void solve(int playerX, int playerY) {
        breathFirst(playerX, playerY);
    }

    static boolean sensesBreeze(int x, int y) {
        return
                board[y][x+1] == 'T' ||
                board[y][x-1] == 'T' ||
                board[y+1][x] == 'T' ||
                board[y-1][x] == 'T';
    }

    static void breathFirst(int x, int y) {
        // out of bounds
        if(x <= 0 || y <= 0 || y >= board.length - 1 || x >= board[y].length - 1) return;
        // has already been here
        if(visited[y][x]) return;
        // hit a wall
        if(board[y][x] == '#') return;


        if(board[y][x] == 'G') {
            goldsFound++;
            board[y][x] = '.';
        }

        visited[y][x] = true;

        boolean isDangerous = sensesBreeze(x, y);

        // move if we do not sense a breeze OR if we know its safe since before
        if(!isDangerous || safe[y][x + 1]) breathFirst(x + 1, y);
        if(!isDangerous || safe[y][x - 1]) breathFirst(x - 1, y);
        if(!isDangerous || safe[y + 1][x]) breathFirst(x, y + 1);
        if(!isDangerous || safe[y - 1][x]) breathFirst(x, y - 1);
    }
}