package MatrixFiller;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMatrixFiller extends MatrixFiller {
    public ParallelMatrixFiller() {
        super();
    }

    public ParallelMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        super(matchScore,mismatchScore,gapPenalty);
    }

    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        int stringALen = stringA.length();
        int stringBLen = stringB.length();
        int[][] matrix = new int[stringALen + 1][stringBLen + 1]; // plus 1 because of the init 0
        int diagRow = 1;
        int diagCol = 2;

        while (diagRow <= stringALen) { // loop in going down per row
            int i = diagRow;
            ArrayList<Callable<Void>> callables = new ArrayList<>();

            for (int j = 1; j <= diagRow; j++) { // loop in traversing the diagonal line
                final int iFinal = i;
                final int jFinal = j;

                callables.add(new Callable<Void>() {
                    public Void call() throws Exception {
                        fillCell(matrix, iFinal, jFinal, stringA, stringB);
                        return null;
                    }
                });
                i--;
                if (j == stringBLen) { // if already reached the edge
                    break;
                }
            }

            executorService.invokeAll(callables);
            diagRow++;
        }

        while (diagCol <= stringBLen) {
            int i = stringALen;
            ArrayList<Callable<Void>> callables = new ArrayList<>();

            for (int j = diagCol; j <= stringBLen; j++) { // loop in traversing the diagonal line
                final int iFinal = i;
                final int jFinal = j;

                callables.add(new Callable<Void>() {
                    public Void call() throws Exception {
                        fillCell(matrix, iFinal, jFinal, stringA, stringB);
                        return null;
                    }
                });

                i--;

                if(i == 0) {
                    break;
                }
            }
            executorService.invokeAll(callables);

            diagCol++;
        }


        return matrix;
    }
}
