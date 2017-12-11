package Laurenz;

import Laurenz.Parallel.MatrixStorage;
import Laurenz.Parallel.Matrixer;
import Utility.IOMaster;
import Laurenz.standard.StandardTraceback;
import Laurenz.standard.StandardMatrixFiller;

import static Utility.Print.printMatrix;

public class Imp2 {

    private static int[][] matrix1;
    private static int[][] matrix2;

    public static int[][] runSingle(String stringA, String stringB)
    {
        StandardMatrixFiller standardMatrixFiller = new StandardMatrixFiller();
        StandardTraceback magicalTraceback;
        long startTime = System.currentTimeMillis(); /* time thingy */
        /* Populate matrix */
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

        /*
         * Perform Traceback below
         * */
        magicalTraceback = new StandardTraceback(matrix, standardMatrixFiller.getHighX(), standardMatrixFiller.getHighY());
        /* Print matrix table */
        printMatrix(matrix);
        /* Find the best local alignment */
        magicalTraceback.generateAlignments(stringA, stringB);

        return matrix;
    }


    public static void runParallel2(String stringA, String stringB)
    {
        MatrixStorage ms = new MatrixStorage(stringA, stringB);
        Matrixer m2 = new Matrixer(ms);
        //m2.runMatrixer2();
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

        runSingle(stringA, stringB);
//        runParallel2(stringA, stringB);
        /* print at cell */
    }
}
