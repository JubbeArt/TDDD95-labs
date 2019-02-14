import java.util.HashSet;
import java.util.Set;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Walrusweights {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int plates = io.getInt();
            weights = new int[plates];

            for(int i = 0; i < plates; i++) {
                int weight = io.getInt();
                weights[i] = weight;
            }
        }

        solve();
        io.println(bestWeight);
        io.close();
    }

    static int[] weights;
    static int bestWeight = 0;
    static int bestWeightDiff = 1000;

    static void solve() {
        Set<Integer> possibleWeights = new HashSet<>();
        possibleWeights.add(0);

        for(int weight : weights) {
            Set<Integer> newWeights = new HashSet<>();

            for(int possibleWeight : possibleWeights) {
                int newWeight = weight + possibleWeight;
                int diff = Math.abs(1000 - newWeight);

                if(diff < bestWeightDiff) {
                    bestWeight = newWeight;
                    bestWeightDiff = diff;
                } else if(diff == bestWeightDiff && newWeight > bestWeight) {
                    bestWeight = newWeight;
                }

                if(newWeight >= 1000 && diff > bestWeightDiff) continue;
                newWeights.add(newWeight);
            }

            possibleWeights.addAll(newWeights);
        }

    }
}
