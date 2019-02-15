/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Pointinpolygon {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while (true) {
            int vertices = io.getInt();
            if(vertices == 0) break;

            VectorInt[] poly = new VectorInt[vertices];

            for(int i = 0; i < vertices; i++) {
                poly[i] = new VectorInt(io.getInt(), io.getInt());
            }

            int tests = io.getInt();

            for(int i = 0; i < tests; i++) {
                VectorInt point = new VectorInt(io.getInt(), io.getInt());
                solve(point, poly);
            }
        }

        io.close();
    }

    static void solve(VectorInt point, VectorInt[] poly) {
        int crossings = 0;

        for(int i = 0; i < poly.length; i++) {
            int next = (i + 1) % poly.length;
            int x = point.x;
            int y = point.y;

            int xi = poly[i].x;
            int yi = poly[i].y;

            int xi1 = poly[next].x;
            int yi1 = poly[next].y;

            boolean insideX = (xi <= x && x < xi1) || (xi1 <= x && x < xi);

            if(isOnLine(x, y, xi, yi, xi1, yi1)) {
                io.println("on");
                return;
            }

            // polygon is a vertical line here
            if(xi1 == xi) {
                if(x == xi) {
                    crossings++;
                }
            } else {
                double slope = (yi1 - yi) / (double)(xi1 - xi);
                boolean aboveInY = y < slope * (x - xi) + yi;

                if(insideX && aboveInY) {
                    crossings++;
                }
            }
        }

        io.println(((crossings % 2) != 0) ? "in" : "out");
//        io.println(crossings);
    }

    static boolean isOnLine(int pointX, int pointY, int startX, int startY, int endX, int endY) {
        // straight vertical line
        if(startX == endX) {
            return pointX == startX && ( (startY <= pointY && pointY <= endY) || (endY <= pointY && pointY <= startY) );
        }

        double slope = (endY - startY) / (double)(endX - endY);
        double y = slope * (pointX - startX) + startY;
        return y == pointY;
    }
}