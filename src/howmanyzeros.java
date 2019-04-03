/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class howmanyzeros {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(true) {
            long first = io.getLong();
            long second = io.getLong();

            if(first == -1 && second == -1) break;

            io.println(solve(first, second));
        }
        io.close();
    }

    static long solve(long first, long second) {

        if(first == second) {
            return String.valueOf(first).length() - String.valueOf(first).replace("0", "").length();
        }

        if(first == 0) {
            return numberOfZeros(second);
        }

        return numberOfZeros(second) - numberOfZeros(first - 1);
    }

    // calculates from 0 to N
    public static long numberOfZeros(long n) {
        if(n == 0) {
            return 1;
        }

        long count = 1;
        long tenMultiple = 1;

        while(true) {
            long b = n / tenMultiple;
            long c = n % tenMultiple;
            long a = b / 10;
            b = b % 10;

            if(a == 0) return count;

            if(b == 0) count += (a - 1) * tenMultiple  + c + 1;
            else count += a * tenMultiple;

            tenMultiple *= 10;
        }
    }

}
