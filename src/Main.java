import MatrixFiller.ParallelMatrixFiller;
import MatrixFiller.StandardMatrixFiller;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] matrix;
//        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
//        matrix = standardMatrixFiller.fillMatrix("GGTTGACTA", "TGTTACGG");

//        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller(5, -3, -4);
//        matrix = standardMatrixFiller.fillMatrix("GACTTAC", "CGTGAATTCAT");

        ParallelMatrixFiller parallelMatrixFiller = new ParallelMatrixFiller();
        matrix = parallelMatrixFiller.fillMatrix("1", "12");


        Main.printMatrix(matrix);
    }

    public static void printMatrix(int [][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
