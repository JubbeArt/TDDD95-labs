public class FenwickTree {
    long[] fenwickTree;

    FenwickTree(int size) {
        // The fenwick tree is always 1 larger than the "actual size",
        // since the 0 index is not used
        fenwickTree = new long[size + 1];
    }

    void add(int index, long delta) {
        index++;

        while(index <= fenwickTree.length - 1) {
            fenwickTree[index] += delta;
            index += (index & -index); // Go to next power of two
        }
    }

    long sum(int end) {
        long sum = 0;
        int index = end;

        while(index > 0) {
            sum += fenwickTree[index];
            index -= (index & -index); // Go to previous power of two
        }

        return sum;
    }

    long sum(int start, int end) {
        return sum(end) - sum(start - 1);
    }

}
