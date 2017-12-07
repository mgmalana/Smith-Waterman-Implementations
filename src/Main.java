import standard.StandardMatrixFiller;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int[][] matrix;
        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
        matrix = standardMatrixFiller.fillMatrix("GGTTGACTA", "TGTTACGG");

        Main.printMatrix(matrix);
    }

    public static void printMatrix(int [][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
