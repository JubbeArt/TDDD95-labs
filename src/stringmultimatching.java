import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 10/03/19
 */


public class stringmultimatching {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while(scanner.hasNext()) {
            int numOfPatterns = io.getInt();
            String[] patterns = new String[numOfPatterns];

            for(int i = 0; i < numOfPatterns; i++) {
                patterns[i] = scanner.nextLine();
            }
            String text = scanner.nextLine();

            solve(patterns, text);
        }

        io.close();
    }

    static void solve(String[] patterns, String text) {
        Node start = new Node(' ');

        for(int i = 0; i < patterns.length; i++) {
            List<Character> chars = patterns[i].chars().mapToObj(c -> (char)c).collect(Collectors.toList());
            start.addPattern(chars, i);
        }


    }

    static class Node {
        // could also be made as an ASCII-array...
        Map<Character, Node> children = new HashMap<>();
        char character;
        boolean isEndOfPattern = false;
        List<Integer> patternIndexes = new ArrayList<>();

        Node(char character) {
            this.character = character;
        }


        void addPattern(List<Character> characters, int patternIndex) {
            boolean isLast = characters.size() == 1;

            // remove first and add to tree,
            // do this recursively till nothing is left to add
            char character = characters.remove(0);

            Node child = children.get(character);

            // create new
            if(child == null) {
                child = new Node(character);
                children.put(character, child);
            }


            if(isLast) {
                child.patternIndexes.add(patternIndex);
                child.isEndOfPattern = true;
            } else {
                child.addPattern(characters, patternIndex);
            }
        }
    }

}