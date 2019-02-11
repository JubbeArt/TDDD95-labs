/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 24/01/19
 */

public class Knapsack {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            double C = io.getDouble();
            capacity = (int) Math.floor(C);

            int n = io.getInt();
            items = new Item[n];

            for(int i = 0; i < n; i++) {
                int value = io.getInt();
                int weight = io.getInt();
                items[i] = new Item(value, weight, i);
            }

            values = new int[n][capacity + 1];
            knapsack();
        }

        io.close();
    }

    static int capacity;
    static Item[] items;
    static int[][] values;

    static void knapsack() {
        // Generate a computed map of all possible values
        // Use old values to generate new ones
        for(int i = 0; i < items.length; i++) {
            for(int weight = 0; weight <= capacity; weight++) {
                int excludeValue = i == 0 ? 0 : values[i-1][weight];

                // Too heavy, just use the last value
                if(weight < items[i].weight) {
                    values[i][weight] = excludeValue;
                } else {
                    int lastValue = i == 0 ? 0 : values[i-1][weight-items[i].weight];
                    int includeValue = lastValue + items[i].value;
                    // Take max of including the item and not doing so
                    values[i][weight] = Math.max(includeValue, excludeValue);
                }
            }
        }

        int weight = capacity;
        int numberOfItems = 0;
        String solution = "";

        // Backtrack to get full solution
        for(int i = items.length-1; i >= 0; i--) {
            int currentValue = values[i][weight];
            int lastValue = i == 0 ? 0 : values[i-1][weight];

            if(currentValue == 0) {
                break;
            }

            // Included item
            if(values[i][weight] != lastValue) {
                numberOfItems++;
                solution += i + " ";
                weight -= items[i].weight;
            }
        }

        io.println(numberOfItems);
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
