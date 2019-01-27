import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 26/01/19
 */


public class Fenwick {

    static Kattio io = new Kattio(System.in, System.out);

    static long[] fenwickTree;
    static int N;

    public static void main(String[] args) {
        N = io.getInt();
        int Q = io.getInt();
        fenwickTree = new long[N + 1];

        for(int i = 0; i < Q; i++) {
            char operation = io.getWord().charAt(0);
            int index = io.getInt();

            if(operation == '+') {
                int delta = io.getInt();
                add(index, delta);
            } else if(operation == '?'){
                io.println(sum(index));
            }
        }

        io.close();
    }


    static void add(int index, long delta) {
        index++;

        while(index <= N) {
            fenwickTree[index] += delta;
            // Go to next power of two
            // the "readable way" of doing this was apparently too slow for Kattis
            index += (index & -index);
        }

//        io.println(Arrays.toString(fenwickTree));

    }

    static long sum(int end) {
        long sum = 0;
        int index = end;

         while(index > 0) {
             sum += fenwickTree[index];
             // Go to previous power of two
             // the "readable way" of doing this was apparently too slow for Kattis
             index -= (index & -index);
        }

        return sum;
    }
}
