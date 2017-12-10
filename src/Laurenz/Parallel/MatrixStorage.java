package Laurenz.Parallel;

import static Utility.Print.printMatrix;

/**
 * Will be updated by all threads
 */
public class MatrixStorage
{
    private int[][] matrix;
    private String stringA, stringB;
    private int stringALen, stringBLen;
    private int finishedThreads = 0;
    private int offsetX = 1;
    private int offsetY = 1;

    public MatrixStorage(String stringA, String stringB)
    {
        this.matrix = new int[ stringA.length() + 1 ][ stringB.length() + 1 ];
        this.stringA = stringA;
        this.stringB = stringB;
    }

    public void updateMatrixTableCellValue(int x, int y, int value)
    {
        this.matrix[x][y] = value;

    }

    public int getMatrixTableCellValue(int x, int y)
    {
        return this.matrix[x][y];
    }

    public char getLetterAtA(int index)
    {
        return stringA.charAt(index - 1);
    }

    public char getLetterAtB(int index)
    {
        return stringB.charAt(index - 1);
    }

    public int getStringALen()
    {
        this.stringALen = stringA.length();
        return this.stringALen;
    }

    public int getStringBLen()
    {
        this.stringBLen = stringB.length();
        return this.stringBLen;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public synchronized void incrementFinishedThreads()
    {
        this.finishedThreads++;
    }

    public int getFinishedThreads() {
        return finishedThreads;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void incrementOffsetX()
    {
        this.offsetX++;
    }

    public void incrementOffsetY()
    {
        this.offsetY++;
    }

    public void resetFinishedThreads()
    {
        this.finishedThreads = 0;
    }

    public String getStringA() {
        return stringA;
    }

    public String getStringB() {
        return stringB;
    }
}
