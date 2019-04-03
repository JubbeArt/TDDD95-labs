/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Point {
    final int x;
    final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point add(int x, int y) {
        return new Point(this.x + x, this.y + y);
    }

    public Point sub(int x, int y) {
        return add(-x, -y);
    }

    public Point scale(int scalar) {
        return new Point(this.x * scalar, this.y * scalar);
    }

    public int scalarProduct(int x, int y) {
        return this.x * x + this.y * y;
    }

    public int crossProduct(int x, int y) {
        return this.x * y - this.y * x;
    }

    public double distance(int x, int y) {
        return Math.sqrt( Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2) );
    }

    public double angle(int x, int y) {
        return Math.acos(this.scalarProduct(x, y) / (this.magnitude() * magnitude(x, y)) );
    }

    // counterclockwise is positive angle, going from "this object" to "v"
    // e.g. this(1, 0) to v(1, 0) -> pi/2
    public double signedAngle(Point v) {
//        double angle1 = angle(v);

        double angle = Math.atan2(v.y, v.x) - Math.atan2(y, x);

        if (angle > Math.PI) {
            return angle - 2 * Math.PI;
        }
        if (angle < -Math.PI) {
            return angle + 2 * Math.PI;
        }

        return angle;

//        if(this.scalarProduct(v) )
//        double angle2 = v.angle(1, 0);
//
//        if(angle1 < angle2) return ;
//        return -angle(v);
    }

    public boolean equal(int x, int y) {
        return this.x == x && this.y == y;
    }

    public double magnitude() {
        return magnitude(this.x, this.y);
    }

    static double magnitude(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }



    /**
     * Duplicated methods for using the PointD class as input
     */

    public Point add(Point v) {
        return add(v.x, v.y);
    }

    public Point sub(Point v) {
        return sub(v.x, v.y);
    }

    public int scalarProduct(Point v) {
        return scalarProduct(v.x, v.y);
    }

    public int crossProduct(Point v) {
        return crossProduct(v.x, v.y);
    }

    public double distance(Point v) {
        return distance(v.x, v.y);
    }

    public double angle(Point v) {
        return angle(v.x, v.y);
    }

    public boolean equal(Point v) {
        return equal(v.x, v.y);
    }

    public Point clone() {
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
