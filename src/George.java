/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 11/02/19
 */

public class George {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int intersections = io.getInt(); // N
        int streets = io.getInt(); // M

        int lukaStart = io.getInt();
        int lukaEnd = io.getInt();

        int lukaStartTime = io.getInt();

        int numberOfGeorgeIntersections = io.getInt();


        io.close();
    }
}
