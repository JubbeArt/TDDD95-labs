import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 31/03/19
 */

public class unfairplay {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(true) {
            int numberOfTeams = io.getInt();

            if(numberOfTeams == -1) {
                break;
            }

            int numberOfMatches = io.getInt();
            int[] teamPoints = new int[numberOfTeams];

            for(int i = 0; i < numberOfTeams; i++) {
                teamPoints[i] = io.getInt();
            }





        }
        io.close();

    }
}