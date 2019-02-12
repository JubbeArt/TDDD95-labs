/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class Cudak {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        long A = io.getLong();
        long B = io.getLong();
        long S = io.getLong();

        solve(A, B, S);
        io.close();
    }

    static void solve(long A, long B, long S) {
        long numbers = 0;
        long lowestNumber = 0;
        boolean hasLowestNumber = false;

        long sum = digitSum(A - 1);

        for(long i = A; i <= B; i++) {
            sum++;

            // there is a smarter way to write this
            if(i % 10 == 0)                    sum -= 9;
            if(i % 100 == 0)                   sum -= 9;
            if(i % 1000 == 0)                  sum -= 9;
            if(i % 10000 == 0)                 sum -= 9;
            if(i % 100000 == 0)                sum -= 9;
            if(i % 1000000 == 0)               sum -= 9;
            if(i % 10000000 == 0)              sum -= 9;
            if(i % 100000000 == 0)             sum -= 9;
            if(i % 1000000000 == 0)            sum -= 9;
            if(i % 10000000000L == 0)          sum -= 9;
            if(i % 100000000000L == 0)         sum -= 9;
            if(i % 1000000000000L == 0)        sum -= 9;
            if(i % 10000000000000L == 0)       sum -= 9;
            if(i % 100000000000000L == 0)      sum -= 9;
            if(i % 1000000000000000L == 0)     sum -= 9;
            if(i % 10000000000000000L == 0)    sum -= 9;
            if(i % 100000000000000000L == 0)   sum -= 9;
            if(i % 1000000000000000000L == 0)  sum -= 9;

            if(sum == S) {
                numbers++;

                if(!hasLowestNumber) {
                    lowestNumber = i;
                    hasLowestNumber = true;
                }
            }
        }

        io.println(numbers);
        io.println(lowestNumber);
    }

    static long digitSum(long number) {
        long sum = 0;

        while(number > 0) {
            long digit = number % 10;
            sum += digit;

            number = number / 10;
        }

        return sum;
    }

}
