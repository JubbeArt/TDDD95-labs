/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */


public class FenwickTree {
    long[] fenwickTree;

    FenwickTree(int size) {
        // The fenwick tree is always 1 larger than the "actual size",
        // since the 0 index is not visited
        fenwickTree = new long[size + 1];
    }

    void add(int index, long delta) {
        // Make it so the user can use 0-based index
        index++;

        while(index < fenwickTree.length) {
            fenwickTree[index] += delta;
            index += (index & -index); // Go to next power of two
        }
    }

    long sum(int end) {
        long sum = 0;
        int index = end + 1;

        while(index > 0) {
            sum += fenwickTree[index];
            index -= (index & -index); // Go to previous power of two
        }

        return sum;
    }

    // INCLUDING END!!!
    long sum(int start, int end) {
        return sum(end) - sum(start - 1);
    }


    public static void main(String[] args) {
        int size = 5;
        FenwickTree f = new FenwickTree(size);


        for(int i = 0; i < size; i++) {
            f.add(i, i);
        }

        for(int i = 0; i < size; i++) {
            System.out.println(f.sum(i));
        }

        System.out.println("1 + 2 + 3 = " + f.sum(1, 3));
        System.out.println("2 + 3 + 4 = " + f.sum(2, 4));
        System.out.println("2 = " + f.sum(2, 2));
    }

}
