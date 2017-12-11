import Laurenz.Parallel.MatrixStorage;
import Laurenz.Parallel.Matrixer;
import MatrixFiller.ParallelMatrixFiller;
import MatrixFiller.StandardMatrixFiller;
import MatrixFiller.Traceback;

import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class Main {
    public static int NUM_TRIES = 1;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Traceback traceback;
        StandardMatrixFiller standardMatrixFiller = null;

        int[][] matrix = null;
//        String stringA = "ATGCATCCCATGAC";
//        String stringB = "TCTATATCCGT";

        String stringA = "GGACGTGACGAGCTTCTTTTATATGCTTCGCCCGCCGGACCGGCCTCGTGATGGGGTAGCTGCGCATAAGCTTATGACAATTAACGAGTGTGTACTCGTTTTATCATCTCACAGTTAAAGTCGGGAGAATAGGAGCCGCTACACACAATTTACCGCATCTAGACCTAACTGAGATACTGCCATAGACGACTACCCATCCCTCTGGGCCTTAGATAGCCGGATACAGTGACTTTGAAAGGTTTGTGGGGTACAGCTATGACTTGCTTAGCTGCGTGTGGGGGAAGGAACTTTTGCGTGTTAGTATGTTGACCCGTGTATTACGCATGCGGGTAGATTATGTAGGTAGAGACATCCAGGTCAAGTTCTCGACCTTCTCGTGGGAGGTGAACCAGTTCACTATAGGACCATTCCGTTCGAGCATGGCACTAAGTACGCCCTCCCCATTCTGGTAATCTTCATCCCTATCAGGGCTTGGAGTGAATGGTGAGGGTTATTCCCCAGGAACAGACTTCCTACTCACAGTCGGTCACATTGGGCTACTCCTTGGGTCTTCGGCTTGACCCGGTCTGTTGGGCCGCGATTGCGTGAGTTTCGGCCCTGCGCTGCGCTGTATAGCCGATTCTCATCCGGGCCTCACATCTGGAAACCCCAACCTATTTAGACAGCATCATTGGCCGAAGTTGCTGGGCATGTCCACCGTGAAGTCCTCCCCGGGCGTCCCTCCTTCAAAAGACGATAAGCTCCGGCAAGCACCATTGATCAACGCAAGGATCGGTGATGTTAACAAAGATTCGGCACATTACTCTTGTTGGTGTGGTATCGCTTAACTGCGCGGCGGAGCCTTATGGCAAAACCGTTCGGGAATGATTCCGGTAGCGCTAAAGGTCCATAGCACGTACATCGCAACCTGGCGTGCGTTCAATTTGACGACCGCTTGGCGCTAAGGTGCTGGCCACGTGCTAAATTAAAGCGGCTGCACTGCTGTAAGGACGATTACGGAGTGGGCGGCCTGGGGGGAGCACTACCCCATCGACCTGTACAGGAACACTCTATATTGCTCTCAGACGAACAAATTACTAGAGTGCCGCTTTCAGCCCCCCTGTCGTCGCCGACGTCTGTAATATGGCGTTGTTGTGATTCGACTCTATTGAGGCATCAACTGATGCGTAAGGAGATCTGGAATGAATTGGCCTATGTCACTGAAACTGTCCAAACACCCAATGTCGTTAGTGAAGGTTCTGACGCATACCTCCTTCGTTGAGAACTCACAATTATACAACTGGGGACATAATCCCTACGCCCATCATCTACACGCGTCTCTGTGGCTCCAGTTCATGTATTGGGAGAGTATCCTCCACAAGATCTAGTGGTATGGTGGTATAGTAAGCTCGTACTGTGATACACGCGACAGGGGTAGGACCATCAGTAATAGGGATAGTGCGAAAGCTCACTGACCACTGCCTATAGGGGGTGCTTACTTTTAGAAAAAGTGTCAGCCAGTATAACCCCACGAGGATTCGAAAAGGTGAACCGAGCCAGACAATCCGGAGGCACGGGGCTCAAAGCCGCGACACGACGGCTCTCGGCCGGTAACAGTAACCCCGGAGTGAACACCTATGGGGCTGGATAAAACTGCCCTGGTGAGCGCCATCAGCAACCCGAATACGTGGCATTTCAGGAGGCGGCCGGAGGGGGGATGTCTTCTACTATTCGAGGCCGTTCGTTAATACTTGTTGCGTTCCTAGCCGCTATATTTGTCTCTTTGCCGACTAATGTGAACAACCACACCATAGCGATTTATCGGAGCGCCTCGGAATACGGTATGAGCAGGCGCCTCGTGAGACCATTGCGAATACCAGGTATCGTGTAAGTAGCGAAGGCCCGTACGCGAGATAAACTGCTAGGAAACCGCGTCTCTACGACCGGTGCTCGATTTAATTTCGCTGACGTGATGACATTCCAGGCAGTGCGTCTGCTGCCGGGTCCCTCTCGTGATTGGGTAGTTGGACATGCCCTTGAAAGACATAGCAAGAGCCTGCCTCTCTATTGATGTCACGGCGAATGTCGGGGAGACAGCAGCGGCTGCAGACATTACATCGGAGTAACACTAAGGTGGGATAACTCCGTAACTGACTACGCCTTTCTCTAGACCTTACTTGACCAGATACACTGTCTTTGACACGTTGATGGATTAGAGCAATCACATCCAAGACTGGCTATGCACGAAGCAACTCTTGAGTGTTAAAATGTTGTCTCCTGTATTCGGGATGCGGGTACTAGATGACTGCAGGGACTCCGACGTTAAGTACATTACCCTGTCATAGGCGGCGTTCAGGATCACGTTACCGCCATATGATGCGAGCATGACATCATCTCCGCTGTGCCCACCCCAGTAGTGATTATTCCTATAACCCTTCTGAGTGTCCGGAGGCGGAAATCCGCCACGAATGAGAATGTATTTCCCCGACAATCATAATGGGGCGCTCCTAAGCTTTTCCACTTGGTTGGGCCGGCTAGGCCTCTCTGCCCGGAGTTTCGGCGCACTGCTGCCGACAGCCGGGCATTGTTTTAGGGGCGTTATTCGAGGGCACTCGGAGCTAACTTGTCGGGACCAGCCGGGGTAGTCATCGGGCTTATACAGCGAAAAGCCCAGGACCCGGCTCCACGCTATGGAACGTCTTTAGCTCCGGCAAGCAATTAAGAACAACGCAAGCATCGCGGATATAAACAGAGAAACGGCCGAATACACCTGTTCGTATCGTATCGGTAAATAGCCTCGCGGAGCCATGTGCCATACTGGTCTGCGGAGCACTCTGGTTATGCATATGGTCCACAGGACACTCGTCGCTTCCGGGTATGCGCTCTATGTGACGGTCTTTAGGCGCACTAATGCTCAGCACCATTTAAACCAGACCGACACCAGATCTGTAAGGTCCGCCACGCAGACGACAGCCCACGGAGATCACCGACCGATCTATCTGATCGGCGACCATTTGTGTGGTACTGGGGCGGAGAGGTAACTACGGTGCCGCTAACAACCCCTCTGTCGTCGCTGACGTTTGTAGTCTAGTCTCATTATGATTGTACGCTATTCAGGGATTGACTGATACCGGAAGACATCTCAGTTGAAGTGGTCTATACGACAGAGACCGTGCACCTACCAAATCTCCTTAGTGTAAGTTCAGACCAATTGGTAGTTTGTCCAGAACTCAGATTTTAACAGCAGAGGACGCATGCTCTACCTTCATGATCCACTGACGTCCCTGAGGCTGCAATACATGCAACGAGGCAGTCTCCGCGGTAAGTCCTAGTGCAATGGCGCTTTTTTACCCTCGTCCTCGAGAAGAGGGGACGCCAGTGCAGATATCTTTAATGTGGTAATTGGGAGGACTCTTGGCCCTCCGCCCTTAGGCAGTGCATACTCTTCCATAAACGGGCTGTTAGTTATGGCGTCCGAGGATTCAAAAAGGTGAGCGAACTCGGCCGATCCGGAGAGACGGGCTTCAAAGCTGCCTGACGACGGTTGCGCGTCCGTATCAAAATCCTCCTAATAAGCCCCCGTCACTGTTGGTTGAAGAGCCCAGGACGGGTTGGCCAGATGTGCGATTATATCGCTTAATGGCTCTTGGGCCGCGGTGCGTTACCTTGCAGGAATTGAGGCCGTCCGTTAATTTCCCTTGCATACATATTGCGTTTTTTTGTCTTTTTATCCGCTTACTTAGATAAGAGTGACATAGCTTCTTACCGGAGCGCCTCCGTACACAGTACGATCGCACGCCCCATGAGATCGATAGGTATACCAGGTGTCCTGTGAGCAACGAAAGCCTAAACGGGAAATACGCGGCCAAAAGTCGGTGCGAATACGAGTCGTAGCAATGTTGGTCTGGCTATGATCTACATATTTCAGGCGGTACGTCTGCTCTGGTCAGCCTCTAATGGCTCGTTAGATAGTCTAGCCGCTGGTAATCACTCGATGACCTCGGCTCCCCATTGGTGCTACGGCGATTCTTGGAGAGCCAGCTGCGATCGCTAATGTGAGGACAGTGTAATATTAGCAAGCGATAAGTCCCCAACTGGTTGTGGCCTTTTGAAAAGTGAACTTCATAACATATGCTGTCTCACGCACATGGATGGTTTGGACAAATTTGATTCAAGTCTGATCAACCTTCACACAGATCTAGAATCAAAAGCAGTGATCTCCCGGGTGCGAAATAAAAATACTAGGTAACTAGAGGGACTGCGACGTTCTAAACGTTGGTCCGTCTGAACCGCCATCCAGGATCACGTCGCCCCGAAAAAAAGATATCAGGAACTCTCCTCCTCAGCAGTCAGGTCTATGGAAACTACAGGACTAACCTTCCTGGCAACCGGGGGGTGGGAATCCGTCACATATGAGAAGGTATTTGCTCGATAATCAATACTCCAGGCATCTAACTTTTCCCACTGCCTTAAGCCGGCTTGCCCTTTCTGCCTGTAGATCCATAGGACTCGTGCCAACGCGCAGGCATAGTTCGAGGAGAAATATCCGGGGCCAAAGACAACCAGCATCTCGGGTCTTGCCCAACCCGCCTACATGCTGTTATAGCGAATCAGTGGAAACCCGGTGCCAGGCGATGGAATGTCCTTAACTCTGGCAGGAAATTAAAGGGAACGTATATACAACGCAAAGAAGCTGGAAAATTGGCGAGAGAATCTTCTTTCTGTCTATCGAAGAATGGGCATGGGGTGGCAACCGTCATGCTAGCGTGCGGGGTGCACTTGGTAACCATTTGGGACACCGGACACTCGCTGTTTTCGAAATTACCCTTTAAGCGCGGGTATTGAACCAGGCTTATGCCCAGCATCGTTGCAAGCAGACTCAAACTAGATATATTATGCCCGCCATACAGACGAAACTAGTCGGAGATTATCGAGCATACTATCACGTCGGCGACCACTAGTGAGTTACTAGAGCCGAGGGGCAACGTTGATGCCCCTAAGAACCTCTGGCTCGACGCAAGCCATAACACCCCTGTCACATCATAATCGTTTGCAATTCAGGGCTTGATCAACACTGGATTGCCTTTCTCTTAAAGTATTATGCAGGACGGGGTGCGCGTACCATGTAAACCTGTTATAACTTACCTGAGACTAGTTGGAAGTGTGGCTAGATCTTAGCTTACGTTTCTAGTCGGTCCACGTTTGGTTTTTAAGATCCAATGATCTTCAAAACGCTGCAAGATTCACAACCTGCTTTACTAAGCGCTGGGTCCTACTGCAGCGGGACTTTTTTTCTAAAGACGTTGAGAGGAGCAGTCGTCAGACCACATAGCTTTCATGTCCTGATCGGAAGGATCGTTGGCGCCCGACCCTTAGACTCTGTACTGAGTTCTATAAACGAGCCATTGCATACGAGATCGGTAGATTGATAAGGGACACAGAATATCCCCGGACGCAATAGACGGACAGCTTGGTATCCTAAGCACAGTCGCGCGTCCGAACCTAGCTCTACTTTAGAGGCCCCGGATTCTGGTGCTCGTAGACCGCAGAACCGATTGGGGGGATGTACAACAATATTTGTTAGTCACCTTTGGGTCACGATCTCCCACCTTACTGGAATTTAGTCCCTGCTATAATTTGCCTTGCATATAAGTTGCGTTACTTCAGCGTCCTAACCGCACCCTTAGCACGAAGACAGATTTGTTCATTCCCATACTCCGGCGTTGGCAGGGGGTTCGCATGTCCCACGTGAAACGTTGCTAAACCCTCAGGTTTCTGAGCGACAAAAGCTTTAAACGGGAGTTCGCGCTCATAACTTGGTCCGAATGCGGGTTCTTGCATCGTTCGACTGAGTTTGTTTCATGTAGAACGGGCGCAAAGTATACTTAGTTCAATCTTCAATACCTCGTATCATTGTACACCTGCCGGTCACCACCCAACGATGTGGGGACGGCGTTGCAACTTCGAGGACCTAATCTGACCGACCTAGATTCGGCACTGTGGGCAATATGAGGTATTGGCAGACACCCAGTGCCGAACAACACCTGACCTAACGGTAAGAGAGTCTCATAATGCGTCCGGCCGCGTGCCCAGGGTATATTTGGACAGTATCGAATGGACTGAGATGAACCTTTACACCGATCCGGAAACGGGTGCGTGGATTAGCCAGGAGCAAACGAAAAATCCTGGGCTACTTGATGTCTTGTGACGTTCTTAGAGATGGACGAAATGTTTCACGACCTAGGATAAGGTCGCCCTACAAAATAGATTTGTGCTACTCTCCTCATAAGCAGTCCGGTGTATCGAAAGAACAAGACTAGCCTTGCTAGCAACCGCGGGCTGGGGGGCTAAGGTATCACTCAAGAAGCAGGCTCGGTAACATACGGTCTAGCTATCTGACTATCGCCTACGTCATATAGGGACCTATGATATCTGCGTGTCCAACCTTAGAATTCACTTCAGCGCGCAGGTTTGGGTCGAGATAAAATCACCAGTACCCAAGACCACGGGCGCTCGGCGCCTTGGCTAATCCCGGTACATGTTGTTATAAATAATCAGTAGAAAATCTGTGTTAGAGGGTCGAGTCACCATATATCAAGAACGATATTAATCGGTGGGAGTATTCAACGTGATGAAGACGCTGGGTTTACGTGGGAATGGTGCTTCTGTCCTAACAGGCTAGGGTATAATGCTGAAACCGTCCCCCAAGCGTTCAGGGTGGGGTTTGCTACGACTTCCGAGTCCAAAGTGTCCGTGTTTTTGATATATACGCTCAAGGGCGAGAATTGGACCTGGCTTACGTCTTAGTACGTAGCATGGTGACACAAGCACAGTAGATCCTGCCCGCGTTTCCTATATATTAAGTTAAATCTTATGGAATATAATAACATGTGGATGGCCAGTGGTCGGTTGTTACACGCCTACCGCAATGCTGAAAGACCCGGACTAGAGTGGCGAGATCTATGGCGTGTGACCCGTTATGCTCCATTTCGGTCAGTGGGTCACAGCTAGTTGTGGATTGGATTGCCATTCTCCGAGTGTTTTAGCGTGACAGCCGCAGGGATCCCATAAAATGCAATCGTAGTCCACCTGATCGTACTTAGAAATGAGGGTCCGCTTTTGCCCACGCACCTGATCGCTCCTCGTTTGCTTTTAAGAACCGGACGAACCACAGAGCATAAGGAGAACCTCTAGCTGCTTTACAAAGTACTGGTTCCCTTTCCAGCGGGATGCTTTATCTAAACGCAATGAGAGAGGTATTCCTCAGGCCACATCGCTTCCTAGTTCCGCTGGGATCCATCGTTGGCGGCCGAAGCCGCCATTCCATAGTGAGTTCTTCGTCTGTGTCATTCTGTGCCAGATCGTCTGGCAAATAGCCGATCCAGTTTATCTCTCGAAACTATAGTCGTACAGATCGAAATCTTAAGTCAAATCACGCGACTAGACTCAGCTCTATTTTAGTGGTCATGGGTTTTGGTCCCCCCGAGCGGTGCAACCGATTAGGACCATGTAGAACATTAGTTATAAGTCTTCTTTTAAACACAATCTTCCTGCTCAGTGGTACATGGTTATCGTTATTGCTAGCCAGCCTGATAAGTAACACCACCACTGCGACCCTAATGCGCCCTTTCCACGAACACAGGGCTGTCCGATCCTATATTACGACTCCGGGAAGGGGTTCGCAAGTCGCACCCTAAACGATGTTGAAGGCTCAGGATGTACACGCACTAGTACAATACATACGTGTTCCGGCTCTTATCCTGCATCGGAAGCTCAATCATGCATCGCACCAGCGTGTTCGTGTCATCTAGGAGGGGCGCGTAGGATAAATAATTCAATTAAGATATCGTTATGCTAGTATACGCCTACCCGTCACCGGCCAACAGTGTGCAGATGGCGCCACGAGTTACTGGCCCTGATTTCTCCGCTTCTAATACCGCACACTGGGCAATACGAGCTCAAGCCAGTCTCGCAGTAACGCTCATCAGCTAACGAAAGAGTTAGAGGCTCGCTAAATCGCACTGTCGGGGTCCCTTGGGTATTTTACACTAGCGTCAGGTAGGCTAGCATGTGTCTTTCCTTCCAGGGGTATGCGGGTGCGTGGACAAATGAGCAGCATACGTATTTACTCGGCGTGCTTGGTCTCTCGTATTTCTCCTGGAGATCAAGGAAATGTTTCATGTCCAAGCGAAAAGCCGCTCTACGGAATGGATCTACGTTACTGCCTGCATAAGGAGACCGGTGTAGCCAAGGACGAAAGCGACCCTAGGTTCTAACCGTCGACTTTGGCGGAAAGGTTTCACTCAGGAAGCAGACACTGATTGACACGGTTTAGCAGAACGTTTGAGGACTAGGTCAAATTGAGTGGTTTAATATCGGCATGTCTGGCTTTAAAATTCAGTATAGTGCGCTGATCGGAGACGAATTAAAAACACGAGTTCCCAAAACCAGGCGGGCTCGCCACGTCGGCTAATCCTGGTACATTATGTGAACAATGTTCTGAAGAAAATTTGTGAAAGAAGGACGGGTCATCGCCTACTATTAGCAACAACGGTCGGCCACACCTTCCATTGTCGTGGCCACGCTCGGATTACACGGCAGAGGTGCTTGTGTTCCGACAGGCTAGCATATTATCCTAAGGCGTTACCCCAATCGTTTACCGTCGGATTTGCTATAGCCCCTGAACGCTACATGTACGAAACCATGTTATGTATGCACTAGGTCAACAATAGGACATAGCCTTGTAGTTAACACGTAGCCCGGTCGTATAAGTACAGTAGACCCTTCGCCGGCATCCTATTTATTAAGTTATTTCTACAGCAAAACGATCATATGCAGATCCGCAGTGGGCGGTAGACACACGTCCACCCGGCTGCTCTGTAACAGGGACTAAAGAGGTGATGATTATCGTGTGTGCCCCGTTATGGTCGTGTTCGATCAGAGCGCCCTTGCGAGCAGTCGTATGCTTTCTCGAATTCCGAGCGGTTAAGCGTGACAGTCCCAGCGAACCCACAAAACGTG";
        String stringB = "ATCGCAGTCCATGCGATCATACGCAAGAAGGAAGGTCCCCATACACCGACGCACCAGTTTACACGCCGTATGCATAAACGAGCTGCACAAACGAGAGTGTTTGAACTGGACCTCTAGTTCCTCTACAAAGAACACCTTGACCTGTTGCGAAGTTGCCTTGCCTAGATGCAATGTCGGACGTATCACTTTTGCCTCAACGACTGCTGCTTTCGCTGTAACCCAAGACAGACAACAGTAACCGCCTTTTGAAGGCAAGTCCTCCGCCTGTGACTAACTGTGCCAAATCGTCTTCCAAACCCCTTATCCAATTTAACTCACCAAATTATTGCGATACAGACCCTAATTTCACATCATATGACACTAATTGCCTCTGCCAAAATTCTGTCCTCAAGCGTTTTAGTTCGCCCCAGTAATGTTGCCAATAAGGACCACCAAATCCGCATGTTACAGGACTTCTTATAAATTCTTTTTTCGTGGGGAGCAGCGGATCTTAATGGATGGCGCCAGCTGGTATGGAAGCTAATAGCGCCGGTGAGAGGGTAATCAGCCGTCTCCACCAACACAACGCTATCGGGTCATATTATAAGATTCCGCAATGGGACTACTTATAGGTTGCCTTAACGATATCCGCAACTTGCGATGTGCCTGCTATGCTTAAATACATACCTCGCCCAGTAGCTTTCCAATATGGGAACATCAATTGTACATCGGGCCGGGATAATCATGTCGTCACGGAACTTACTGTAAGAGTAATAATTCAAAAGAGATGTCGGTTTGCTAGTTCACGTAAAGGTGCCTCGCGCCACCTCTAAGTAAGTGAGCCGTCGAGACATTATCCCTGATTTTCTCACTACTATTAGTACTCACGGCGCAATACCACCACAGCCTTGTCTCGCCAGAATGCCGGTCAGCATATGGAAGAGCTCAAGGCAGGTCAATTCGCACTGTGAGGGTCACATGGGCGTTTGGCACTACCGACACGAACCTCAGTTAGCGTACATCCTACCAGAGGTCTGTGGCCCCGTGGTCAAAAGTGCGGGTTTCGTATTTGCTGCTCGTCTGTACTTTCAGAATCTTGACCTGCACGGCAAAGAGACGCTTTTTATGGAGCTCGACATGGCAACAACGCGACGGATCTACGTCACAACGAGAATAGTGTAAACGAAGCTGCTGACGGCGGAAGCGACATAGGGATCTGTGAGTTGTTATTCGCGAAAAACATCCGTCCCCGTGGGGGATAGTCACTGACGCGGTTTTGTAGAAGCCTAGGGGAACAGGTTAGTTTGACTAGCTTAAGAATGTAAATTCTGGGATTATACTGTAGTAATCACTAATTAACGGTGAGGGTTTTAAGACGGATCTTTGCAAATTCAAGCGAGGTGATTTCAACAAATTTTGCTGATGGTTTAGGCGTACAATCCCCTAAAGTATAATTAAGAAAATAGCATTCCTTGTCGCCTAGAATTACCTACCGGCGTCCACCATACCTTCGATATTCGCGCCCACTCTCCCATTAGTCGGCACAAGTGGATGTGTTGCGATTGCCCGCTAAGATATTCTAAGGCGTAACGCAGATGAATATTCTACAGAGTTGCCGTACGCGTTGAACACTTCACGGATGATAGGAATTTGCGTATAGAGCGGGTCATTGAAGGGTTATACACTCGTAGTTAACAACGGGCCCGGCTCTATCAGAACACGAGTGCCTTGAATAACATACTCATCACTAAACTTTCTCAACAGTCAATCGAGCAAGTCCATTACCAAGGAGTGCGATGCAGTTTTATTCTCTCGCCAGCACTGTAATAGGCACTAAAAGAGTGATGATAATCATGAGTGCTGAGCTAAGACGGCGTCGGTACATAGCGGTCTTACGGTCAGTCGTAATTCCTCACGAGTCCCGTCCAGTTGAGCGTATCACTCCCAATGTACAAGCAACCCGAGAAGGCTGTGCTTGGAGTCAATCGGATGTAGGATGGTCTCCAGACACGGGGCCACCACTCTTCACGCGTAAAGCAAGAACGTCGAGCAGTCATGAAAGTCTTAGTACCGGACGTGCCGTTTTACTGCGAATATTACCTGAAGCTGTACCGTTATTGGGGGGCAAAGATGGAGTCCTCCTCTTATCATATTTGTATTGACGACAGCCGTGTTCCCGGTTTCCTCAGAGATTTAAGAATAAGGGCTTATTGTAGGCAGAGGGACGCCCTTTTAGTGGCTGGCGTAAAATATCTTCGGATCCCCTTGTCTAACCAGATTAATCGAATTCTCTCATTTAGGACCCTAGTAAGTCATCATTGGTGTTTAAATGCCACCCCGAAGAAACCGCCTAAAAATGTCTATGATTGGTCCACTAAAGTTGATTAAATCAACTCCTAAATCCGCGCGATAGGGCATTAGAGGTTTAATTTTGTATGGCAAGGTACTCCCGATCTTAATGAATGGCCGGAAGTGGTACGGACGCAATATGCGCGGGTGAGAGGGCAAATAGGCAGGTTCGCCTACGTTACGCTAGGGGGCGATTCTATAAGAATGCACATTGCATCGATACATAAGATGTCTCGACCGCATGCGCAACTTGTGAAGTGTCTACTATCCCTAAGCCCATTTCTCGCACAATAACCCCTGAATGTGTCCGCATCTGATGTTACCCGGGTTGAGTTAGTGTCGAGCTCGCGGAACTTATTGCATGAGTAGAGATATGTAAGAGCTGTTAGATAGCTCGCTGAGCTAATAGTTGCCCACAGAACGTCAAAATTAGAGAACGGTCGTAACATTATCGGTGGTTCTCTAACTACTATCAGTACCCATGACTCGACTCTGCCGCAGCTACCTATCGCCTGAAAGCCAGTTGGTGTTAAGGAGTGCTCTGTCCAGGACAACACGCGTAGTGAGAGTTACATGTTCGTTGGGTTCTCCCGACTCGGACCTGAGTCGACCAAGGACCCACTCGAGCTCTGAGCCCCACTGTCGAGAAGTATGTATCTCGCTCCCGCAGCTTGCCAGCACTTTCAGTATCATGGGGCCCATGGTTGAATGACTCCTATAACGGACTTCGACATGGCAAAATCCCCCCCTTGCAACTTCTAGAGGAGAAGAGTACTGACTTGAGCGCTCCCAGCACAACGGCCAAGGAAGTCTCCAATTTCTTGTTTCCGAATGACACGCGTCTCCTTGCGGGTAAATCGCCGACCGCAGAACTTAGGAGCCAGGGGGAACAGATAGGTCTAATTAGGTTAAGGGAGTAAGTCCTCGGATGGTTCAGTTGTAACCATATACTTACGCTGGAACTTCTCCGGCGAATTTTTACTGTCACCAACCACGAGATTTGAGGTAAACCAATTGAGCACATAGTCGCGCTATCCGACAATCTCCAAATTATAACATACCGTTCCATGAAGGCCAGAGTTACTTACCGGCCCTTTCCATGCGCGCGCCATACCCCCCCAGTTCCCCGGTTATCTCTCCGAGGAGAGAGTGAGCGATCCTCCGTTAACATATTCTTACGTATGACGTAGCTATGTATTTTGCAGAGGTAGCGAACGGGTTTGACACTTCACAGATAGTGGGGATCCGGGCAAAGGGCGTATAATTGCGGTCCAACATAGGCGTAAACTACGATGGCACCAACTCAGTCGCAGCTCGTGCGCCGTGAATAACGTACTCATCCCAACTGATTCTCGGCAATCTACGGAGCGACATGATTATCAACAGCTGTCTAGCAGTTCTAATCTTTTGCCATCGTCGTAAAAGCCTCCAAGAGATTGATCATACCTATCGGCACAGAAGTGACACGACGCCGATGGGTAGCGGACTTTTGGTCAACCACAATTCGCTAGGGGACAGGTCCTGCGGCGTACATCACTTTGTATGTGCAACCAGCCCAAGTGGGGCCAGGCAAGACTCAGCTGGTTCCTGTGTTAGCTCGAGGCTAGGGATGACAGCTCTTTAAACATAGGCTGGGGGCGTCGAACGGTCGAGAAGCTCATAGTACCTCGGGTACCAACTTACTCAGGCTATTGCTTGAAGCTGTACTATTTCAGGGGGGGAGCGCTGATGGTCTCTTCTTCTGATGACTCAACTCGCAAGGGTCGTGAAGTCGGTTCCTTCAATGGTTAAAAATCAAAGGCTCACTGTGCAGACTGGAGCGCCCATCTAGCGGCTCGCGTCTCGAATGCTCGGTCCCCTTTGTCATTCCGGATAAATTCATACCCCTCATTCACTAGCTTGCGAAGTCTACATTGGTATATGAATCCGACCTAGAAGAGGGCACTTAAAATTGGGAGTGGTTGATGCTCTATACTCCATTCGGTTTTTTCGTGCATCACCGCGAGAGGCTGACAAGGGTTTGACATTGAGTAGCAAGGCACTTCCGGACTCAATGAAGGGCCGGGAAAGGTACGCGCGTGGTATGGGAGGATCAAGGAGCCAATACAAAGGCTTCATCCTCACTCGCATGGAGGCAAACGCAGAACAATGGTTACTATTTCGATACGTGAAACATGTCCCACGGTAGCCCAAAGACTTAAGAGTCTATCACCCCTAGGGCCCTTTCCCGGATATAAACGCCAGGTTGAATCCGCATTTGGAGCTACGATGGATGAGTCTGGGTGGAGCGCGCCCCATTTATACCGTGAGTAGGGTCGACCAAGAACCGCAAGATGCGTCGGTGTACAAATAATTGTCAACAGACCGTCGTGTTTTGAAAATGGTACCAGCATCTTCGGGCGGTCTCAATCAAGCATGGATTACGGTGTTTACTCTGTCCTGCGGTTACCCATGGCCTGTAATCCAGCTCGAGTCAAGCCATTGCCTCTCTGGGACGCCGCATGAACTAATACGTATACTTTGCACGGGTTCACTGCGGTCCGTTCAGAGTCGACCAAGGACACAATCGAGCTCCCATCTGTATGCTCGACTAACTTGTACCCAACCCCCGGAGCTTGGCAGCTCCTGGGGTATCATGGAGCCTCTGGTTCATCCCGTGGGATATCAAGCTTCGTCTTGATAAAGCTCCCCGCTCGGGAGTAGCAGAGAAGACGCCTACTGAATTGTGCGATCCCTGCACCTCAGCTAAGGTAGCTACCAATATTTAGTTTCTAAGCCTTACGACAGACCTCGCACTTAGATTGCCATGCATAGAGCTAACGAGCCAGCGAAAAGCGTGAGGCGCTTTCAAGCATGGCGAGTAAGTGATCCAACGCTTCGGATACGACTATATACTTAGGTTTGATCTCGCCCCGAGAACTGTAAACCTCAACATTTATAGATTATGAGGTTAGCCGAAAATGCACGTGGTGGCGCCCGCCGACTGCTCCCAGAGTGTGGCTCTTTGTTCTGTCAAGGCCCGACCTTCATCGCGGCCGATTCCTTCTGCGGACCATGTCGTCCTGATACTTTGGTCATGTTTCCGTTGTAGGAGTGAACCCACTTGGCTTTGCGCCATAATTCCAATGAAAAACCTATGCACTTTGTTTAGGGTACCATCAGGAATCTGAACCCTCAGATAGTGGGGATCCCGGGTATAGACCTTTATCTGCGGTCCAACTTAGGCATAAACCTGCATGCTACCTTGTCAGACCCACTCTGCACGAAGTAAATATGGGATGCGTCCGACCTGGCTCCTGGCGTTCCACGCCGCCACGTGTTCGTTAACTGTTGATTGGTGGCACATAAGTAATACCATGGTCCCTGAAATTCGGCTCAGTTACTTCGAGCGTAATGTCTCAAATGGCGTAGAACGGCAATGACTGTTTGACACTAGGTGGTGTTCAGTTCGGTAACGGAGAGTCTGTGCGGCATTCTTATTAATACATTTGAAACGCGCCCAACTGACGCTAGGCAAGTCAGTGCAGGCTCCCGTGTTAGGATAAGGGTAAACATACAAGTCGATAGAAGATGGGTAGGGGCCTTCAATTCATCCAGCACTCTACGGTTCCTCCGAGAGCAAGTAGGGCACCCTGTAGTTCGAAGGGGAACTATTTCGTGGGGCGAGCCCACACCGTCTCTTCTGCGGAAGACTTAACACGTTAGGGAGGTGGAATAGTTTCGAACGATGGTTATTAATCGTAATAACGGAACGCTGTCTGGAGGATGAGTCTGACGGTGTGTAACTCGATCAGTCACTCGCTATTCGAACTGCGCGAAAGATCCCAGCGCTCATGCACTTGATTCCGAGGCCTGTCCCGATATATGAACCCAAACTAGAGCGGGGCTGTTGACGTTTGGAGTTGAAAAAATCTATTATACCAATCGGCTTCAACGTGCTCCACGGCAGGCGCCTGACGAGGGGCCCACACCGAGGAAGTAGACTGTTGCACGTTGGGGATAGCGCTAGCTAACAAAGACGCCTGCTACAACAGGAGTATCAAACCCGTACAAAGGGAACATCCACACTTTGGTGAATCGAAGCGCGGCATCAGGATTTCCTTTTGGATACCTGAAACAAAGCCCATCGTGGTCCTTAGACTTGGCACACTTACACCTGCAGCGCGCGCATGTGGAATTAGAGGCCAAGTTCGATCCCTACACCGACGTACGATGCAACTGTGTGGATGTGACGAGCTTCATTTATACGCTTCGCGCGCCGGACCGGCCTCCGCAAGGCGCAGCAGTGCACAAGCAAATGACAATTAACCACCGTGTATTCGTTATAGCATCAGGCAGTTTAAGTCGGGACAATAGGGGCCGCAATACACAGTTTACCGCATCTTGACCTAACTGACAAACTGCCATGGACGACTAGCCATGCCATTGGCTCTTAGACAGCCCGATACAGTGATTATGAAAGGTTTGCGGGGCATGGCTACGACTTGCTCAGCTACGTGCGAGGGCAGAAACTTTTCCGCATTTGTATGTTCACCTATCTACTACCCATGCCCGGAGATTATGTAGGTTGTGAGATGCGGGAGAGGTTCTCGATCTTCCCGTGGGACGTCAACCTTTCCCTTGATAAAGCATTCCGCTCGGGTATGGCAGTAAGTACGCCTTCTGAATTGTGCTAACCTTCATCCTTATCAAAGCTTGCTGCCAATGATTAGGATTATTGCCTTGCGACAGACTTCCTACTCACACTCGCTCACATTGAGCTACTCGATGGGCCATCAGCTTGACCCGCTCTGTAGGGTCGCGATTACGTGAGTTAGGGCTCCGGACTGCGCTGTATAGTCGAATCTGATCTCGCCCCCACAACTGCAAACCCCAACTTATTTAGATAACATGATTAGCCGAAGTTGCACGGGGTGCCCACCGTGGACTCCTCCCCGGGTGTCGCTCCTTCATCTGACAATATGCAGCCGCTACCACCATCGATTAATACAACGAACGGTGATGTTGTCATAGATTCGGCACATTTCCCTTGTAGGTGTGAAATCACTTAGCTTCGCGCCGAAGTCTTATGGCAAAACCGATGGACTATGTTTCGGGTAGCACCAGAAGTCTATAGCACGTGCATCCCAACGTGGCGTGCGTACACCTTAATCACCGCTTCATGCTAAGGTCCTGGCTGCATGCTATGTTGATACGCCTGCACTGCTCGAAGAAAATATACGAAGCGGGCGGCCTGGCCGGAGCACTACCGCATCGACGCGTATTCGAATACTGTTAATTGCTCACACATGAGCAAAATAGTAGACCGTCAATTTCAGCCCTCTTATCCTCGGCGTTGTGTGTCAAATGGCGTAGATCTGGATTGACTCTATGACGGTATCTGCTGATGGGTAGGGAGACCCAGAATCTATCGGCCTATGTCACTAAAACTTTCCAAACACCCCATGTCGATACTGAACGAATCGACGCACACTCCCTTCCTTGAAAACGCACAATCATACAACTGGGCACATAATGCGTACGCCCATCTAATACATCCAACTCTCTAGGTCCTGTTCAAGAGCTGGAAGAGCACCCTCCACTTGGTCAAGTGATATCCTCGTAAGGCAAGCTCGTACCGTCATTCATGCGGAAGGGGTAAGACCATTAGAAGTAGGGATAGTCCCGAACCTCACTTACCACTCCCAATAAGGGATCCCTGTCTGAAGGATGAGTGTCAGCCAGTGTAACCCGATGAGGTACCCAGAAGCCGAACTGGGCCAGACAACCCGGCGCTAACGCACTCAAAGCCGGGACGCGACGCGACATATCGGCTAAGAGTAGGCCGGGAGTGTAGACCTTTGGGGTTGAATAAATCTGTCGTAGTAACCGGCTTCAACGACCCGTACAGGTGGCACTTCAGGAGGGGCCCGCAGGGAGGAAGTTTTCTGCTATTCGTGGCCGTTCGTGGTAACTAGTTGCGTTCCTAGCCACTACAATTGTTTCTAAGCCGTGTAATGAGAACAACCACACCATAGCGAATTGATGCGCCGCCTCGGAATACCGTTTTGGCAACCCCTTACTAAGGCCATCGCGATTTTCAGGTATCGTGCATGTAGGGTTGGACCGCACGCATGTTAAACTGCTGGCGAACCGCGATTCCACGACCGGTGCACGATTTAATTACGCCGACGTGACGACATTCCTGCTAATGCCTCACCCGCCGGACCGCCCTCGTGATGGGGTAGCTGGGCATGACCTTGTGACATATAACGAGAGTCTACTTGTTTAATCATCTCACGGCGAAAGTCGGGGGGACAGCAGCCGCTGCAGACATTATACCGCAACTACACTAAGCTGAGATAACTCCGTAGTTGACTACGCATCCCTCTAGGCCTTACTTAACCGGATACAGTGACTTTGACAGGTTTGTGGGCTACAGCAATCACTTGCATAGCTGCGTATGGAGGAAGCAACTCTTGAGTGTTAATATGTTGACCCCTGTATTAGGGATGCGGGTAGTAGATGTGGGCAGAGACACCCAGGTCAAGTACATAACCCTCTCGTAGGAGGTGTTCCAGATCACCATACCACCATATCATTCGAGCATGACACTATGTACGCTGTCCCCATTCTGGTAGTCATCATCCCTATCACGGTTTCGAGTGACTGGTGACGGATATCCCCCACGAA";
        long start;
        long end;
        long standardAveTime = 0;
        long parallel1AveTime = 0;
        long parallel2AveTime = 0;

        System.out.println("StringA len: " + stringA.length());
        System.out.println("StringB len: " + stringB.length());

        for(int i = 0; i < NUM_TRIES; i++) {
//            standardMatrixFiller = new StandardMatrixFiller();
//            start = System.nanoTime();
//            int[][] matrix1 = standardMatrixFiller.fillMatrix(stringA, stringB);
//            end = System.nanoTime();
//            standardAveTime+= end - start;
//            //System.out.println("Standard Matrix Filler Time: " + (end - start));

//            ParallelMatrixFiller parallelMatrixFiller = new ParallelMatrixFiller();
//            start = System.nanoTime();
//            int[][] matrix2 = parallelMatrixFiller.fillMatrix(stringA, stringB);
//            end = System.nanoTime();
//            parallel1AveTime+= end - start;
//            //System.out.println("Parallel Matrix Filler Time: " + (end - start));

            MatrixStorage ms = new MatrixStorage(stringA, stringB);
            Matrixer m2 = new Matrixer(ms);
            start = System.nanoTime();
            m2.runMatrixer2();
            end = System.nanoTime();
            parallel2AveTime+= end - start;
            // int[][] matrix3 = m2.getMs().getMatrix();
            //System.out.println("Laurenz' Matrix Filler Time: " + (end - start));

//            System.out.println("Is Matrix equal: " + Main.isMatrixSame(matrix1, matrix2));
//            matrix = matrix1;
        }
        standardAveTime /= NUM_TRIES;
        parallel1AveTime /= NUM_TRIES;
        parallel2AveTime /= NUM_TRIES;

        // printMatrix(matrix);
        System.out.println("Standard1 Matrix Filler Time: " + standardAveTime);
        System.out.println("Parallel1 Matrix Filler Time: " + parallel1AveTime);
//        System.out.println("Parallel2 Matrix Filler Time: " + parallel2AveTime);

        traceback = new Traceback(matrix, standardMatrixFiller.getHighX(), standardMatrixFiller.getHighY());
        traceback.generateAlignments(stringA,stringB);
    }

    public static void printMatrix(int [][] matrix) {
        System.out.println(Arrays.deepToString(matrix).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
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
}
