/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 26/01/19
 */


public class unionfind {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int N = io.getInt();
        int Q = io.getInt();

        UnionFind unionFind = new UnionFind(N);

        while(io.hasMoreTokens()) {
            char operation = io.getWord().charAt(0);
            int a = io.getInt();
            int b = io.getInt();

            if (operation == '?') {
                io.println(unionFind.same(a, b) ? "yes" : "no");
            } else {
                unionFind.union(a, b);
            }
        }

        io.close();
    }
}
