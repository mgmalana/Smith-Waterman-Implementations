package Laurenz.Parallel;

import Utility.IOMaster;
import Laurenz.standard.StandardMatrixFiller;

import static Utility.Print.*;

public class Cell extends Thread
{
    MatrixStorage ms;
    /* Variables for traversing */
    int start, end;
    int startX = 1, startY = 1;
    int endX, endY;
    int offset = 1;
    char direction;
    /* Constant variables */
    private final int matchScore = 3;
    private final int mismatchScore = -3;
    private final int gapPenalty = -2;



    public Cell(MatrixStorage matrixStorage, int start, int end)
    {
        this.ms = matrixStorage;
        this.start = start;
        this.end = end;
    }

    public Cell(MatrixStorage ms, int startX, int startY, int endX, int endY)
    {
        this.ms = ms;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public Cell(MatrixStorage ms)
    {
        this.ms = ms;
    }

    public Cell(String name, MatrixStorage ms, int start, int end, char direction)
    {
        super(name);
        this.ms = ms;
        this.start = start;
        this.end = end;
        this.direction = direction;
    }

    public int computeCellScore(int x, int y)
    {
        int score = 0;
        int maxPossibleScore = 0;
        int pDiag = 0;
        int pUp = 0;
        int pLeft = 0;

        /* if both letters match, add 3 to the score + diag value */
        if ( ms.getLetterAtA(x) == ms.getLetterAtB(y))
        {
            score = ms.getMatrixTableCellValue(x - 1,y - 1) + matchScore;
        }
        else
        {
            pDiag   = ms.getMatrixTableCellValue(x - 1,y - 1 ) + mismatchScore;
            pLeft   = ms.getMatrixTableCellValue(x, y -1 ) + gapPenalty;
            pUp     = ms.getMatrixTableCellValue(x - 1, y ) + gapPenalty;

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

        return score;
    }

    public void run3()
    {
        try
        {

        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    /* second version boi */
    public void run2()
    {
        long startTime = System.currentTimeMillis();
        int tempVal;
        try
        {

//            if( ms.getFinishedThreads() == 2) {
//                currentThread().interrupt();
//                println("interrupted");
//                return;
//            }
            if( direction == 'r' )
            {
//                println("r ran");
                for( int i = start; i <= ms.getStringALen(); i++ )
                {
                    tempVal = computeCellScore(i, ms.getOffsetY());
                    ms.updateMatrixTableCellValue(i, ms.getOffsetY(), tempVal);

                }
//                ms.incrementOffsetY();
            }
            else
            {
//                println("d ran");
                for( int i = start; i <= ms.getStringBLen(); i++ )
                {
                    tempVal = computeCellScore(ms.getOffsetX(), i);
                    ms.updateMatrixTableCellValue(ms.getOffsetX(), i, tempVal);
                }
            }
/*
            for( int i = start; i <= end; i++ )
            {
                if( this.direction == 'r')
                {
                    tempVal = computeCellScore(i, ms.getOffsetY());
                    ms.updateMatrixTableCellValue(i, ms.getOffsetY(), tempVal);
                    ms.incrementOffsetY();
                }
                else
                {
                    tempVal = computeCellScore(ms.getOffsetX(), i);
                    ms.updateMatrixTableCellValue(ms.getOffsetX(), i, tempVal);
                    ms.incrementOffsetX();
                }
            }*/
            if(ms.getOffsetY() < ms.getStringBLen() && direction == 'r')
            {
//                println("y adds");
                ms.incrementOffsetY();
                ms.incrementFinishedThreads();
//                if(ms.getFinishedThreads() == 2)
//                {
//                    println("shit1");
//                    this.interrupt();
//                    currentThread().interrupt();
//                    return;
//
//                }
            }
            if(ms.getOffsetX() < ms.getStringALen() && direction != 'r' )
            {
//                println("x adds");
                ms.incrementOffsetX();
                ms.incrementFinishedThreads();
//                if(ms.getFinishedThreads() == 2)
//                {
//                    println("shit2");
//                    this.interrupt();
//                    currentThread().interrupt();
//                    return;
//                }
            }


        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }
//        printMatrix(ms.getMatrix());
//        println("increment by the cell: " + ms.getFinishedThreads());
    }


    public static void printToFile(String fileName, int[][] matrix) throws Exception
    {
        IOMaster ioMaster = new IOMaster(fileName);
        try {
            ioMaster.writeStringToFile(matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void run()
    {
        long startTime = System.currentTimeMillis();
        int tempVal;
        try {
            if (startY == 0)
            {
                for (int offset = 1; offset <= endY; offset++) {
                    for (int i = startX; i <= endX; i++) {
                        tempVal = computeCellScore(i, offset);
                        ms.updateMatrixTableCellValue(i, offset, tempVal);
                    }
                    startX++;
                }
            }
            if( startX == 0)
            {
                for( int offset = 1; offset <= endX; offset++)
                {
                    for( int i = startY; i <= endY; i++ )
                    {
                        tempVal = computeCellScore(offset, i);
                        ms.updateMatrixTableCellValue( offset, i, tempVal);
                    }
                }
            }

        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
        ms.incrementFinishedThreads();

        if( ms.getFinishedThreads() == 2 )
        {
            long endTime   = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("ParallelRun: Time: " + totalTime);
            println("Finished all " + ms.getFinishedThreads() + " thread(s) ");
//            printMatrix( ms.getMatrix() );

//            int[][] testMe = runSingle(ms.getStringA(), ms.getStringB());
//            isMatrixSame(testMe, ms.getMatrix());
//            try
//            {
//                printToFile("parallel2.txt", ms.getMatrix());
//            }
//            catch ( Exception e)
//            {
//                e.printStackTrace();
//            }
        }
    }


    public static boolean isMatrixSame(int[][] matrix1, int[][] matrix2) {
        for (int i = 1; i < matrix1.length; i++) {  // starts with 1 because row 1 is all 0
            for (int j = 1; j < matrix1[i].length; j++) { // starts with 1 because column 1 is all 0
                if(matrix1[i][j] != matrix2[i][j]){
                    return false;
                }
            }
        }

        return true;
    }
    public static int[][] runSingle(String stringA, String stringB)
    {
        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
        /*
         * Single version
         * */
        /* Populate matrix */
        long startTime = System.currentTimeMillis(); /* time thingy */
        int[][] matrix = standardMatrixFiller.fillMatrix(stringA, stringB);

        /* timer thingy */
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("SingleRun: Time: " + totalTime);
//        matrix1 = matrix;
//        printMatrix(matrix);
        try {
            printToFile("single.txt", matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matrix;
    }

    public MatrixStorage getMs() {
        return ms;
    }
}



    /*
    public void run()
    {
        try
        {
            for( offset = 1; offset <= ms.getStringBLen(); offset++ )
            {
                for( int i = start; i <= ms.getStringALen(); i++ )
                {
                    ms.updateMatrixTableCellValue(i, offset, end);

                }
                start++;
            }
            println("");
            printMatrix( ms.getMatrix() );

        }
        catch ( Exception e)
        {
            e.printStackTrace();
        }
    }
}
*/
