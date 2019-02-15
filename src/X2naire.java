/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class X2naire {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(true) {
            int questions = io.getInt();
            double probability = io.getDouble();

            if(questions == 0 && probability == 0) break;

            solve(questions, probability);

        }
        io.close();
    }

    static void solve(int questions, double probability) {



    }

}
