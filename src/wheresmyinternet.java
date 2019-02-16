/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class wheresmyinternet {
    static Kattio io = new Kattio(System.in, System.out);


    static int[] parents;
    static int[] counter;

    public static void main(String[] args) {
        int numOfHouses = io.getInt();
        int numOfCables = io.getInt();

        parents = new int[numOfHouses+1];
        counter = new int[numOfHouses+1];

        for(int i = 0; i <= numOfHouses; i++) {
            parents[i] = i;
        }


        for(int i = 0; i < numOfCables; i++) {
            int house1 = io.getInt();
            int house2 = io.getInt();

            union(house1, house2);
        }

        boolean allConnected = true;

        for(int i = 2; i <= numOfHouses; i++) {
            if(!same(1, i)) {
                io.println(i);
                allConnected = false;
            }
        }

        if(allConnected) {
            io.println("Connected");
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