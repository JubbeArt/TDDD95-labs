import java.util.ArrayList;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 24/01/19
 */

public class Intervalcover {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        while(io.hasMoreTokens()) {
            double A = io.getDouble();
            double B = io.getDouble();
            int n = io.getInt();

            List<Interval> intervals = new ArrayList<>(n);

            for(int i = 0; i < n; i++) {
                double start = io.getDouble();
                double end = io.getDouble();
                intervals.add(new Interval(start, end, i));
            }

            cover(new Interval(A, B, -1), intervals);
        }

        io.close();
    }

    static void cover(Interval AB, List<Interval> intervals) {
        double covered = AB.start;
        List<Integer> optimal = new ArrayList<>();

        // Special case if A = B, just find one interval that fits
        if(AB.start == AB.end ) {
            for(Interval inter : intervals) {
                if (inter.start <= AB.start && inter.end >= AB.end) {
                    io.println(1);
                    io.println(inter.index);
                    return;
                }
            }

            io.println("impossible");
            return;
        }


        // Find the longest available interval
        while(covered < AB.end) {
            double longest_range = 0;
            int longest_range_i = -1;

            for(int i = 0; i < intervals.size(); i++) {
                if(intervals.get(i).start <= covered) {
                    double currentDistance = intervals.get(i).end - covered;

                    if(currentDistance > longest_range) {
                        longest_range = currentDistance;
                        longest_range_i = i;
                    }
                }
            }

            if(longest_range_i == -1) {
                io.println("impossible");
                return;
            }

            optimal.add(intervals.get(longest_range_i).index);
            covered = intervals.get(longest_range_i).end;
            intervals.remove(longest_range_i);
        }

        io.println(optimal.size());
        for(int i : optimal) {
            io.print(i + " ");
        }

        io.println();

    }

    static class Interval {
        public double start;
        public double end;
        public int index;

        Interval(double start, double end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }

        public String toString() {
            return String.format("(%f, %f)", this.start, this.end);
        }
    }
}
