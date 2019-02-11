import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Turbo2 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt();
            array = new ArrayList<>(n);

            for(int i = 0; i < n; i++) {
                array.add(io.getInt());
            }

            solve();
        }

        io.close();
    }

    static ArrayList<Integer> array;

    static void solve() {
        boolean isStart = true;

//        io.println(array);

        while(array.size() > 0) {
            if(isStart) {
                int index = indexOfMin();
                array.remove(index);
                io.println(index);
            } else {
                int index = indexOfMax();
                array.remove(index);
                io.println(array.size() - index);
            }

//            io.println(array);
            isStart = !isStart;
        }
    }

    static int indexOfMax() {
        int i = 0;
        int maxIndex = 0;
        int maxValue = -1;

        for(int value : array) {
            if(value > maxValue) {
                maxIndex = i;
                maxValue = value;
            }

            i++;
        }

        return maxIndex;
    }

    static int indexOfMin() {
        int i = 0;
        int minIndex = 0;
        int minValue = 100_002;


        for(int value : array) {
            if(value < minValue) {
                minIndex = i;
                minValue = value;
            }

            i++;
        }

        return minIndex;
    }
}
