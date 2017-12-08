package MatrixFiller;

public abstract class MatrixFiller {
    protected int matchScore;
    protected int mismatchScore;
    protected int gapPenalty;
    /* traceback */
    private int highestValue = 0;
    private int highX = 0;
    private int highY = 0;

    public MatrixFiller() {
        this.matchScore = 3;
        this.mismatchScore = -3;
        this.gapPenalty = -2;
    }

    public MatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        this.matchScore = matchScore;
        this.mismatchScore = mismatchScore;
        this.gapPenalty = gapPenalty;
    }

    void fillCell(int[][] matrix, int i, int j, String stringA, String stringB){
        if(stringA.charAt(i - 1) == stringB.charAt(j - 1)){ // if match
            matrix[i][j] = matrix[i-1][j-1] + this.matchScore;
            updateHighScore(matrix[i-1][j-1] + this.matchScore, i, j);
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
            updateHighScore(maxScore, i, j);
        }
    }

    public void updateHighScore(int currScore, int i, int j)
    {
        /* for treysbak */
        if( highestValue < currScore)
        {
            this.highestValue = currScore;
            this.highX = i;
            this.highY = j;
        }
//        System.out.println("highestValue: " + highestValue);
//        System.out.println("maxScore: " + currScore);
    }

    public int getHighestValue() {
        return highestValue;
    }

    public int getHighX() {
        return highX;
    }

    public int getHighY() {
        return highY;
    }
}
