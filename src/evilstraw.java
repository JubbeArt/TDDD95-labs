
/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 01/04/19
 */

public class evilstraw {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfTests = io.getInt();

        for(int i = 0; i < numOfTests; i++) {
            solve(io.getWord());
        }

        io.close();
    }

    static void solve(String wordStr) {
        char[] word = wordStr.toCharArray();
        int totalSwaps = 0;
        int left = 0;
        int right = word.length - 1;

        while(left <= right) {
            // left and right matches => go to next
            if(word[left] == word[right]) {
                left++;
                right--;
                continue;
            }

            // doesn't match, find one that does
            int swapsMade = findSwap(word, left, right);

            // no match found => not a palindrome
            if(swapsMade == 0) {
                io.println("Impossible");
                return;
            }

            totalSwaps += swapsMade;
            left++;
            right--;
        }

        io.println(totalSwaps);
    }

    static int findSwap(char[] word, int left, int right) {
        int swapsMade = 0;
        int nextLeft = left  + 1;
        int nextRight = right - 1;

        // just swap the first best character we find, look from both sides
        while(nextLeft <= right - 1)  {
            // found left match, do swap and return
            if(word[left] == word[nextRight]) {
                while(word[left] != word[right]) {
                    swap(word, nextRight, nextRight + 1);
                    swapsMade++;
                    nextRight++;
                }

                return swapsMade;
            }

            // found right match, do swap and return
            if(word[right] == word[nextLeft]) {
                while (word[left] != word[right]) {
                    swap(word, nextLeft, nextLeft - 1);
                    swapsMade++;
                    nextLeft--;
                }

                return swapsMade;
            }

            nextLeft++;
            nextRight--;

        }

        // no match found
        return 0;
    }

    static void swap(char[] word, int index1, int index2) {
        char tmp = word[index1];
        word[index1] = word[index2];
        word[index2] = tmp;
    }

}
