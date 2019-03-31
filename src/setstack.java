import java.util.*;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 01/04/19
 */

public class setstack {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numberOfTests = io.getInt();

        for(int test = 0; test < numberOfTests; test++) {
            stack = new Stack<>();
            unique = new HashMap<>();

            int operations = io.getInt();

            for(int op = 0; op < operations; op++) {

                String operation = io.getWord();

                if(operation.equals("PUSH")) {
                    push();
                } else if(operation.equals("DUP")) {
                    dup();
                } else if(operation.equals("UNION")) {
                    union();
                } else if(operation.equals("INTERSECT")) {
                    intersect();
                } else if(operation.equals("ADD")) {
                    add();
                } else {
                    throw new RuntimeException();
                }

                io.println(stack.peek().size());
            }

            io.println("***");
        }

        io.close();

    }

    static int counter = 0;
    static Stack<Set<Object>> stack;
    static Map<Set<Object>, Object> unique;

    static void push() {
        stack.add(new HashSet<>());
    }

    static void dup() {
        Set<Object> set = stack.pop();
        stack.push(set);
        stack.push(set);
    }

    static void union() {
        Set<Object> set1 = stack.pop();
        Set<Object> set2 = stack.pop();

        Set<Object> newSet = new HashSet<>(set1);
        newSet.addAll(set2);
        stack.add(newSet);
    }

    static void intersect() {
        Set<Object> set1 = stack.pop();
        Set<Object> set2 = stack.pop();

        Set<Object> newSet = new HashSet<>(set1);
        newSet.retainAll(set2);
        stack.add(newSet);
    }

    static void add() {
        Set<Object> set1 = stack.pop();
        Set<Object> set2 = stack.pop();

        Set<Object> newSet = new HashSet<>();

        if(!unique.containsKey(set1)) {
            unique.put(set1, counter);
            newSet.add(counter);
            counter++;
        } else {
            newSet.add(unique.get(set1));
        }

        newSet.addAll(set2);
        stack.add(newSet);
    }
}
