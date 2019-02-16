/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 26/01/19
 */


public class unionfind {
    static Kattio io = new Kattio(System.in, System.out);

    static int[] parents;
    static int[] counter;

    public static void main(String[] args) {
        int N = io.getInt();
        int Q = io.getInt();
        parents = new int[N];
        counter = new int[N];

        for(int i = 0; i < N; i++) {
            parents[i] = i;
        }

        while(io.hasMoreTokens()) {
            char operation = io.getWord().charAt(0);
            int a = io.getInt();
            int b = io.getInt();

            if (operation == '?') {
                io.println(same(a, b) ? "yes" : "no");
            } else {
                union(a, b);
            }
        }

        io.close();
    }

    static int getRoot(int a) {
        while(parents[a] != a) {
            a = parents[a];
        }

        return a;
    }

    static void union(int a, int b) {
        int aRoot = getRoot(a);
        int bRoot = getRoot(b);

        if(aRoot == bRoot) {
            return;
        }

        // Do some kind of fixed order depending on a and b to not get recursive structures
        if(counter[aRoot] < counter[bRoot]) {
            parents[aRoot] = bRoot;
        } else {
            parents[bRoot] = aRoot;

            if(counter[aRoot] == counter[bRoot]) {
                counter[aRoot] += 1;
            }
        }
    }

    static boolean same(int a, int b) {
        return getRoot(a) == getRoot(b);
    }
}
