/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class Uxuhulvoting {
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
    static int[][] votes;
    static int priests;

    public static void main(String[] args) {
        int n = io.getInt();

        for(int round = 0; round < n; round++) {

            priests = io.getInt();
            votes = new int[priests][8];
            bestSimilarity = 0;
            cache = new int[priests][YYY + 1];

            for(int priest = 0; priest < priests; priest++) {
                for(int vote = 0; vote < 8; vote++)  {
                    votes[priest][vote] = VOTES[io.getInt() - 1];
                }
            }
            int stones = 0b000;
            solve(stones, 0);
            printStone(solution);
        }

        io.close();
    }

    static int bestSimilarity;
    static int solution;
    static int[][] cache;

    static int solve(int stones, int priestIndex) {
        if(priestIndex < priests && cache[priestIndex][stones] != 0) {
            return cache[priestIndex][stones];
        }

        int currentSimilarity = totalSimilarity(stones);

        if(priestIndex >= priests) {
            if(currentSimilarity > bestSimilarity) {
                solution = stones;
                bestSimilarity = currentSimilarity;
            }

            return currentSimilarity;
        }

        // Test voting for each stone and see which one gets best results
        int similarity0 = solve(flipStone(stones, 0), priestIndex + 1);
        int similarity1 = solve(flipStone(stones, 1), priestIndex + 1);
        int similarity2 = solve(flipStone(stones, 2), priestIndex + 1);

        cache[priestIndex][stones] = Math.max(similarity0, Math.max(similarity1, similarity2));
        return cache[priestIndex][stones];
    }

    static int totalSimilarity(int stones) {
        int totalSimilarity = 0;

        for(int priest = 0; priest < priests; priest++) {
            for(int vote = 0; vote < 1; vote++) {
                // make lower votes more valued
                int weight = (8-vote)  * 100;

                totalSimilarity += weight * similarity(votes[priest][vote], stones);

            }
        }

        return totalSimilarity;
    }

    static void printStone(int binary) {
        io.print((binary & YNN) == YNN ? "Y": "N");
        io.print((binary & NYN) == NYN ? "Y": "N");
        io.print((binary & NNY) == NNY ? "Y": "N");
        io.println();
    }

    static int flipStone(int stone, int index) {
        assert index >= 0 && index <= 2;

        if(index == 0) {
            return stone ^ NNY;
        } else if(index == 1) {
            return stone ^ NYN;
        } else {
            return stone ^ YNN;
        }
    }

    // return a score between 0 to 3,
    // 0 means no stone are the same
    // 3 means all stones are the same
    static int similarity(int stone1, int stone2) {
        int score = 0;

        if((stone1 & NNY) == (stone2 & NNY)) score++;
        if((stone1 & NYN) == (stone2 & NYN)) score++;
        if((stone1 & YNN) == (stone2 & YNN)) score++;

        return score;
    }
}
