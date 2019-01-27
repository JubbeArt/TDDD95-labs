import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 24/01/19
 */

public class Knapsack {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            double C = io.getDouble();
            int n = io.getInt();
            List<Item> items = new ArrayList<>();

            for(int i = 0; i < n; i++) {
                int value = io.getInt();
                int weight = io.getInt();

                items.add(new Item(value, weight, i));
            }

            knapsack((int) Math.floor(C), items);
        }

        io.close();
    }

    static void knapsack(int capacity, List<Item> items) {
        Map<String, Integer> values = new HashMap<>();
        //items.sort(Comparator.comparingInt(item -> item.value));
        Map<String, String> solutions = new HashMap<>();

        // Generate a computed map of all possible values
        // Use old values to generate new ones
        for(int index = 0; index < items.size(); index++) {
            for(int weight = 0; weight <= capacity; weight++) {
                String currentKey = "w" + weight + "i" + index;
                int lastValue = index == 0 ? 0 : values.get("w" + weight + "i" + (index - 1));
                String lastSolution = index == 0 ? "" : solutions.get("w" + weight + "i" + (index - 1));
                Item item = items.get(index);

                // Too heavy to include
                if (item.weight > weight) {
                    // Use last value
                    values.put(currentKey, lastValue);
                    solutions.put(currentKey, lastSolution);
                }
                // Decide if you should include the item or not by looking at the max value of doing both
                else {
                    int includedValue = index == 0 ? 0 : values.get("w" + (weight - item.weight) + "i" + (index - 1));
                    String includedSolution = index == 0 ? "" : solutions.get("w" + (weight - item.weight) + "i" + (index - 1));

                    int included = includedValue + item.value;
                    int excluded = lastValue;

                    if(included >= excluded) {
                        values.put(currentKey, included);
                        solutions.put(currentKey,   includedSolution + item.index + " ");
                    } else {
                        values.put(currentKey, excluded);
                        solutions.put(currentKey, lastSolution);
                    }

                }
            }
        }

        String solution =  solutions.get("w" + capacity + "i" + (items.size() - 1));
        io.println(solution.split(" ").length);
        io.println(solution);
    }


    static class Item {
        int value;
        int weight;
        int index;

        Item(int value, int weight, int index) {
            this.value = value;
            this.weight = weight;
            this.index = index;
        }

        public String toString() {
            return String.format("(value: %d, weight: %d)", this.value, this.weight);
        }
    }
}
