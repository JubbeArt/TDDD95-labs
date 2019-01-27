import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 25/01/19
 */

public class Longincsubseq {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int n = io.getInt();
            int[] sequence = new int[n];

            for(int i = 0; i < n; i++) {
                sequence[i] = io.getInt();
            }

            longestIncreasingSequence(sequence);
        }

        io.close();
    }

    static void longestIncreasingSequence(int[] sequence) {
        int longestSequence = 0;
        List<Integer> solution = new ArrayList<>();

        for(int start = 0; start < sequence.length; start++) {
            List<Integer> tmpSolution = new ArrayList<>();
            int currentItem = sequence[start];
            tmpSolution.add(start);

            for(int i = start + 1; i < sequence.length; i++) {
                // Valid next item
                if(currentItem < sequence[i]) {
                    tmpSolution.add(i);
                    currentItem = sequence[i];
                }
            }

            if(tmpSolution.size() > longestSequence) {
                solution = tmpSolution;
                longestSequence = tmpSolution.size();
            }
        }

        io.println(solution.size());

        for(int i : solution) {
            io.print(i + " ");
        }

        io.println();

    }
}
