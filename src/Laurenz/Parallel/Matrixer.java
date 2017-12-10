package Laurenz.Parallel;

import static Utility.Print.*;

public class Matrixer
{

    int[][] resultMatrix;

    public int[][] buildInitialMatrix(String stringA, String stringB)
    {
        int[][] resultingMatrix;
        /* Create the initial size of the matrix (note plus one due to 0 col row) */
        resultingMatrix = new int[ stringA.length() + 1 ][ stringB.length() + 1 ];
//        Cell cell = new Cell(1,1,resultingMatrix, stringA, stringB);
//        cell.computeCellScore();
        /* set starting for now as 1,1 */
        return resultingMatrix;
    }

    public void runMe(String stringA, String stringB)
    {
        int[][] matrix;
        matrix = buildInitialMatrix(stringA, stringB);
//        printMatrix(matrix);
        println("");
//            Cell cell = new Cell(1,1, matrix, stringA, stringB);
//            cell.placeRandIntoCell();
//            printMatrix(matrix);
        Traverser t1 = new Traverser("right", matrix, 1,1,stringA,stringB,'r');
        Traverser t2 = new Traverser("down", matrix, 1,2, stringA, stringB, 'd');
        t2.start();
        t1.start();

//        resultMatrix = t2.getOriginalMatrix();
    }

    public int[][] getResultMatrix() {
        return resultMatrix;
    }

    private static class testMe
    {
        public static void main(String[] args)
        {
            Matrixer matrixer = new Matrixer();
            String stringA = "GGTTGACTA";
            String stringB = "TGTTACGG";
            int[][] matrix;
            matrix = matrixer.buildInitialMatrix(stringA, stringB);
            printMatrix(matrix);
            println("");
            Traverser t1 = new Traverser("right", matrix, 1,1,stringA,stringB,'r');
            Traverser t2 = new Traverser("down", matrix, 1,2, stringA, stringB, 'd');
            t1.start();
            t2.start();

        }
    }
}
