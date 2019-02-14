
/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */


public class Slatkisi {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        long C = io.getLong();
        int K = io.getInt();

        solve(C, K);
        io.close();
    }


    public static void solve(long C, int K) {
        double lowestBill = Math.pow(10, K);

        double a = C / lowestBill;
        a = Math.round(a);

        io.println((int) (lowestBill * a));
    }
}
