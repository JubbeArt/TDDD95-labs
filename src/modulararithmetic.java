
/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 29/03/19
 */


public class modulararithmetic {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(true) {
            long mod = io.getLong();
            int tests = io.getInt();

            if(mod == 0 && tests == 0) {
                break;
            }

            for(int i = 0; i < tests; i++) {
                long x = io.getLong();
                char op = io.getWord().charAt(0);
                long y = io.getLong();


                if(op == '+') {
                    io.println((x + y) % mod);
                } else if(op == '-') {
                    io.println(modulus(x - y, mod));
                } else if(op == '*') {
                    io.println( ((x % mod) * (y % mod)) % mod );
                } else if (op == '/'){
                    solve(x, y, mod);
                }
            }
        }

        io.close();
    }

    static long modulus(long num, long mod) {
        long res = num % mod;

        // cus java '%' operator is actually remainder and not modulo
        if(res < 0) {
            res += mod;
        }

        return res;
    }

    static void solve(long x, long y, long mod) {
        long t = 1;
        long oldT = 0;
        long r = y;
        long oldR = mod;

        while (r != 0) {
            long quotient = oldR / r;

            long tmpR = r;
            r = oldR - quotient * r;
            oldR = tmpR;

            long tmpT = t;
            t = oldT - quotient * t;
            oldT = tmpT;
        }

        // no solution
        if (oldR > 1)  {
            io.println(-1);
            return;
        }

        // remove minus
        oldT = modulus(oldT, mod);
        io.println( ((x % mod) * oldT) % mod );
    }
}
