package Laurenz.Parallel;

import static Utility.Print.println;

public class Cell
{
    /* current cell coordinates */
    private int x;
    private int y;
    /* */
    private String stringA;
    private String stringB;
    private int[][] matrix;
    /* score for the current cell */
    private int cellScore;
    /* Constant variables */
    private final int matchScore = 3;
    private final int mismatchScore = -3;
    private final int gapPenalty = -2;

    public Cell(int x, int y, int[][] matrix, String stringA, String stringB) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
        this.stringB = stringB;
        this.stringA = stringA;
    }

    public void placeRandIntoCell()
    {
        this.matrix[x][y] = 99;
    }

    public int computeCellScore()
    {
        int score = 0;
        int maxPossibleScore = 0;
        int pDiag = 0;
        int pUp = 0;
        int pLeft = 0;

        /* If letters match */
        if( stringA.charAt(x - 1) == stringB.charAt(y - 1) )
        {
            score = matrix[x-1][y-1] + matchScore;
        }
        /* otherwise */
        else
        {
            pDiag = getDiagScore() + mismatchScore;
            pLeft = getLeftScore() + gapPenalty;
            pUp = getUpScore() + gapPenalty;

            if( pDiag > maxPossibleScore ) {
                maxPossibleScore = pDiag;
            }
            if( pUp > maxPossibleScore ) {
                maxPossibleScore = pUp;
            }
            if( pLeft > maxPossibleScore ) {
                maxPossibleScore = pLeft;
            }

            score = maxPossibleScore;
        }
        matrix[x][y] = score;
        this.cellScore = score;
        return score;
    }

    public int getMaxScore()
    {
        int currHigh = 0;

        return currHigh;
    }

    public int getLeftScore()
    {
        if( x > 0)
            return this.matrix[x-1][y];
        else
            return 0;
    }

    public int getUpScore()
    {
        if( y > 0 )
            return this.matrix[x][y-1];
        else
            return 0;
    }

    public int getDiagScore()
    {
        if( x > 0 && y > 0 )
            return this.matrix[x-1][y-1];
        else
            return 0;
    }

}
