
/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 31/03/19
 */

public class factovisors {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while(io.hasMoreTokens()) {
            factorialNumber = io.getInt();
            testNumber = io.getInt();

            // handle trivial cases first
            if(testNumber <= 0) {
                printFailure();
                continue;
            } else if(testNumber <= factorialNumber || testNumber == 1) {
                printSuccess();
                continue;
            }

            solve();
            io.flush();
        }


        io.close();
    }

    static int testNumber;
    static int factorialNumber;

    static void solve() {
        int c = 0;
        int reducedTestNumber = testNumber;
        int upperLimit = (int) Math.ceil(Math.sqrt(testNumber)) + 1;

        for(int i = 2; i < upperLimit; i++) {
            // reduce test number as long as possible
            while(reducedTestNumber % i == 0) {
                reducedTestNumber = reducedTestNumber / i;
                c++;
            }

            if(c == 0) {
                continue;
            }

            int x = i;

            // get factors from factorial number
            while(x <= factorialNumber) {
                c = c - factorialNumber / x;
                x = x * i;
            }

            if(c <= 0) {
                c = 0;
                continue;
            } else {
                printFailure();
                return;
            }
        }


        if(reducedTestNumber <= factorialNumber ) {
            printSuccess();
            return;
        }

        printFailure();
    }

    static void printSuccess() {
        io.printf("%d divides %d!\n", testNumber, factorialNumber);
    }

    static void printFailure() {
        io.printf("%d does not divide %d!\n", testNumber, factorialNumber);
    }
}
