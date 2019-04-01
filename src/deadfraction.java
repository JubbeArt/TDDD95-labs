import java.math.BigInteger;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 01/04/19
 */

public class deadfraction {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(true) {
            String number = io.getWord();

            if(number.equals("0")) {
                break;
            }

            solve(number.substring(2, number.length() - 3));
        }

        io.close();
    }

    /**
     * x = 0.474612399...
     * 10^9x = 474612399.999
     * 10^8x = 47461239.999
     *
     * (10^9-10^8)x = 474612399 - 47461239
     *
     * x = (474612399 - 47461239) / (10^9 - 10^8)
     */

    static void solve(String numberStr) {
        BigInteger top;

        // Number only has 1 decimal, e.g. 0.2...
        if(numberStr.length() == 1) {
            top = new BigInteger(numberStr);
        } else {
            top = new BigInteger(numberStr)
                    .subtract(new BigInteger(numberStr.substring(0, numberStr.length() - 1)));
        }

        BigInteger bottom = new BigInteger("10").pow(numberStr.length())
                .subtract(new BigInteger("10").pow(numberStr.length() - 1));

        BigInteger gcd = top.gcd(bottom);
        io.println(top.divide(gcd).toString() + "/" + bottom.divide(gcd).toString());
    }
}
