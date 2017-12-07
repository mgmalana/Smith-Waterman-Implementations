package MatrixFiller;

public class StandardMatrixFiller extends MatrixFiller{
    public StandardMatrixFiller() {
        super();
    }

    public StandardMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        super(matchScore,mismatchScore,gapPenalty);
    }

    public int[][] fillMatrix (String stringA, String stringB) {
        int[][] matrix = new int[stringA.length() + 1][stringB.length() + 1]; // plus 1 because of the init 0

        for (int i = 1; i < matrix.length; i++) {  // starts with 1 because row 1 is all 0
            for (int j = 1; j < matrix[i].length; j++) { // starts with 1 because column 1 is all 0
                fillCell(matrix, i, j, stringA, stringB);
            }
        }

        return matrix;
    }
}
