import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 26/01/19
 */


public class Fenwick {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int N = io.getInt();
        int Q = io.getInt();
        FenwickTree fenwickTree = new FenwickTree(N);

        for(int i = 0; i < Q; i++) {
            char operation = io.getWord().charAt(0);
            int index = io.getInt();

            if(operation == '+') {
                int delta = io.getInt();
                fenwickTree.add(index, delta);
            } else if(operation == '?'){
                io.println(fenwickTree.sum(index));
            }
        }

        io.close();
    }


}
