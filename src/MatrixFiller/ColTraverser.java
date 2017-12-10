package MatrixFiller;

import com.sun.rowset.internal.Row;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ColTraverser {
    protected int matchScore;
    protected int mismatchScore;
    protected int gapPenalty;

    public ColTraverser() {
        this.matchScore = 3;
        this.mismatchScore = -3;
        this.gapPenalty = -2;
    }
    public static final Semaphore semaphore = new Semaphore(0);

    public void fillColumns(int[][] matrix, String stringA, String stringB) throws InterruptedException {
        int stringALen = stringA.length();
        int stringBLen = stringB.length();

        int len = stringALen < stringBLen ? stringALen : stringBLen;

        for(int k = 1; k <= len; k++){
            if(len - k < 6000) {
                for (int i = k; i < matrix.length; i++) {  // starts with 1 because row 1 is all 0
                    for (int j = k; j < matrix[i].length; j++) { // starts with 1 because column 1 is all 0
                        fillCell(matrix, i, j, stringA, stringB);
                    }
                }
                break;
            }
            fillCell(matrix, k, k, stringA,stringB);
            semaphore.release();
            for(int j = k + 1; j <= stringBLen; j++) {
//                System.out.println("col " + k);
                fillCell(matrix, k, j, stringA, stringB);
            }

            semaphore.release();
            RowTraverser.semaphore.acquire();
        }

    }

    void fillCell(int[][] matrix, int i, int j, String stringA, String stringB){
        if(stringA.charAt(i - 1) == stringB.charAt(j - 1)){ // if match
            matrix[i][j] = matrix[i-1][j-1] + this.matchScore;
        } else { // if mismatch
            int maxScore = 0;
            int mismatchScore = matrix[i-1][j-1] + this.mismatchScore;
            int gapDeletionScore = matrix[i-1][j] + this.gapPenalty; // < from left
            int gapInsertionScore = matrix[i][j-1] + this.gapPenalty; // ^ from up

            if (mismatchScore > maxScore) {
                maxScore = mismatchScore;
            }
            if (gapDeletionScore > maxScore) {
                maxScore = gapDeletionScore;
            }
            if (gapInsertionScore > maxScore) {
                maxScore = gapInsertionScore;
            }

            matrix[i][j] = maxScore;
        }
    }
}
