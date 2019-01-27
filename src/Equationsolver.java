import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: Jesper Wrang (jeswr740)
 * Date: 27/01/19
 */

public class Equationsolver {
    static Kattio io = new Kattio(System.in, System.out);
    static List<int[]> swapsMade;

    public static void main(String[] args) {

        while(io.hasMoreTokens()) {
            int N = io.getInt();
            swapsMade = new ArrayList<>();

            if(N == 0) {
                break;
            }

            double[][] matrix = new double[N][N+1];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    matrix[i][j] = io.getDouble();
                }
            }

            for(int i = 0; i < N; i++) {
                matrix[i][N] = io.getDouble();
            }

            solve(matrix);
        }

        io.close();
    }

    static void printMatrix(double[][] matrix) {
        io.println("[[");
        for(int i = 0; i < matrix.length; i++) {
            io.print("\t");
            for(int j = 0; j < matrix[i].length; j++) {
                io.print(matrix[i][j]+ "\t\t");
            }

            io.println();
        }

        io.println("]]");
        io.println();
    }


    static void solve(double[][] matrix) {

        for(int row = 0; row < matrix.length; row++) {

            // current pivot element is 0, so we need to swap another row to get a pivot element
            if(matrix[row][row] == 0) {
                boolean couldFindSwap = swapToPivotElement(matrix, row);

                if(!couldFindSwap) {
                    if(matrix[row][matrix.length] == 0) {
                        io.println("multiple");

                    } else {
                        io.println("inconsistent");
                    }

                    return;
                }
            }

            // Normalize the current pivot element
            pivot(matrix, row);

            // Get zeroes in the other rows for that pivot
            for(int i = row + 1; i < matrix.length; i++) {
                addRow(matrix, row, i, -matrix[i][row]);
            }


        }

        // Start solving the equations
        for(int row = matrix.length - 1; row >= 0; row--) {
            // remove current row from above equations
            for(int i = row - 1; i >= 0; i--) {
                addRow(matrix, row, i, -matrix[i][row]);
            }
        }

        double[] X = new double[matrix.length];

        // get solution from the B vector (most right side column of matrix)
        for(int row = 0; row < matrix.length; row++) {
            X[row] =  matrix[row][matrix.length];
        }

        // Reverse swaps made before (in reverse order)
        for(int i = swapsMade.size() - 1; i >= 0; i--) {
            int[] swap = swapsMade.get(i);
            double tmp = X[swap[0]];
            X[swap[0]] = X[swap[1]];
            X[swap[1]] = tmp;
        }


        for(int i = 0; i < X.length; i++) {
            io.print(X[i] + " ");
        }

        io.println();
    }

    static boolean swapToPivotElement(double[][] matrix, int row) {
        for(int i = row + 1; i < matrix.length; i++) {
            if(matrix[row][i] != 0) {
                swap(matrix, row, i);
                swapsMade.add(new int[]{row, i});
                return true;
            }
        }

        // could not find solution
        return false;
    }

    static void addRow(double[][] matrix, int sourceRow, int targetRow, double mutiplier) {
        for(int i = 0; i < matrix[sourceRow].length; i++) {
            matrix[targetRow][i] += matrix[sourceRow][i] * mutiplier;
        }
    }

    static void swap(double[][] matrix, int firstRow, int secondRow) {
        double[] tmpRow = matrix[firstRow];
        matrix[firstRow] = matrix[secondRow];
        matrix[secondRow] = tmpRow;
    }

    static void pivot(double[][] matrix, int row) {
        double element = matrix[row][row];

        for(int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] = matrix[row][i] / element;
        }
    }

    static double threadHold = 0.0001; // 10^-4

    static boolean isEqual(double a, double b) {
        return Math.abs(a-b) < threadHold;
    }

    static boolean isZero(double a) {
        return isEqual(a, 0);
    }

}
