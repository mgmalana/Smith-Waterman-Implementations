package MatrixFiller;

public class ParallelMatrixFiller extends MatrixFiller {
    public ParallelMatrixFiller() {
        super();
    }

    public ParallelMatrixFiller(int matchScore, int mismatchScore, int gapPenalty) {
        super(matchScore,mismatchScore,gapPenalty);
    }

    public int[][] fillMatrix (String stringA, String stringB) {
        int stringALen = stringA.length();
        int stringBLen = stringB.length();
        int[][] matrix = new int[stringALen + 1][stringBLen + 1]; // plus 1 because of the init 0
        int diagRow = 1;
        int diagCol = 2;

        while (diagRow <= stringALen) { // loop in going down per row
            int i = diagRow;

            for (int j = 1; j <= diagRow; j++) { // loop in traversing the diagonal line
                matrix[i][j] = diagRow;
                i--;
                if (j == stringBLen) { // if already reached the edge
                    break;
                }
            }
            diagRow++;
        }

        while (diagCol <= stringBLen) {
            int i = stringALen;

            for (int j = diagCol; j <= stringBLen; j++) { // loop in traversing the diagonal line
                matrix[i][j] = diagCol;
                i--;

                if(i == 0) {
                    break;
                }
            }
            diagCol++;
        }


        return matrix;
    }
}
