import java.util.BitSet;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 29/03/19
 */

public class primesieve {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int n = io.getInt();
        int q = io.getInt();

        generateBitSet(n);
        io.println(numberOfPrimes);

        for(int i = 0; i < q; i++) {
            int test = io.getInt();
            io.println(isPrime(test) ? '1' : '0');
        }

        io.close();

    }

    static BitSet primeBitSet;
    static int numberOfPrimes = 0;

    static void generateBitSet(int prime) {
        int size = prime + 1;
        primeBitSet = new BitSet(size);

        primeBitSet.set(0, size, true);

        primeBitSet.set(0, false);
        primeBitSet.set(1, false);

        for(int i = 2; i <= size; i++) {
            if(primeBitSet.get(i)) {
                for (long j = (long)i * (long)i; j <= size; j += i) {
                    primeBitSet.set((int)j, false);
                }
                numberOfPrimes++;
            }
        }
    }

    static boolean isPrime(int i) {
        return primeBitSet.get(i);
    }

}
