import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 31/03/19
 */

public class ljutnja {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        long candies = io.getLong(); // m
        long children = io.getLong(); // n
        long[] childNeeds = new long[(int)children];

        for(int i = 0; i < children; i++) {
            childNeeds[i] = io.getLong();
        }

        Arrays.sort(childNeeds);

        solve(candies, childNeeds);

       io.close();

    }

    static void solve(long candies, long[] childNeeds) {
        long totalNeed = 0;
        long totalAnger = 0;

        for(long need : childNeeds) {
            totalNeed += need;
        }

        long candiesLeft = totalNeed - candies;

        for(int i = 0; i < childNeeds.length; i++) {
            long kidsLeft = childNeeds.length - i;
            long candiesTaken = Math.min(childNeeds[i], (long) Math.floor((double) candiesLeft / kidsLeft));
            totalAnger += candiesTaken * candiesTaken;
            candiesLeft -= candiesTaken;
        }

        io.println(totalAnger);
    }

}
