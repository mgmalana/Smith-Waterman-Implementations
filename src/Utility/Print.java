package Utility;

import java.util.Arrays;

public class Print
{
    public static void println(Object input)
    {
        System.out.println(input.toString() + "");
    }

    public static void print (Object input)
    {
        System.out.print(input.toString() + "");
    }

    public static void printMatrix(int [][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
    }
}
