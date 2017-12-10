package Utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;

import static Utility.Print.println;
import static Utility.Print.print;

public class IOMaster
{
    String fileAddress = "";
    String fileName = "sequences.txt";
    int maxLines = 2;

    public IOMaster() {
        System.out.println("Launching IOMaster to read/write files");
    }

    public IOMaster(String fileName) {
        this.fileName = fileName;
    }

    public String[] readFileToStringArray() throws Exception
    {
        String[] sequence = new String[2];
        BufferedReader br;
        int lineNumber = 0;

        try
        {
            br = new BufferedReader( new FileReader( fileName ) );

            while( lineNumber < maxLines )
            {
                sequence[lineNumber] = br.readLine();
                lineNumber++;
            }
        }
        catch ( FileNotFoundException e)
        {
            println("[ERROR] Error reading file.");
            e.printStackTrace();
        }

        return sequence;
    }

    public int[] readFileToNumList() throws Exception
    {
        int[] numList = new int[10000000];
        int content;
        BufferedReader br;
        int lineNumber = 0;
        try
        {
            br = new BufferedReader( new FileReader( fileName ) );
            while ( lineNumber < 10000000)
            {

                numList[lineNumber] = Integer.parseInt(br.readLine());
                lineNumber++;

            }
            println("\n Done reading from file.");
        }
        catch ( FileNotFoundException e)
        {
            e.printStackTrace();
        }

        return numList;
    }

    public void writeNumListToFile(int[] numlist) throws Exception
    {
        String completeFileName = fileAddress + "/" + fileName;
        PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");
//		// Write the result to file
//		writer.println(toPrint);
        for(int n: numlist)
        {
            writer.println(n);
        }
        // Close the printer
        writer.close();
    }

    public void writeStringToFile(int[][] matrix) throws Exception
    {
        PrintWriter writer = new PrintWriter(this.fileName, "UTF-8");
        String finals = Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]");
        writer.println(finals);
        writer.close();
    }

    public static class testThis
    {
        public static void main(String[] args)
        {
            String[] sequences;
            String stringA;
            String stringB;

            IOMaster ioMaster = new IOMaster();

            println("Running read test.");

            try
            {
                sequences = ioMaster.readFileToStringArray();

                for( int i = 0; i < sequences.length; i++ )
                {
                    println(sequences[i]);
                }
            }
            catch ( Exception e)
            {
                println("Error at testThis");
            }
        }
    }
}
