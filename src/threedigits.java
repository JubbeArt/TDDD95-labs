
/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 03/04/19
 */

public class threedigits {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        solve(io.getLong());
        io.close();
    }

    static void solve(long n) {
        long numberOfTwos = 0;
        long numberOfFives = 0;
        long lastThreeDigits = 1;

        for(long i = 1; i <= n; i++) {
            long tmp = i;

            while (tmp % 5 == 0) {
                numberOfFives++;
                tmp /= 5;
            }
        }

        for(long i = 1; i <= n; i++) {
            long tmp = i;

            // remove all factors of 5
            while (tmp % 5 == 0) {
                tmp /= 5;
            }

            // remove all factors of 2, but not more than we did for the factors of 5
            // (trailing zeros will be a multiplication of 2 and 5)
            while(tmp % 2 == 0 && numberOfTwos < numberOfFives) {
                numberOfTwos++;
                tmp /= 2;
            }

            // always keep last 3 digits of factorial
            lastThreeDigits = lastThreeDigits * tmp % 1000;
        }

        // if factorial becomes bigger than 1000 we might need to left pad with zeros
        if(n >= 7) {
            io.println(String.format("%03d", lastThreeDigits));
        } else {
            io.println(lastThreeDigits);
        }
    }
}
