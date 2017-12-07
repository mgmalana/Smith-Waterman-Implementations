package standard;

public class StandardMatrixFiller {
    private int matchScore;
    private int mismatchScore;
    private int gapPenalty;

    public StandardMatrixFiller() {
        this.matchScore = 3;
        this.mismatchScore = -3;
        this.gapPenalty = -2;
    }

    public StandardMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        this.matchScore = matchScore;
        this.mismatchScore = mismatchScore;
        this.gapPenalty = gapPenalty;
    }

    public int[][] fillMatrix (String stringA, String stringB) {
        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0

        for (int i = 1; i < matrix.length; i++) {  // starts with 1 because row 1 is all 0
            for (int j = 1; j < matrix[i].length; j++) { // starts with 1 because column 1 is all 0
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

        return matrix;
    }
}
