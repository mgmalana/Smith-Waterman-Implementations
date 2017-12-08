package MatrixFiller;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMatrixFiller extends MatrixFiller {
    private int kFinal;
    public ParallelMatrixFiller() {
        super();
    }

    public ParallelMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        super(matchScore,mismatchScore,gapPenalty);
    }

//    public int[][] fillMatrix (String stringA, String stringB) {
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
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


    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
        int stringALen = stringA.length();
        int stringBLen = stringB.length();

        int len = stringALen < stringBLen ? stringALen : stringBLen;

        Thread thread1 = new Thread(() -> {
            for(int i = kFinal; i <= stringALen; i++) {
                fillCell(matrix, i, kFinal, stringA, stringB);
            }
        });

        Thread thread2 = new Thread(() -> {
            for(int j = kFinal + 1; j <= stringBLen; j++) {
                fillCell(matrix, kFinal, j, stringA, stringB);
            }
        });
        for(int k = 1; k <= len; k++){
            kFinal = k;

            thread1.run();
            thread2.run();
        }

        return matrix;
    }

//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//
//        int len = stringALen < stringBLen ? stringALen : stringBLen;
//
//        Thread thread1 = new Thread() {
//            @Override
//            public void run() {
//
//                for(int i = kFinal; i <= stringALen; i++) {
//                    fillCell(matrix, i, kFinal, stringA, stringB);
//                }
//            }
//        };
//
//        Thread thread2 = new Thread() {
//            @Override
//            public void run() {
//                for(int j = kFinal + 1; j <= stringBLen; j++) {
//                    fillCell(matrix, kFinal, j, stringA, stringB);
//                }
//            }
//        };
//        for(int k = 1; k <= len; k++){
//            kFinal = k;
//
//            thread1.run();
//            thread2.run();
//        }
//
//        return matrix;
//    }
// two way parallel
//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(6);
//        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0
//        int stringALen = stringA.length();
//        int stringBLen = stringB.length();
//
//        int len = stringALen < stringBLen ? stringALen : stringBLen;
//
//        ArrayList<Callable<Void>> callables = new ArrayList<>();
//
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
//
//        for(int k = 1; k <= len; k++){
//            this.kFinal = k;
//
//            executorService.invokeAll(callables);
//        }
//
//        return matrix;
//    }

//    // this is the diagonal threading. not worth it magparallel huhu
//    public int[][] fillMatrix (String stringA, String stringB) throws InterruptedException {
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
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
//
//        while (diagCol <= stringBLen) {
//            int i = stringALen;
//            ArrayList<Callable<Void>> callables = new ArrayList<>();
//
//            for (int j = diagCol; j <= stringBLen; j++) { // loop in traversing the diagonal line
//                final int iFinal = i;
//                final int jFinal = j;
//
//                callables.add(new Callable<Void>() {
//                    public Void call() throws Exception {
//                        fillCell(matrix, iFinal, jFinal, stringA, stringB);
//                        return null;
//                    }
//                });
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
//
//        return matrix;
//    }
}
