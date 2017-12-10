package Laurenz;

import Laurenz.Parallel.MatrixStorage;
import Laurenz.Parallel.Matrixer;
import Laurenz.Parallel.Matrixer2;
import Utility.IOMaster;
import Laurenz.standard.MagicalTraceback;
import Laurenz.standard.StandardMatrixFiller;

import java.util.Arrays;

import static Utility.Print.printMatrix;
import static Utility.Print.println;

public class Imp2 {

    private static int[][] matrix1;
    private static int[][] matrix2;

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
        matrix1 = matrix;
//        printMatrix(matrix);
//        try {
//            printToFile("single.txt", matrix);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return matrix;
    }

    public static void runParallel3(String stringA, String stringB)
    {
        Matrixer2 m2 = new Matrixer2(stringA, stringB);
        m2.runMatrixer2B();

    }

    public static void runParallel2(String stringA, String stringB)
    {
        MatrixStorage ms = new MatrixStorage(stringA, stringB);
        Matrixer2 m2 = new Matrixer2(ms);
        m2.runMatrixer2();
//        try
//        {
//            printToFile("parallel2.txt", m2.getMs().getMatrix());
//        }
//        catch ( Exception e)
//        {
//            e.printStackTrace();
//        }
        matrix2 = m2.getMs().getMatrix();
    }

    public static void runParallel(String stringA, String stringB)
    {
        long startTime = System.currentTimeMillis();
        /* parallel version */
        Matrixer matrixer = new Matrixer();
        matrixer.runMe(stringA, stringB);
        int[][] matrix = matrixer.getResultMatrix();
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        /*try {
            printToFile(matrix);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
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

    public static void main(String[] args)
    {

        MagicalTraceback magicalTraceback;
        IOMaster ioMaster = new IOMaster();
        String[] sequences = new String[2];
        String stringA, stringB;
        int[][] matrix;

//        stringA = "GGTCATGTTTCCGTTGTAGGAGTGAACCCACTTGCCTTTGCGTCTTAATACCAATGAAAAACCTATGCACTTTGTACAGGGTACCATCGGGATTCTGAACCCTCAGATAGTGGGGATCCCGGGTATAGACCTTTATCTGCGGTCCAACTTAGGCATAAACCTCCATGCTACCTTGTCAGACCCACCCTGCACGAGGTAAATATGGGACGCGTCCGACCTGGCTCCTGGCGTTCTACGCCGCCACGTGTTCGTTAACTGTTGATTGGTAGCACAAAAGTAATACCATGGTCCTTGAAATTCGGCTCAGTTAGTTCGAGCGTAATGTCACAAATGGCGCAGAACGGCAATGAGTGTTTGACACTAGGTGGTGTTCAGTTCGGTAACGGAGAGACTGTGCGGCATACTTAATTATACATTTGAAACGCGCCCAAGTGACGCTAGGCAAGTCAGAGCAGGTTCCCGTGTTAGCTTAAGGGTAAACATACAAGTCGATTGAAGATGGGTAGGGGGCTTCAATTCGTCCAGCACTCTACGGTACCTCCGAGAGCAAGTAGGGCACCCTGTAGTTCGAAGCGGAACTATTTCGTGGGGCGAGCCCACATCGTCTCTTCTGCGGATGACTTAACACGTTAGGGAGGTGGAGTTGATTCGAACGATGGTTATAAATCAAAAAAACGGAACGCTGTCTGGAGGATGAATCTAACGGTGCGTAACTCGATCACTCACTCGCTATTCGAACTGCGCGAAAGT";
//        stringB = "TCCCAGCGCTCATACACTTGGTTCCGAGGCCTGTCCTGATATATGAACCCAAACTAGAGCGGGGCTGTTGACGTTTGGAGTTGAAAAAATCTAATATTCCAATCGGCTTCAACGTGCACCACCGCAGGCGGCTGACGAGGGGCTCACACCGAGAAAGTAGACTGTTGCGCGTTGGGGGTAGCGCCGGCTAACAAAGACGCCTGGTACAGCAGGAGTATCAAACCCGTACAAAGGCAACATCCTCACTTCGGTGAATCGAAGCGCGGCATCAGGGTTACTTTTTGGATACCTGAAACAAAACCCATCGTAGTCCTTAGACTTGGCACACTTACACCTGCAGCGCGCGCATCTGGAAATAGAGGCCAAGTTCGATCCGTACTCCGACGTACGATGCAACAGTGTGGATGTGACGAGCTTCATTTATACCCTTCGCGCGCCGGACCGGCCTCCGCAAGGCGCGGCGGTGCACAAGCAATTGACAACTAACCACCGTGTATTCGTTATGGCATCAGGCAGTTTAAGTCGAGACAATAGGGCTCGCAATACACAGTTTACCGCATCTTGCCCTAACTGACAAACTGTGATCGACCACTAGCCATGCCATTGCCTCTTAGACACCCCGATACAGTGATTATGAAAGGTTTGCGGGGCATGGCTACGACTTGTTCAGCTACGTCCGAGGGCAGAAACTTATCCCCATTTGTATGTTCACCTATCTACTACCCATCCCCGGAGGTTAAGTAGGTTGTG";
//
        /* Read sequences from file */
        try
        {
            sequences = ioMaster.readFileToStringArray();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        /* Copy to stringA and stringB */
        stringA = sequences[0];
        stringB = sequences[1];
        /*
        * CHOOSE ONE ONLY
        * */
//        runSingle(stringA, stringB);
//        runSingle(stringA, stringB);
//        runSingle(stringA, stringB);
        runSingle(stringA, stringB);
        runParallel2(stringA, stringB);
//        runParallel2(stringA, stringB);
//        runParallel2(stringA, stringB);
//        runParallel2(stringA, stringB);
//        runParallel3(stringA, stringB);
//        printMatrix( matrix2 );
        /* print at cell */
//        println("Check if match: " + isMatrixSame(matrix1,matrix2));
        /*
        * Perform Traceback below
        * */
//        magicalTraceback = new MagicalTraceback(matrix, standardMatrixFiller.getHighX(), standardMatrixFiller.getHighY());
        /* Print matrix table */
//        printMatrix(matrix);
        /* Find the best local alignment */
//        magicalTraceback.generateAlignments(stringA, stringB);
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
    public static class checker
    {
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
    }



}
