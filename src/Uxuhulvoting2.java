/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class Uxuhulvoting2 {
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

            for(int priest = 0; priest < priests; priest++) {
                for(int vote = 0; vote < 8; vote++)  {
                    votes[priest][vote] = VOTES[io.getInt() - 1];
                }
            }
            solve();
        }

        io.close();
    }

    static void solve() {


//        for(int priest = 0; priest < priests; priest++) {
//            for(int stone = 0; stone <= 2; stone++) {
//
//                int currentSimilarity = totalSimilarity(stones);
//
//            }
//        }
    }

    static int totalSimilarity(int stones) {
        int totalSimilarity = 0;

        for(int i = 0; i < priests; i++) {
            totalSimilarity += similarity(votes[i][0], stones);
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
