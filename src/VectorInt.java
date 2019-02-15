/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class VectorInt {
    final int x;
    final int y;

    VectorInt() {
        this(0, 0);
    }

    VectorInt(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public VectorInt add(int x, int y) {
        return new VectorInt(this.x + x, this.y + y);
    }

    public VectorInt scale(int scalar) {
        return new VectorInt(this.x * scalar, this.y * scalar);
    }

    public int scalarProduct(int x, int y) {
        return this.x * x + this.y * y;
    }

    public int crossProduct(int x, int y) {
        return this.x * y - this.y * x;
    }

    public double distance(int x, int y) {
        return Math.sqrt( Math.pow(this.x - x, 2)+ Math.pow(this.y - y, 2) );
    }

    public double angle(int x, int y) {
        return Math.acos(this.scalarProduct(x, y) / (this.magnitude() * magnitude(x, y)) );
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
     * Duplicated methods for using the Vector class as input
     */

    public VectorInt add(VectorInt v) {
        return add(v.x, v.y);
    }

    public int scalarProduct(VectorInt v) {
        return scalarProduct(v.x, v.y);
    }

    public int crossProduct(VectorInt v) {
        return crossProduct(v.x, v.y);
    }

    public double distance(VectorInt v) {
        return distance(v.x, v.y);
    }

    public double angle(VectorInt v) {
        return angle(v.x, v.y);
    }

    public boolean equal(VectorInt v) {
        return equal(v.x, v.y);
    }

    public VectorInt clone() {
        return new VectorInt(x, y);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
