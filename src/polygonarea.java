/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class polygonarea {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while (true) {
            int vertices = io.getInt();
            if(vertices == 0) break;

            Point[] points = new Point[vertices];

            for(int i = 0; i < vertices; i++) {
                points[i] = new Point(io.getInt(), io.getInt());
            }

            solve(points);
        }

        io.close();
    }

    static void solve(Point[] points) {
        double area = 0;

        // do the shoelace formula
        for(int k = 0; k < points.length; k++) {
            int xk = points[k].x;
            int yk = points[k].y;

            int xk1 = k == points.length - 1 ? points[0].x : points[k+1].x;
            int yk1 = k == points.length - 1 ? points[0].y : points[k+1].y;

            area += (xk1 + xk) * (yk1 - yk);
        }

        area = area / 2;

        // sign of area will determine direction of polygon
        io.print(area >= 0 ? "CCW" : "CW");
        io.println(" " + String.format("%.1f", Math.abs(area)));
    }
}