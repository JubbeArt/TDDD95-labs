/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class PointD {
    final double x;
    final double y;

    PointD(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public PointD add(double x, double y) {
        return new PointD(this.x + x, this.y + y);
    }

    public PointD sub(double x, double y) {
        return add(-x, -y);
    }

    public PointD scale(double scalar) {
        return new PointD(this.x * scalar, this.y * scalar);
    }

    public double scalarProduct(double x, double y) {
        return this.x * x + this.y * y;
    }

    public double crossProduct(double x, double y) {
        return this.x * y - this.y * x;
    }

    public double distance(double x, double y) {
        return Math.sqrt( Math.pow(this.x - x, 2)+ Math.pow(this.y - y, 2) );
    }

    public double angle(double x, double y) {
        return Math.acos(this.scalarProduct(x, y) / (this.magnitude() * magnitude(x, y)) );
    }

    // counterclockwise is positive angle, going from "this object" to "v"
    // e.g. this(1, 0) to v(1, 0) -> pi/2
    public double signedAngle(PointD v) {
        double angle = Math.atan2(v.y, v.x) - Math.atan2(y, x);

        if (angle > Math.PI) {
            return angle - 2 * Math.PI;
        }
        if (angle < -Math.PI) {
            return angle + 2 * Math.PI;
        }

        return angle;
    }

    public boolean equal(double x, double y) {
        return this.x == x && this.y == y;
    }

    public boolean almostEqual(double x, double y) {
        double eps = 0.0001;
        return Math.abs(this.x - x) <= eps && Math.abs(this.y - y) <= eps;
    }

    public double magnitude() {
        return magnitude(this.x, this.y);
    }

    static double magnitude(double x, double y) {
        return Math.sqrt(x * x + y * y);
    }



    /**
     * Duplicated methods for using the Vector class as input
     */

    public PointD add(PointD v) {
        return add(v.x, v.y);
    }

    public PointD sub(PointD v) {
        return sub(v.x, v.y);
    }

    public double scalarProduct(PointD v) {
        return scalarProduct(v.x, v.y);
    }

    public double crossProduct(PointD v) {
        return crossProduct(v.x, v.y);
    }

    public double distance(PointD v) {
        return distance(v.x, v.y);
    }

    public double angle(PointD v) {
        return angle(v.x, v.y);
    }

    public boolean equal(PointD v) {
        return equal(v.x, v.y);
    }

    public boolean almostEqual(PointD v) {
        return almostEqual(v.x, v.y);
    }

    public PointD clone() {
        return new PointD(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }
}
