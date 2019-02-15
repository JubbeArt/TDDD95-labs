import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */


public class Stringmatching {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        io.println(Arrays.toString(createPrefixFunction("ababaca")));

//        while(scanner.hasNext()) {
//            String pattern = scanner.nextLine();
//            String word = scanner.nextLine();
//
//            solve(pattern, word);
//        }

        io.close();
    }

    static void solve(String pattern, String word) {
        int[] prefixMapping = createPrefixFunction(pattern);
        io.println();
    }

    static int[] createPrefixFunction(String pattern) {
        int[] Π = new int[pattern.length()];
        Π[0] = 0;
        int k = 0;

        for(int q = 1; q < pattern.length(); q++) {
            while(k > 0 && pattern.charAt(k + 1) != pattern.charAt(q)) {
                k = Π[k];
            }

            if(pattern.charAt(k + 1) == pattern.charAt(q)) {
                k++;
            }

            Π[q] = k;


//

//            do {
//                k = Π[k];
//
//                if(pattern.charAt(k + 1) == pattern.charAt(q)) {
//                    k++;
//                }
//
//                Π[q] = k;
//            } while(k > 0 && pattern.charAt(k + 1) != pattern.charAt(q));
        }

        return Π;
    }


}