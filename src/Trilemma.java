import java.util.Arrays;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */

public class Trilemma {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        int triangles = io.getInt();

        for(int caze = 0; caze < triangles; caze++) {
            VectorInt p1 =  new VectorInt(io.getInt(), io.getInt());
            VectorInt p2 =  new VectorInt(io.getInt(), io.getInt());
            VectorInt p3 =  new VectorInt(io.getInt(), io.getInt());

            io.print("Case #" + (caze + 1) + ": ");
            solve(p1, p2, p3);
        }

        io.close();
    }

    static void solve(VectorInt p1, VectorInt p2, VectorInt p3) {
        double[] lengths = new double[]{
                p1.distance(p2),
                p1.distance(p3),
                p2.distance(p3),
        };
        Arrays.sort(lengths);

        double a = lengths[0];
        double b = lengths[1];
        double c = lengths[2];

        // check triangle inequality
        if(c + 0.000001 >= a + b) {
            // inequality did not hold
            io.println("not a triangle");
            return;
        }


        boolean isosceles = a == b || a == c || b == c;
        io.print(isosceles ? "isosceles " : "scalene ");


        // pythagoras => right side
        // abs needed for floating errors
        if(Math.abs(a * a + b * b - c * c) <= 0.000001) {
            io.println("right triangle");
            return;
        }

        // a^2 + b^2 < c^2  => obtuse
        if(a * a + b * b < c * c) {
            io.println("obtuse triangle");
            return;
        }

        io.println("acute triangle");
    }
}