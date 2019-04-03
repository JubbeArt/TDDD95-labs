import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class help2 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfTests = scanner.nextInt();
        scanner.nextLine();

        for(int t = 0; t < numOfTests; t++) {
            pattern1 = scanner.nextLine().split(" ");
            pattern2 = scanner.nextLine().split(" ");

            // trivially no solution
            if(pattern1.length != pattern2.length) {
                io.println("-");
                continue;
            }

            solve();
        }

        io.close();
    }

    static String[] pattern1;
    static String[] pattern2;

    static void solve() {
        while(true) {
            // repeat till all easy assignments are done
            boolean didSomething = doForcedAssignments();

            if(didSomething) {
                continue;
            }

            // no valid assignments were found, but we might not be done
            boolean foundAssignment = doRandomAssignment();

            // found valid assignment
            if(foundAssignment) {
                continue;
            }

            // no forced assignments or "random" assignments to be made,
            // patterns should match by now
            if(Arrays.equals(pattern1, pattern2)) {
                io.println(String.join(" ", pattern1));
            } else {
                io.println("-");
            }

            break;
        }
    }


    static boolean doForcedAssignments() {
        for(int i = 0; i < pattern1.length; i++) {
            String wordA = pattern1[i];
            String wordB = pattern2[i];

            boolean aIsPlaceholder = isPlaceholder(wordA);
            boolean bIsPlaceholder = isPlaceholder(wordB);

            // one of them is placeholder => forced to do assignment of that placeholder
            if(aIsPlaceholder && !bIsPlaceholder) {
                replaceAllOccurrences(pattern1, wordA, wordB);
                return true;
            } else if(bIsPlaceholder && !aIsPlaceholder) {
                replaceAllOccurrences(pattern2, wordB, wordA);
                return true;
            }
        }

        return false;
    }

    static boolean doRandomAssignment() {
        for(int i = 0; i < pattern1.length; i++) {
            String wordA = pattern1[i];
            String wordB = pattern2[i];

            if(isPlaceholder(wordA) && isPlaceholder(wordB)) {
                replaceAllOccurrences(pattern1, wordA, "zzzz");
                replaceAllOccurrences(pattern2, wordB, "zzzz");
                return true;
            }
        }

        return false;
    }

    static void replaceAllOccurrences(String[] pattern, String value, String replacer) {
        for(int i = 0; i < pattern.length; i++) {
            if(pattern[i].equals(value)) {
                pattern[i] = replacer;
            }
        }
    }

    static boolean isPlaceholder(String word) {
        return word.startsWith("<") && word.endsWith(">");
    }
}
