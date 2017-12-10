package MatrixFiller;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;

public class ParallelMatrixFiller extends MatrixFiller {
    private int kFinal;
    private int iFinal = 1;
    private int jFinal = 1;

    private final static int NUM_THREADS = 12;

    public ParallelMatrixFiller() {
        super();
    }
    public ParallelMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        super(matchScore, mismatchScore, gapPenalty);
    }

    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);

        int stringALen = stringA.length();
        int stringBLen = stringB.length();
        int numCellsPerRowGroup = stringALen / NUM_THREADS;
        int numCellsPerColGroup = stringBLen / NUM_THREADS;

        int[][] matrix = new int[stringALen + 1][stringBLen + 1]; // plus 1 because of the init 0
        int diagRowGroup = 0;
        int diagColGroup = 1;

        int rowMod = stringALen % NUM_THREADS;  // used for edge cells
        int colMod = stringBLen % NUM_THREADS; // used for edge cells

        int colOffset = 0;
        int rowOffset = 0;

        while (diagRowGroup < NUM_THREADS) { // loop in going down per row
            int iGroup = diagRowGroup;
            Vector<Future> futures = new Vector<>();

            // loop in traversing groups diagonally
            for (int jGroup = 0; jGroup <= diagRowGroup; jGroup++) {
                if (jGroup == NUM_THREADS - 1 && colMod != 0){ // If at the last jgroup and if there's offset
                    colOffset = colMod;
                }
                if (jGroup == 0 && rowMod != 0 && diagRowGroup == NUM_THREADS - 1){
                    rowOffset = rowMod;
                }

                final int iGroupFinal = iGroup;
                final int jGroupFinal = jGroup;
                final int diagRowGroupFinal = diagRowGroup;
                final int numCellsRowGroupFinal = numCellsPerRowGroup + rowOffset;
                final int numCellsColGroupFinal = numCellsPerColGroup + colOffset;

                futures.add(executorService.submit( new Runnable() {
                    @Override
                    public void run() {
                        // Grouping cells for multithreading
                        for(int i = 1; i <= numCellsRowGroupFinal; i++){ // relative (to the group) i
                            for(int j = 1; j <= numCellsColGroupFinal; j++){ // relative (to the group) j
//                                matrix[iGroupFinal * numCellsPerRowGroup + i][jGroupFinal * numCellsPerColGroup + j] = diagRowGroupFinal;
                                fillCell(matrix, iGroupFinal * numCellsPerRowGroup + i, jGroupFinal * numCellsPerColGroup + j, stringA, stringB);
                            }
                        }
                    }
                }));
                iGroup--;
            }
            waitForFuturesToFinish(futures);

            diagRowGroup++;
        }
        while (diagColGroup < NUM_THREADS) {
            int iGroup = NUM_THREADS;
            Vector<Future> futures = new Vector<>();

            for (int jGroup = diagColGroup; jGroup < NUM_THREADS; jGroup++) {

                final int iGroupFinal = iGroup;
                final int jGroupFinal = jGroup;
                final int diagColGroupFinal = diagColGroup;
                final int numCellsRowGroupFinal = numCellsPerRowGroup + rowOffset;
                final int numCellsColGroupFinal = numCellsPerColGroup + colOffset;


                futures.add(executorService.submit( new Runnable() {
                    @Override
                    public void run() {
                        for(int i = 1; i <= numCellsRowGroupFinal; i++){ // relative (to the group) i
                            for(int j = 1; j <= numCellsColGroupFinal; j++){ // relative (to the group) j
//                                matrix[(iGroupFinal - 1) * numCellsPerRowGroup + i][jGroupFinal * numCellsPerColGroup + j] = diagColGroupFinal;
                                fillCell(matrix, (iGroupFinal - 1) * numCellsPerRowGroup + i, jGroupFinal * numCellsPerColGroup + j, stringA, stringB);
                            }
                        }
                    }
                }));

                iGroup--;

                if(iGroup == 0) { // if nasa edge na
                    break;
                }
            }

            waitForFuturesToFinish(futures);
            diagColGroup++;
        }

        executorService.shutdown();
        return matrix;
    }

    private void waitForFuturesToFinish(Vector <Future> futures) throws ExecutionException, InterruptedException {
        //to wait for everyone to finish
        for (int k = 0; k < futures.size(); k++) {
            futures.get(k).get();
        }
    }

}
