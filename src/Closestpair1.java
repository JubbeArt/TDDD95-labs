import java.util.Arrays;
import java.util.Comparator;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 16/02/19
 */


public class Closestpair1 {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while (true) {
            int numbOfPoint = io.getInt(); // how many objects fit in cache

            if(numbOfPoint == 0) break;

            PointD[] points = new PointD[numbOfPoint];

            for(int i = 0; i < numbOfPoint; i++) {
                points[i] = new PointD(io.getDouble(), io.getDouble());
            }

            Arrays.sort(points, (p1, p2) -> {
                if(p1.x == p2.x) return (int)(p1.y - p2.y);
                return (int)(p1.x - p2.x);
            });
            solve(points, 0, numbOfPoint - 1);
        }

        io.close();
    }

    static double solve(PointD[] points, int startIndex, int endIndex) {

//        double L = 0;
//        for(int i = startIndex; i <= endIndex; i++) {
//            L += points[i].x;
//            io.println(points[i]);
//        }
//
//        L = L / (endIndex - startIndex + 1);
//
//        io.println(L);
//        io.println();
        int half = startIndex + (startIndex - endIndex) / 2;
        double minDistL = solve(points, startIndex, half);
        double minDistR = solve(points, half + 1, endIndex);

        double minDistLR = Math.min(minDistL, minDistR);
        return 0;

//        return Math.min(minDistL, Math.min(minDistR, min))
    }
}
