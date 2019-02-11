import java.util.Arrays;

public class Caching {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            cacheSize = io.getInt(); // how many objects fit in cache
            int n = io.getInt(); // how many object are in the system
            int a = io.getInt(); // how many accesses will occur

            for(int i = 0; i < a; i++) {
                accesses[i] = io.getInt();
            }

            solve();
        }

        io.close();
    }

    static int[] accesses;
    static int cacheSize;
    static public void solve() {

    }
}
