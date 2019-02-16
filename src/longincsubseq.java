import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 06/02/19
 */

public class longincsubseq {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int n = io.getInt();
            sequence = new int[n];
            solution = new int[n];
            asdf = 0;
            int maxValue = 1;
            for(int i = 0; i < n; i++) {
                sequence[i] = io.getInt();
                solution[i] = -1;

                if(sequence[i] > maxValue) {
                    maxValue = sequence[i];
                }
            }

            cache = new int[n][maxValue + 1];

            int length = solve(0, 0, Integer.MIN_VALUE);
            int count = 0;
            String indexes = "";
            io.println();
            io.println(length);

            for(int i = 0; i < n; i++) {
                if(count >= length) {
                    break;
                }

                // Solution is marked with the longest sequence length
                if(solution[i] == length) {
                    indexes += i + " ";
                    count++;
                }
            }
            io.println(indexes);
            io.println(Arrays.toString(solution));
        }

        io.close();
    }

    static int[] sequence;
    static int[] solution;
    static int[][] cache;

    static int asdf= 0;

    // TODO: FIX CACHING FOR NEGATIVE VALUES
    static int solve(int length, int i, int value) {
        if(i >= sequence.length) {
            return length;
        }
        io.printf("i: %d, value: %d, length: %d\n", i, value, length);
        for(var a:cache) {
            io.println(Arrays.toString(a));
        }
        io.println();


        if(value >= 0 && cache[i][value] != 0) {
            io.println("cached: " + Math.max(cache[i][value], length));
            cache[i][value] = Math.max(cache[i][value], length);
            return cache[i][value];
        }

//        asdf++;

        int excludeLength = solve(length, i + 1, value);

        // we cannot include next item, so skip it
        if(sequence[i] <= value) {
            solution[i] = Math.max(solution[i], excludeLength);
            if(value >= 0) {
                cache[i][value] = Math.max(cache[i][value], excludeLength);
            }
            return excludeLength;
        }

        int includeLength = solve(length + 1, i + 1, sequence[i]);
        int maxLength = Math.max(includeLength, excludeLength);

        if(includeLength > excludeLength) {
            solution[i] = Math.max(solution[i], maxLength);
        }

        if(value >= 0) {
            cache[i][value] = Math.max(cache[i][value], maxLength);
        }
        return maxLength;
    }
}
