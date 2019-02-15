import java.util.ArrayList;
import java.util.Scanner;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 15/02/19
 */

public class Codetheft {
    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        // do this since Kattio.java can't handle getting full lines
        Scanner scanner = new Scanner(System.in);

        int numOfFiles = scanner.nextInt();

        String[] fileNames = new String[numOfFiles];
        ArrayList<String>[] files = new ArrayList[numOfFiles];



        for(int f = 0; f < numOfFiles; f++) {


            String lines = scanner.nextLine();


            if(lines.trim().equals("")) continue;



            io.println(lines);
        }

        io.close();
    }
}
