import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 29/03/19
 */

public class convexhull {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while (true) {
            int n = io.getInt();

            if(n == 0) {
                break;
            }

            List<Point> points = new ArrayList<>(n+1);

            io.println(points.size());

            for (int i = 0; i < n; i++) {
                points.add(i, new Point(io.getInt(), io.getInt()));
            }

            solve(points);
        }

        io.close();

    }

    static void solve(List<Point> points) {
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point point, Point t1) {
                return 0;
            }
        });
    }

}