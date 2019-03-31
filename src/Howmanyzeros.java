/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Howmanyzeros {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
//        while(true) {
//            long first = io.getLong();
//            long second = io.getLong();
//
//            if(first == -1 && second == -1) break;
//
//            generateBitSet(first, second);
//
//        }
//        io.println(generateBitSet(0));
//        io.println(generateBitSet(1));
//        io.println(generateBitSet(9));
//        io.println(generateBitSet(20));
//        io.println(generateBitSet(21));
//        io.println();
        io.println(solve(125));
        io.println();
        io.println(solve(200) - solve(100));
        io.println(solve(500) - solve(0));

        io.close();
    }

    static void solve(long first, long second) {



    }

    static int solve(int number) {
        int zeroes = 0;
        while(number > 0) {
            number = number / 10;
            zeroes += number;
        }

        // add 1 to get the initial "0"
        return zeroes + 1;
    }
}
