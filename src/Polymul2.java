/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 27/01/19
 */

public class Polymul2 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int T = io.getInt();

        int aDegree = io.getInt();
        int[] aCoeffs = new int[aDegree + 1];

        for(int i = 0; i < aDegree; i++) {
            aCoeffs[i] = io.getInt();
        }

        int bDegree = io.getInt();
        int[] bCoeffs = new int[bDegree + 1];

        for(int i = 0; i < bDegree; i++) {
            bCoeffs[i] = io.getInt();
        }

        solve(aCoeffs, bCoeffs);

        io.close();
    }

    // IF ERROR: CHECK OVERFLOW POSSIBILITY


    static void solve(int[] aCoeffs, int[] bCoeffs) {
        int[] cCoeffs = new int[aCoeffs.length + bCoeffs.length];

        for(int i = 0; i < aCoeffs.length + bCoeffs.length; i++) {


        }
    }
}
