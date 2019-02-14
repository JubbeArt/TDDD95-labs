import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 14/02/19
 */

public class Ladice {

    static Kattio io = new Kattio(System.in, System.out);

    public static void main(String[] args) {
        int numOfItems = io.getInt();
        int numOfDrawers = io.getInt();

        Item[] items = new Item[numOfItems];

        for(int i = 0; i < numOfItems; i++) {
            items[i] = new Item(i, io.getInt(), io.getInt());
        }


        solve(items, numOfDrawers);
        io.close();
    }

    static void solve(Item[] items, int numberOfDrawers) {
        int[] drawers = new int[numberOfDrawers];
        Arrays.fill(drawers, -1);

        for(Item item : items) {
            // step 1
            if(drawers[item.drawerA] == -1) {
                drawers[item.drawerA] = item.index;
                io.println("LADICA");
                continue;
            }

            // step 2
            if(drawers[item.drawerB] == -1) {
                drawers[item.drawerB] = item.index;
                io.println("LADICA");
                continue;
            }

            // step 3
            Item current = items[drawers[item.drawerA]];
            boolean foundDrawer = false;

            // look for drawer
            while(true) {
//                if()
            }


        }
    }

    static class Item {
        int index;
        int drawerA;
        int drawerB;


        Item(int index, int drawerA, int drawerB) {
            this.index = index;
            this.drawerA = drawerA;
            this.drawerB = drawerB;
        }
    }

}
