package MatrixFiller;

import java.util.Arrays;

import static Utility.Print.println;

public class Traceback
{
    private String localA = "";
    private String localB = "";
    private int[][] matrix;
    private int highX;
    private int highY;
    private int highVal;

    public Traceback(int[][] matrix, int highX, int highY)
    {
        this.matrix = matrix;
        this.highX = highX;
        this.highY = highY;
        this.highVal = matrix[highX][highY];
        // get value for highVal
        println("highest val: " + this.highVal + " from " + highX + "/" + highY);
    }

    public Traceback(int[][] matrix, int highX, int highY, int highVal)
    {
        this.matrix = matrix;
        this.highX = highX;
        this.highY = highY;
        this.highVal = highVal;
    }

    public String generateAlignments(String stringA, String stringB)
    {
        // to be returned
        String alignments = "";
        // original matrix
        int[][] matrix = this.matrix; // huhu
        // variables
        int currHigh = 0;
        int left, up, diag; // to be checked for highest value
        int[] values = new int[3];

        int i = this.highX;
        int j = this.highY;


        while( matrix[i][j] != 0)
        {
            if( stringA.charAt(i-1) == stringB.charAt(j-1) )
            {
                alignments = stringA.charAt(i-1) + "" + alignments;
                localA = stringA.charAt(i-1) + "" + localA;
                localB = stringB.charAt(j-1) + "" + localB;
                i--;
                j--;
                continue;
            }

            up  = matrix[i-1][j];
            left = matrix[i][j-1];
            diag = matrix[i-1][j-1];

            // populate array for getMax
            values[0] = up;
            values[1] = left;
            values[2] = diag;
            currHigh  = getMax(values);

            if(values[currHigh] == 0) {
                break;
            }

            // goes up
            if( currHigh == 0 )
            {
                i--;
                localB = "-" + localB;
                localA = stringA.charAt(i-1)+ "" + localA;
            }
            // goes left
            if( currHigh == 1)
            {
                j--;
                localA = "-" + localA;
                localB = stringB.charAt(j-1) + "" + localB;
            }
            // goes diagonally
            if( currHigh == 2)
            {
                i--;
                j--;
                localB = " " + localB;
                localA = " " + localA;
            }
        }

        println("LCS: " + alignments);
        println("localA: " + localA);
        println("localB: " + localB);
        return alignments;
    }

    public int getMax(int[] values)
    {
        int maxVal = 0;
        int maxIndex = 0;

        if (maxVal < values [0] - MatrixFiller.mismatchScore) {
            maxVal = values [0];
            maxIndex = 0;
        }
        if  (maxVal < values [1] - MatrixFiller.mismatchScore) {
            maxVal = values [1];
            maxIndex = 1;
        }
        if (maxVal < values [2] - MatrixFiller.gapPenalty) {
            maxVal = values [2];
            maxIndex = 2;
        }
        return maxIndex;
    }
}
