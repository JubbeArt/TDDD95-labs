public class UnionFind {
    int[] parents;
    int[] counter;

    UnionFind(int size) {
        parents = new int[size];
        counter = new int[size];

        for(int i = 0; i < size; i++) {
            parents[i] = i;
        }


    }

    int getRoot(int a) {
        while(parents[a] != a) {
            a = parents[a];
        }

        return a;
    }

    void union(int a, int b) {
        int aRoot = getRoot(a);
        int bRoot = getRoot(b);

        if(aRoot == bRoot) {
            return;
        }

        if(counter[aRoot] < counter[bRoot]) {
            parents[aRoot] = bRoot;
        } else {
            parents[bRoot] = aRoot;

            if(counter[aRoot] == counter[bRoot]) {
                counter[aRoot] += 1;
            }
        }
    }

    boolean same(int a, int b) {
        return getRoot(a) == getRoot(b);
    }

}
