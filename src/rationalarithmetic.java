/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */

public class rationalarithmetic {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfOperations = io.getInt();

        for(int i = 0; i < numOfOperations; i++) {
            RationalNumber left = new RationalNumber(io.getInt(), io.getInt());
            String op = io.getWord();
            RationalNumber right = new RationalNumber(io.getInt(), io.getInt());

            switch (op.charAt(0)) {
                case '+':
                    left.add(right);
                    break;
                case '-':
                    left.sub(right);
                    break;
                case '*':
                    left.mul(right);
                    break;
                case '/':
                    left.div(right);
            }

            io.println(left);
        }

        io.close();
    }

    static class RationalNumber {
        long a; // dividend
        long b; // divisor

        RationalNumber(long top, long bottom) {
            this.a = top;
            this.b = bottom;
        }

        public void add(long c, long d) {
            a = a * d + b * c;
            b = b * d;
            gcd();
        }

        public void sub(long c, long d) {
            add(-c, d);
        }

        public void mul(long c, long d) {
            a = a * c;
            b = b * d;
            gcd();
        }

        public void div(long c, long d) {
            mul(d, c);
        }

        public boolean equal(RationalNumber other) {
            this.gcd();
            other.gcd();

            if(a == 0 && other.a == 0) return true;
            return a == other.a && b == other.b;
        }

        public void gcd() {
            long tmpA = Math.abs(a);
            long tmpB = Math.abs(b);

            while(tmpB > 0) {
                long tmp = tmpA;
                tmpA = tmpB;
                tmpB = tmp % tmpB;
            }

            long gcd = tmpA;
            a = a / gcd;
            b = b / gcd;
        }

        public void add(RationalNumber other) {
            add(other.a, other.b);
        }

        public void sub(RationalNumber other) {
            sub(other.a, other.b);
        }

        public void mul(RationalNumber other) {
            mul(other.a, other.b);
        }

        public void div(RationalNumber other) {
            div(other.a, other.b);
        }

        @Override
        public String toString() {
            boolean isNegative = a < 0 && b > 0 || b < 0 && a > 0;
            return (isNegative ? "-" : "") + Math.abs(a) + " / " + Math.abs(b);
        }
    }
}
