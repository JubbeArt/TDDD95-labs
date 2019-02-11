import java.util.ArrayList;
import java.util.Arrays;

public class Chopwood {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt(); // how many object are in the system

            nodes = new int[n+2];
            parents = new int[n+2];

            for(int i = 0; i < n; i++) {
                nodes[i] = io.getInt();
                parents[i] = i;
            }

            solve();
        }

        io.close();
    }

    // {{},{{}}} => [1, 2]
    // {{},{{{}}}} => [1, 3]
    // { {}, {{}}, {{{}}} } => [ 1, 2, 3]
    // { {}, {{{}}}, {{},{{}}} } => [ 1, 3, [ 1, 2 ] ]

    static int[] nodes;
    static int[] parents;

    static public void solve() {
        for(int i = nodes.length; i >= 0; i--) {

        }
    }


//    static class Set {
//        ArrayList<Set> list = new ArrayList<>();
//
//
//    }
}

