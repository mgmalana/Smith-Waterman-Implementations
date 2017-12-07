public abstract class MatrixFiller {
    protected int matchScore;
    protected int mismatchScore;
    protected int gapPenalty;

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
}
