import java.util.BitSet;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Supercomputer {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int memorySize = io.getInt();
        int queries = io.getInt();

        memory = new BitSet(memorySize);

        for(int q = 0; q < queries; q++) {
            char query = io.getWord().charAt(0);

            if(query == 'F') {
                int index = io.getInt() - 1;
                memory.flip(index);
            } else {
                int start = io.getInt() - 1;
                int end = io.getInt();
                io.println(memory.get(start, end).cardinality());
            }
        }

        io.close();
    }

    static BitSet memory;


}
