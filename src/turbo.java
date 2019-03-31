/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class turbo {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int n = io.getInt();

        array = new int[n];
        indexes = new int[n];
        fenwick = new FenwickTree(n);

        for(int i = 0; i < n; i++) {
            int number = io.getInt() - 1;
            array[i] = number;
            indexes[number] = i;
            fenwick.add(i, 1);
        }

        solve();

        io.close();
    }

    static int[] array;
    static int[] indexes;
    static FenwickTree fenwick;

    static void solve() {
        int startIndex = 0;
        int endIndex = array.length - 1;

        boolean isStart = true;

        while(startIndex != endIndex) {
            if(isStart) {
                int index = indexes[startIndex];
                fenwick.add(index, -1);
                int swaps = (int) fenwick.sum(index);
                io.println(swaps);
                startIndex++;
            } else {
                int index = indexes[endIndex];
                fenwick.add(index, -1);
                int swaps = (int) fenwick.sum(index, array.length - 1);
                io.println(swaps);
                endIndex--;
            }
            isStart = !isStart;
        }

        io.println(0);
    }
}
