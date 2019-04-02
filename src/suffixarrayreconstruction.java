import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 02/04/19
 */

public class suffixarrayreconstruction {
    static Kattio io = new Kattio(System.in, System.out);

    static SuffixPart[] suffixParts;
    static char[] suffix;
    static final char EMPTY = ' ';
    static String IMPOSSIBLE = "IMPOSSIBLE";


    public static void main(String[] args) {
        int numOfTests = io.getInt();

        for(int t = 0; t < numOfTests; t++) {
            int suffixLength = io.getInt();
            int numberOfSuffixes = io.getInt();
            suffix = new char[suffixLength];
            Arrays.fill(suffix, EMPTY);

            suffixParts = new SuffixPart[numberOfSuffixes];

            for(int i = 0; i < numberOfSuffixes; i++) {
                suffixParts[i] = new SuffixPart(io.getInt() - 1, io.getWord().toCharArray());
            }

            solve();
        }

        io.close();
    }


    static void solve() {
        List<SuffixPart> suffixesWithStar = new ArrayList<>();

        // make all assignments that we are forced to do:
        // 1. all suffixes without a star can be completely assigned
        // 2. suffixes with a star can have everything left of the star assigned
        for(SuffixPart suffixPart : suffixParts) {
            for(int i = 0; i < suffixPart.suffix.length; i++) {
                int suffixIndex = suffixPart.index + i;

                // out of bounds
                if(suffixIndex >= suffix.length) {
                    break;
                }

                // will deal with star assignments later
                if(suffixPart.suffix[i] == '*') {
                    suffixesWithStar.add(suffixPart);
                    break;
                }

                // can assign
                if(suffix[suffixIndex] == EMPTY) {
                    suffix[suffixIndex] = suffixPart.suffix[i];
                    continue;
                }

                // already taken but not the same as current suffix
                if(suffix[suffixIndex] != suffixPart.suffix[i]) {
                    io.println(IMPOSSIBLE);
                    return;
                }
            }
        }


        // make sure all star suffixes are assignable
        for(SuffixPart suffixPart : suffixesWithStar) {
            for(int i = 0; i < suffixPart.suffix.length; i++) {
                int suffixIndex = suffix.length - 1 - i;
                int partIndex = suffixPart.suffix.length - 1 - i;

                // out of bounds
                if(suffixIndex < 0) {
                    break;
                }

                // found the star, meaning we're done with this suffix
                if(suffixPart.suffix[partIndex] == '*') {
                    break;
                }

                // can assign one for right side of star
                if(suffix[suffixIndex] == EMPTY) {
                    suffix[suffixIndex] = suffixPart.suffix[partIndex];
                    continue;
                }

                // cannot assign so that right side of star matches
                if(suffix[suffixIndex] != suffixPart.suffix[partIndex]) {
                    io.println(IMPOSSIBLE);
                    return;
                }
            }

        }

        // check if we got valid solution
        for(char c : suffix) {
            if(c == EMPTY) {
                io.println(IMPOSSIBLE);
                return;
            }
        }

        io.println(new String(suffix));
    }


    static class SuffixPart {
        int index;
        char[] suffix;

        SuffixPart(int index, char[] suffix) {
            this.index = index;
            this.suffix = suffix;
        }

        @Override
        public String toString() {
            return String.format("Suffix: \"%s\" at start at: %d\n", new String(suffix), index);
        }
    }
}

