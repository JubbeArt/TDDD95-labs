/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class ignore {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            int K = io.getInt();

            String number = getReadableNumber(K);
            printNumber(number);
        }

        io.close();
    }

    // (0, 1, 2, 3, 5, 6, 8, 9) is the valid base
    // this is same as base 7

    // Mapping from base 7 to the valid one is:
    // 0 -> 0
    // 1 -> 1
    // 2 -> 2
    // 3 -> 5
    // 4 -> 6
    // 5 -> 8
    // 6 -> 9

    static String getReadableNumber(int K) {
        String base7 = Integer.toString(K, 7);
        String baseValid = "";

        for(int i = 0; i < base7.length(); i++) {
            char c = base7.charAt(i);

            if(c == '3') {
                baseValid += '5';
            } else if(c == '4') {
                baseValid += '6';
            }else if(c == '5') {
                baseValid += '8';
            }else if(c == '6') {
                baseValid += '9';
            }else {
                baseValid += c;
            }
        }

        return baseValid;
    }

    // print number "upside down", rules are:
    // 6 -> 9
    // 9 -> 6
    // rest is the same
    static void printNumber(String number) {
        for(int i = number.length() - 1; i >= 0; i--) {
            char current = number.charAt(i);

            if(current == '6') {
                io.print('9');
            } else if(current == '9') {
                io.print('6');
            } else {
                io.print(current);
            }
        }

        io.println();
    }
}
