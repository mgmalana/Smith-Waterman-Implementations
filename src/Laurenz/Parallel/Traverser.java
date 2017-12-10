package Laurenz.Parallel;

import static Utility.Print.printMatrix;
import static Utility.Print.println;

public class Traverser extends Thread
{
    private int[][] originalMatrix;
    private int[][] matrix;
    private int x;
    private int y;
    /* Strings */
    private String stringA;
    private String stringB;
    /* direction */
    private char direction;
    /* i offset */
    int iOff = 1;

    public Traverser(String name, int[][] originalMatrix, int x, int y, String stringA, String stringB, char direction) {
        super(name);
        this.originalMatrix = originalMatrix;
        this.x = x;
        this.y = y;
        this.stringA = stringA;
        this.stringB = stringB;
        this.direction = direction;
    }

    public void run()
    {
        Cell cell;
        try
        {
            if( direction == 'd' )
            {
                while( x <= stringA.length() )
                {
                    for( int i = iOff; i < stringA.length(); i++ )
                    {
                        cell = new Cell(x, i, originalMatrix, stringA, stringB);
                        int temp = cell.computeCellScore();

                        println("ix " + i + " " + x + " " + temp);
//                        printMatrix(originalMatrix);
                        println("");
                    }
                    x++;
                    iOff++;
                }
            }
            if( direction == 'r' )
            {

                while( y <= stringB.length() )
                {
                    for( int i = iOff; i <= stringB.length(); i++)
                    {
                        cell = new Cell(i, y, originalMatrix, stringA, stringB);
                        cell.computeCellScore();
                        int temp = cell.computeCellScore();
                        println("iy " + i + " " + y + " " + temp);
//                        printMatrix(originalMatrix);
                        println("");
                    }
                    y++;
                    iOff++;
                }
                println("last y = " + y + "| last iOff = " + iOff);
            }
            println("FINAL: " + x + "-" + y);
            printMatrix(originalMatrix);
            println("");
        }
        catch (Exception e)
        {

        }
    }
}
