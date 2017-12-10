import MatrixFiller.ParallelMatrixFiller;
import MatrixFiller.StandardMatrixFiller;
import MatrixFiller.Traceback;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Traceback traceback;

//        String stringA = "ABCDEFG";
//        String stringB = "ABCDEFGH";

        String stringA = "ABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASDABCDEFFGJIJDSKADKSKDSAKDKSAKDSAKDSAKDAKSdSADKDKASDKSAKDKASKDASDKSAKDKSAKDASKDAJDJASJDASJDJASJDASJDJCMMCADSAKDASKDASKDASKDASKDADJSAJDSAJDJASJDASJDJASFIJSDAFJDSAFOKDSAOFJKDSAOFJASFJASD";
        String stringB = "GGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTGACTAGGTTG";


        long start;
        long end;

        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
        start = System.nanoTime();
        int[][] matrix1 = standardMatrixFiller.fillMatrix(stringA, stringB);
        end = System.nanoTime();
        System.out.println("Standard Matrix Filler Time: " + (end - start));

        ParallelMatrixFiller parallelMatrixFiller = new ParallelMatrixFiller();
        start = System.nanoTime();
        int[][] matrix2 = parallelMatrixFiller.fillMatrix(stringA, stringB);
        end = System.nanoTime();
        System.out.println("Parallel Matrix Filler Time: " + (end - start));

//        System.out.println("matrix1: ");
//        Imp2.printMatrix(matrix1);
//        System.out.println("matrix2: ");
//        Imp2.printMatrix(matrix2);

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
