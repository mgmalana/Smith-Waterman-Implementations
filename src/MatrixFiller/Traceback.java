package MatrixFiller;

import static Utility.Print.println;

public class Traceback
{
    private String stringA;
    private String stringB;
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
        // println("highest val: " + this.highVal + " from " + highX + "/" + highY);
    }

    public Traceback(String stringA, String stringB, int[][] matrix, int highX, int highY) {
        this.stringA = stringA;
        this.stringB = stringB;
        this.matrix = matrix;
        this.highX = highX;
        this.highY = highY;
        // get value for highVal
        this.highVal = matrix[highX][highY];
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
        int newI = 0, newJ = 0, newPos;
        int[] values = new int[3];

        int i = this.highX;
        int j = this.highY;


        while( j > 0 || i > 0)
        {
            if( i == 0 || j == 0  )
            {
                break;
            }
//            println(i + "-" + j);
//            println(stringA.charAt(i-1) + "-" + stringB.charAt(j-1));
            if( stringA.charAt(i-1) == stringB.charAt(j-1) )
            {
                alignments = stringA.charAt(i-1) + "" + alignments;
                localA = stringA.charAt(i-1) + "" + localA;
                localB = stringB.charAt(j-1) + "" + localB;
            }

            up  = matrix[i-1][j];
            left = matrix[i][j-1];
            diag = matrix[i-1][j-1];
            // populate array for getMax
            values[0] = up;
            values[1] = left;
            values[2] = diag;
            currHigh  = getMax(values);
            if ( matrix[i][j] == up || matrix[i][j] == left )
            {
                currHigh = 2;
            }
            //* goes up *//*
            if( currHigh == 0 )
            {
                newI = i - 1;
                newJ = j;
                localB = "-" + localB;
                localA = stringA.charAt(i-1)+ "" + localA;
            }
            //* goes left *//*
            if( currHigh == 1)
            {
                newI = i;
                newJ = j - 1;
                localA = "-" + localA;
                localB = stringB.charAt(j-1) + "" + localB;
            }
            //* goes diagonally *//*
            if( currHigh == 2)
            {
                newI = i - 1;
                newJ = j - 1;
            }

            i = newI;
            j = newJ;

        }

        // println("LCS: " + alignments);
        // println("localA: " + localA);
        // println("localB: " + localB);
        return alignments;
    }

    public int getMax(int[] values)
    {
        int maxVal = 0;
        int maxIndex = 0;

        for( int i = 0; i < values.length; i++ )
        {
            if( maxVal < values[i] )
            {
                maxVal = values[i];
                maxIndex = i;
            }
        }

        return maxIndex;
    }
}
