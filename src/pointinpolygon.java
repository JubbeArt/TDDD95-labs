/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */

public class pointinpolygon {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        while (true) {
            int vertices = io.getInt();
            if(vertices == 0) break;

            Point[] poly = new Point[vertices];

            for(int i = 0; i < vertices; i++) {
                poly[i] = new Point(io.getInt(), io.getInt());
            }

            int tests = io.getInt();

            for(int i = 0; i < tests; i++) {
                Point point = new Point(io.getInt(), io.getInt());
                solve(point, poly);
            }
        }

        io.close();
    }

    static void solve(Point point, Point[] poly) {
        // calculate winding number with respects to the point
        double windingNumber = 0;

        for(int i = 0; i < poly.length; i++) {
            int next = (i + 1) % poly.length;
            Point p1 = poly[i];
            Point p2 = poly[next];

            if(isOnLine(point, p1, p2)) {
                io.println("on");
                return;
            }

            // put center point at the origin
            Point tmp1 = p1.sub(point);
            Point tmp2 = p2.sub(point);

            windingNumber += tmp1.signedAngle(tmp2);
        }

        boolean isZero = Math.abs(windingNumber) <= 0.000001;
        io.println(!isZero ? "in" : "out");
    }

    static boolean isOnLine(Point point, Point line1, Point line2) {
        // check if triangle inequality does not hold
        double directDistance = line1.distance(line2);
        double distanceViaPoint = point.distance(line1) + point.distance(line2);
        return Math.abs(directDistance - distanceViaPoint) <= 0.00000001;
    }
}