import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 10/03/19
 */


public class stringmatching {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            String pattern = scanner.nextLine();
            String text = scanner.nextLine();

            solve(pattern, text);
        }

        io.close();
    }

    static void solve(String pattern, String text) {
        ArrayList<Integer> solution = KMPMatcher(pattern, text);

        for(int match : solution) {
            io.print(match + " ");
        }
        io.println();
    }

    static ArrayList<Integer> KMPMatcher(String pattern, String text) {
        ArrayList<Integer> solution = new ArrayList<>();
        int n = text.length();
        int m = pattern.length();
        int[] pi =  calculatePrefixFunction(pattern);
        int q = 0;

        for(int i = 0; i < n; i++) {
            while(q >= 0 && pattern.charAt(q) != text.charAt(i)) {
                if(q > 0) {
                    q = pi[q - 1];
                } else {
                    q--;
                }
            }

            q++;

            // match found
            if(q == m) {
                solution.add(i - m + 1);
                q = pi[m - 1];
            }
        }


        return solution;
    }

    static int[] calculatePrefixFunction(String pattern) {
        int m = pattern.length();
        int[] pi = new int[m];
        int k = 0;

        for(int q = 1; q < m; q++) {
            // current char do not match, go back till it does
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q)) {
                k = pi[k - 1];
            }

            if(pattern.charAt(k) == pattern.charAt(q)) {
                k++;
            }
            pi[q] = k;
        }

        return pi;
    }
}