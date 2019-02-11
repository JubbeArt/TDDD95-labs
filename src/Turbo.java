public class Turbo {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (io.hasMoreTokens()) {
            int n = io.getInt();

            array = new int[n];
            indexes = new int[n];

            for(int i = 0; i < n; i++) {
                int number = io.getInt() - 1;
                array[i] = number;
                indexes[number] = i;
            }

            solve();
        }

        io.close();
    }

    static int[] array;
    static int[] indexes;

    static void solve() {
        int startIndex = 0;
        int endIndex = array.length - 1;

        boolean isStart = true;

        while(startIndex != endIndex) {
            if(isStart) {
                int index = indexes[startIndex];
                int moves = index - startIndex;

                for(int i = index; i > startIndex; i--) {
                    swap(i, i-1);
                }

                io.println(moves);
                startIndex++;
            } else {
                int index = indexes[endIndex];
                int moves = endIndex - index;

                for(int i = index; i < endIndex; i++) {
                    swap(i, i+1);
                }

                io.println(moves);
                endIndex--;
            }
            isStart = !isStart;
        }

        io.println(0);
    }

    static void swap(int index1, int index2) {
        int tmpIndex = indexes[array[index1]];
        indexes[array[index1]] = indexes[array[index2]];
        indexes[array[index2]] = tmpIndex;

        int tmpValue = array[index1];
        array[index1] = array[index2];
        array[index2] = tmpValue;
    }
}
