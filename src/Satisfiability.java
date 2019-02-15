import java.util.List;
import java.util.Set;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */


public class Satisfiability {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {

        solve();

        io.close();
    }

    // use the DPLL algorithm
    static void solve() {

    }

    static List<List<Literal>> clauses;

    //static boolean DPLL(Set<Integer> unasignedIndexes, int[] assignments) {
    static boolean DPLL(int[] assignments) {
        boolean holdForAll = true;

        for(List<Literal> literals: clauses) {
            for(Literal lit : literals) {
                // unassigned
                if(assignments[lit.index] == 0) {
                    holdForAll = false;
                    break;
                }


            }
        }

        return true;
    }


    // Like "X1" or "~X3"
    static class Literal {
        int index; // 0 based, so X1 has index 0
        boolean negated;

        Literal(int index, boolean negated) {
            this.index = index;
            this.negated = negated;
        }

        boolean isTrue(int value) {
            boolean tru = value == 1;

            if(negated) return !tru;
            return tru;
        }

        @Override
        public Object clone()  {
            return new Literal(index, negated);
        }

        @Override
        public String toString() {
            String s = "";
            if(negated) s += "~";
            s += (index + 1);
            return  s;
        }
    }

//    static boolean or(List<Literal> literals) {
//        for(Literal l : literals) {
//            if(l.isTrue()) {
//                return  true;
//            }
//        }
//
//        return false;
//    }
}

