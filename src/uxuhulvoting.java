/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class uxuhulvoting {
    static Kattio io = new Kattio(System.in, System.out);

    static final int NNN = 0b000;
    static final int NNY = 0b001;
    static final int NYN = 0b010;
    static final int NYY = 0b011;
    static final int YNN = 0b100;
    static final int YNY = 0b101;
    static final int YYN = 0b110;
    static final int YYY = 0b111;

    static final int[] VOTES = new int[]{NNN, NNY, NYN, NYY, YNN, YNY, YYN, YYY};
    static int[][] preferences;
    static int priests;

    public static void main(String[] args) {
        int n = io.getInt();

        for(int round = 0; round < n; round++) {

            priests = io.getInt();
            preferences = new int[priests][8];
            cache = new int[priests + 1][YYY + 1];

            for(int priest = 0; priest < priests; priest++) {
                for(int vote = 0; vote < 8; vote++)  {
                    preferences[priest][vote] = VOTES[io.getInt() - 1];
                }
            }

            for(int i = 0; i < cache.length; i++) {
                for(int j = 0; j < cache[i].length; j++) {
                    cache[i][j] = -1;
                }
            }

            int res = solve(0b000, 0);
            printStone(res);
        }

        io.close();
    }

    static int[][] cache;

    static int solve(int stones, int priestIndex) {
        if(priestIndex == priests) {
            return stones;
        }

        if(cache[priestIndex][stones] != -1) {
            return cache[priestIndex][stones];
        }

        int stone1 = solve(flipStone(stones, 0), priestIndex + 1);
        int stone2 = solve(flipStone(stones, 1), priestIndex + 1);
        int stone3 = solve(flipStone(stones, 2), priestIndex + 1);

        int best = stone1;

        if(preferences[priestIndex][stone2] < preferences[priestIndex][best]) {
            best = stone2;
        }

        if(preferences[priestIndex][stone3] < preferences[priestIndex][best]) {
            best = stone3;
        }

        cache[priestIndex][stones] = best;
        return best;
    }

    static void printStone(int binary) {
        io.print((binary & YNN) == YNN ? "Y": "N");
        io.print((binary & NYN) == NYN ? "Y": "N");
        io.print((binary & NNY) == NNY ? "Y": "N");
        io.println();
    }

    static int flipStone(int stone, int index) {
        return stone ^ (1 << index);
    }
}
