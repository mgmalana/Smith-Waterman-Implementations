import MatrixFiller.ParallelMatrixFiller;
import MatrixFiller.StandardMatrixFiller;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        long start;
        long end;

        start = System.nanoTime();
        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
        int[][] matrix1 = standardMatrixFiller.fillMatrix("DSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFF",
                "DSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFF");
        end = System.nanoTime();
        System.out.println("Standard Matrix Filler Time: " + (end - start));

        start = System.nanoTime();
        ParallelMatrixFiller parallelMatrixFiller = new ParallelMatrixFiller();
        int[][] matrix2 = parallelMatrixFiller.fillMatrix("DSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFF",
                "DSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFFDSADASDFFFFFSDSADSADASDADSADSADSDFFF");
        end = System.nanoTime();
        System.out.println("Parallel Matrix Filler Time: " + (end - start));

//        System.out.println("matrix1: ");
//        Main.printMatrix(matrix1);
//        System.out.println("matrix2: ");
//        Main.printMatrix(matrix2);

        System.out.println("Is Matrix equal: " + Main.isMatrixSame(matrix1,matrix2));

//        start = System.nanoTime();
//        ParallelMatrixFiller parallelMatrixFiller = new ParallelMatrixFiller();
//        matrix = parallelMatrixFiller.fillMatrix("GGTTGACTA",
//                "GGTTGACTA");
//        end = System.nanoTime();

//        System.out.println("Parallel Matrix Filler Time: " + (end - start));

//        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller(5, -3, -4);
//        matrix = standardMatrixFiller.fillMatrix("GACTTAC", "CGTGAATTCAT");
    }

    public static void printMatrix(int [][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }

    public static boolean isMatrixSame(int[][] matrix1, int[][] matrix2) {
        for (int i = 1; i < matrix1.length; i++) {  // starts with 1 because row 1 is all 0
            for (int j = 1; j < matrix1[i].length; j++) { // starts with 1 because column 1 is all 0
                if(matrix1[i][j] != matrix2[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
}
