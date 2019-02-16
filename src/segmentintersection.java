/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 16/02/19
 */


public class segmentintersection {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfTests = io.getInt();

        for(int i = 0; i < numOfTests; i++) {
            Line line1 = new Line(
                    new Point(io.getInt(), io.getInt()),
                    new Point(io.getInt(), io.getInt())
            );

            Line line2 = new Line(
                    new Point(io.getInt(), io.getInt()),
                    new Point(io.getInt(), io.getInt())
            );

            solve(line1, line2);

        }

        io.close();
    }

    static void solve(Line line1, Line line2) {

    }

    static boolean equal(double a, double b) {
        return Math.abs(a - b) <= 0.0000001;
    }

    static class Line {
        Point left;
        Point right;

        double k;
        double m;

        boolean isVertical;
        boolean isHorizontal;
        boolean isPoint;

        Line(Point p1, Point p2) {
            if(p1.x <= p2.x) {
                left = p1;
                right = p2;
            } else {
                left = p2;
                right = p1;
            }

            isHorizontal = p1.y == p2.y;
            isVertical = p1.x == p2.x;
            isPoint = isHorizontal && isVertical;

            if(isVertical) return;
            k = (double)(right.y - left.y) / (right.x - left.x);
            m = left.y - k * left.x;
        }

        boolean insideX(int x) {
            return left.x <= x && x <= right.x;
        }

        boolean insideY(int y) {
            return ( left.y <= y && y <= right.y ) || ( right.y <= y && y <= left.y );
        }
    }
}
