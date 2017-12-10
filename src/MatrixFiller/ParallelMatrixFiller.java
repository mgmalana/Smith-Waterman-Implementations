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

//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException, ExecutionException {
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//
//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                ColTraverser colTraverser = new ColTraverser();
//                try {
//                    colTraverser.fillColumns(matrix, stringA, stringB);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                RowTraverser rowTraverser = new RowTraverser();
//                try {
//                    rowTraverser.fillRows(matrix,stringA,stringB);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t1.start();
//        t2.start();
//
//        t1.join();
//        t2.join();
//
//        return matrix;
//    }
//    public int[][] fillMatrixTwoWayPool (String stringA, String stringB) throws InterruptedException, ExecutionException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//        Vector<Future> futures = new Vector<>();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i < matrix.length; i++) { // starts with 1 because column 1 is all 0
//                    fillCell(matrix, i, jFinal, stringA, stringB);
//                }
//                jFinal++;
//            }
//        };
//        for(int j = 1; j < matrix[0].length; j++) {
//            fillCell(matrix, 1, j, stringA, stringB);
//            futures.add(executorService.submit(runnable));
//        }
//
//        //to wait for everyone to finish
//        for (int k = 0; k < futures.size(); k++) {
//            futures.get(k).get();
//        }
//
//        executorService.shutdown();
//
//        return matrix;
//    }


// v1.0: With forking
//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException, ExecutionException {
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        ExecutorService executorService = Executors.newFixedThreadPool(16);
//        int forkAtIndex = stringB.length() < 200 ? stringB.length() : stringB.length() / 3; // either 1 or 1/3 of stringB len
//        Vector<Future> futures = new Vector<>();
//
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                int i = iFinal;
//                for (int j = 1; j < matrix[i].length; j++) { // starts with 1 because column 1 is all 0
//                    fillCell(matrix, i, j, stringA, stringB);
//                    if(j == forkAtIndex) { // if current column is at the fork index
//                        iFinal++;
//                        if(iFinal < matrix.length){ // check if next row is within matrix' n rows
//                            try {
//                                futures.add(executorService.submit(this));
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                }
//            }
//        };
//
//        futures.add(executorService.submit(runnable)); // wait to finish all threads
//
//        for (int k = 0; k < futures.size(); k++) {
//            futures.get(k).get();
//        }
//
//        executorService.shutdown();
//        return matrix;
//    }

// v2.0 Per line multithreading
//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        for (int i = 1; i < matrix.length; i++) {  // starts with 1 because row 1 is all 0
//            int iFinal = i;
//            Runnable runnable = new Runnable() {
//                @Override
//                public void run() {
//                    for (int j = 1; j < matrix[iFinal].length; j++) { // starts with 1 because column 1 is all 0
//                        fillCell(matrix, iFinal, j, stringA, stringB);
//                    }
//                }
//            };
//            executorService.submit(runnable);
//        }
//
//        return matrix;
//    }

//  v3.0 Two-way parallel
//    public int[][] fillMatrixTwoWayVanilla (String stringA, String stringB) throws InterruptedException {
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//
//        int len = stringALen < stringBLen ? stringALen : stringBLen;
//
//        Runnable r1 = new Runnable() {
//            @Override
//            public void run() {
//
//                for(int i = kFinal; i <= stringALen; i++) {
//                    fillCell(matrix, i, kFinal, stringA, stringB);
//                }
//            }
//        };
//
//        Runnable r2 = new Runnable() {
//            @Override
//            public void run() {
//                for(int j = kFinal + 1; j <= stringBLen; j++) {
//                    fillCell(matrix, kFinal, j, stringA, stringB);
//                }
//            }
//        };
//        for(int k = 1; k <= len; k++){
//            kFinal = k;
//            Thread t1 = new Thread(r1);
//            t1.start();
//            Thread t2 = new Thread(r2);
//            t2.start();
//            t1.join();
//            t2.join();
//        }
//
//        return matrix;
//    }
//
//// v3.1 Threadpool version of v3.0
//    public int[][] fillMatrixTwoWayPool (String stringA, String stringB) throws InterruptedException, ExecutionException {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//
//        int len = stringALen < stringBLen ? stringALen : stringBLen;
//
//        List<Callable<Void>> callables = new ArrayList<>();
//        callables.add(new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                for(int i = kFinal; i <= stringALen; i++) {
//                    fillCell(matrix, i, kFinal, stringA, stringB);
//                }
//                return null;
//            }
//        });
//
//        callables.add(new Callable<Void>() {
//            @Override
//            public Void call() throws Exception {
//                for(int j = kFinal + 1; j <= stringBLen; j++) {
//                    fillCell(matrix, kFinal, j, stringA, stringB);
//                }
//                return null;
//            }
//        });
//        for(int k = 1; k <= len; k++){
//            this.kFinal = k;
//            executorService.invokeAll(callables);
//        }
//
//        executorService.shutdown();
//        return matrix;
//    }

//    v4.0 this is the diagonal threading. not worth it magparallel huhu
//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//        int[][] matrix = new int[stringALen + 1][stringBLen + 1]; // plus 1 because of the init 0
//        int diagRow = 1;
//        int diagCol = 2;
//
//        while (diagRow <= stringALen) { // loop in going down per row
//            int i = diagRow;
//            ArrayList<Callable<Void>> callables = new ArrayList<>();
//
//            for (int j = 1; j <= diagRow; j++) { // loop in traversing the diagonal line
//                final int iFinal = i;
//                final int jFinal = j;
//
//                callables.add(new Callable<Void>() {
//                    public Void call() throws Exception {
//                        fillCell(matrix, iFinal, jFinal, stringA, stringB);
//                        return null;
//                    }
//                });
//                i--;
//                if (j == stringBLen) { // if already reached the edge
//                    break;
//                }
//            }
//
//            executorService.invokeAll(callables);
//            diagRow++;
//        }
//        while (diagCol <= stringBLen) {
//            int i = stringALen;
//            ArrayList<Callable<Void>> callables = new ArrayList<>();
//
//            for (int j = diagCol; j <= stringBLen; j++) { // loop in traversing the diagonal line
//                final int iFinal = i;
//                final int jFinal = j;
//
//                long start =  System.nanoTime();
//                callables.add(new Callable<Void>() {
//                    public Void call() throws Exception {
//                        fillCell(matrix, iFinal, jFinal, stringA, stringB);
//                        return null;
//                    }
//                });
//
//                totalTime += (System.nanoTime() - start);
//
//                i--;
//
//                if(i == 0) {
//                    break;
//                }
//            }
//            executorService.invokeAll(callables);
//
//            diagCol++;
//        }
//
//        System.out.println("time: " + this.totalTime);
//        return matrix;
//    }

    //v4.1 diagonal threading but with groups
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

        int numRowCellsToFill = matrix[0].length - colMod; // used for edge cells
        int colOffset = 0;
        int rowOffset = 0;

        while (diagRowGroup < NUM_THREADS) { // loop in going down per row
            int iGroup = diagRowGroup;
            Vector<Future> futures = new Vector<>();

            // loop in traversing groups diagonally
            for (int jGroup = 0; jGroup <= diagRowGroup; jGroup++) {
                if (jGroup == NUM_THREADS - 1 && colMod != 0){ // If at the last jgroup
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

//        // for edges
//        if (colMod != 0) {
//            for (int i = 1; i < matrix.length; i++) {
//                for (int j = matrix[0].length - colMod; j < matrix[0].length; j++) {
//                    fillCell(matrix, i, j, stringA, stringB);
//                }
//            }
//        }
//        if (rowMod != 0) {
//            for (int i = matrix.length - rowMod; i < matrix.length; i++) {
//                for (int j = 1; j < numRowCellsToFill; j++) {
//                    fillCell(matrix, i, j, stringA, stringB);
//                }
//            }
//        }

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
