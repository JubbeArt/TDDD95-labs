/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Vector {
    double x;
    double y;

    Vector() {
        this(0, 0);
    }

    Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    public void scale(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    public double scalarProduct(double x, double y) {
        return this.x * x + this.y * y;
    }

    public double distance(double x, double y) {
        return Math.sqrt( (this.x-x)*(this.x-x) + (this.y-y)*(this.y-y) );
    }


    public double angle(double x, double y) {
        return Math.acos(this.scalarProduct(x, y) / (this.magnitude() * magnitude(x, y)) );
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

    public void add(Vector v) {
        add(v.x, v.y);
    }

    public double scalarProduct(Vector v) {
        return scalarProduct(v.x, v.y);
    }

    public double distance(Vector v) {
        return distance(v.x, v.y);
    }

    public double angle(Vector v) {
        return angle(v.x, v.y);
    }


}
