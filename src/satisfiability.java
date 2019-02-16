import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */


public class satisfiability {
    static Kattio io = new Kattio(System.in, System.out);

    // CUSTOM MADE "BOOLEAN" TYPE,
    // INSPIRED BY https://thedailywtf.com/articles/What_Is_Truth_0x3f_
    static final int TRUE = 1;
    static final int FALSE = -1;
    static final int UNASSINGED = 0;

    public static void main(String[] args) {
        // do this since Kattio.java can't handle getting full lines
        Scanner scanner = new Scanner(System.in);

        int problems = scanner.nextInt();

        int[] assignments = null;

        for(int p = 0; p < problems; p++) {
            int variables = scanner.nextInt();
            int numOfClauses = scanner.nextInt();

            // start with all assignments set to "UNASSINGED"
            assignments = new int[variables];
            clauses = new ArrayList<>(numOfClauses);
            scanner.nextLine();

            for(int i = 0; i < numOfClauses; i++) {
                clauses.add(new ArrayList<>());

                String line = scanner.nextLine();
                String[] literals = line.split(" v ");

                for(String lit : literals) {
                    boolean negated = lit.charAt(0) == '~';
                    String number = negated ? lit.substring(2) : lit.substring(1);
                    int index = Integer.valueOf(number) - 1;
                    clauses.get(i).add(new Literal(index, negated));
                }
            }

            // use the DPLL algorithm
            boolean satisfiable = DPLL(assignments);
            io.println(satisfiable ? "satisfiable" : "unsatisfiable");
        }

        io.close();
    }

    static List<List<Literal>> clauses;

    static boolean DPLL(int[] assignments) {
        boolean holdsForAll = true;

        for(List<Literal> clause : clauses) {
            // Unallowed assignment has been done
            if(everyLiteralFalse(clause, assignments)) {
                return false;
            }

            if(!atLeastOneTrue(clause, assignments)) {
                holdsForAll = false;
            }
        }

        // FOUND A VIABLE ASSIGNMENT!!!
        if(holdsForAll) return true;

        // unit clause assignment and pure literal elimination not needed to pass Kattis

        int unassignedIndex = findUnassigned(assignments);

        int[] assignmentsForTrue = assignments.clone();
        assignmentsForTrue[unassignedIndex] = TRUE;

        int[] assignmentsForFalse = assignments.clone();
        assignmentsForFalse[unassignedIndex] = FALSE;

        return DPLL(assignmentsForTrue) || DPLL(assignmentsForFalse);
    }

    static int findUnassigned(int[] assignments) {
        for(int i = 0; i < assignments.length; i++) {
            if(assignments[i] == UNASSINGED) return i;
        }

        return -1;
    }

    static boolean atLeastOneTrue(List<Literal> clause, int[] assignments) {
        for(Literal literal : clause) {
            if(literal.isTrue(assignments[literal.index]) == TRUE) {
                return true;
            }
        }
        return false;
    }

    static boolean everyLiteralFalse(List<Literal> clause, int[] assignments) {
        for(Literal literal : clause) {
            int value = literal.isTrue(assignments[literal.index]);

            if(value == TRUE || value == UNASSINGED) {
                return false;
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

        int isTrue(int value) {
            if(value == UNASSINGED) return UNASSINGED;

            boolean bool = value == 1;

            if(negated) bool = !bool;

            return bool ?  TRUE : FALSE;
        }

        @Override
        public String toString() {
            return (negated ? "~" : "") + "X" + (index + 1);
        }
    }
}

