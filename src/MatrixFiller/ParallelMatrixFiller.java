package parallel;

public class ParallelMatrixFiller {
    private int matchScore;
    private int mismatchScore;
    private int gapPenalty;

    public ParallelMatrixFiller() {
        this.matchScore = 3;
        this.mismatchScore = -3;
        this.gapPenalty = -2;
    }

    public ParallelMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        this.matchScore = matchScore;
        this.mismatchScore = mismatchScore;
        this.gapPenalty = gapPenalty;
    }

    public int[][] fillMatrix (String stringA, String stringB) {
        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0



        return matrix;
    }
}
