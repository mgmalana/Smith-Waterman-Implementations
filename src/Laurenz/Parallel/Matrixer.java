package Laurenz.Parallel;

import static Utility.Print.*;

public class Matrixer
{
    String stringA;
    String stringB;
    MatrixStorage ms;

    public Matrixer(String stringA, String stringB) {
        this.stringA = stringA;
        this.stringB = stringB;
    }

    public Matrixer(MatrixStorage ms) {
        this.ms = ms;
    }

    public void manualLoop(MatrixStorage ms)
    {

    }

    public void runMatrixer2B()
    {
        long startTime = System.currentTimeMillis();
        MatrixStorage ms = new MatrixStorage(stringA, stringB);
        Cell c1, c2;
        int i = 1, j = 1;

        while( i <= ms.getStringALen() )
        {
//            println(ms.getFinishedThreads());

//            println("FS: " + ms.getFinishedThreads());
            c1 = new Cell("1", ms, i, ms.getStringALen(), 'r');
            c2 = new Cell("2", ms, i, ms.getStringBLen(), 'd');
            i++;

            c1.start();
            c2.start();
//            println("FT NOW: " + ms.getFinishedThreads());
        }
        while( ms.getFinishedThreads() < ( (ms.getStringALen()-1) + (ms.getStringBLen()-1) ) )
        {
            print("ft: " + ms.getFinishedThreads());
//            int sbs = ms.getFinishedThreads()+1;
        }
        println("finishedThreads: " + ms.getFinishedThreads());
        println("m2b finished");
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Time: " + totalTime);
        printMatrix(ms.getMatrix());
    }

    public void runMatrixer2() throws InterruptedException {
//        MatrixStorage matrixStorage = new MatrixStorage(stringA, stringB);
//        this.ms = matrixStorage;
        Cell c1 = new Cell(ms, 1,0, ms.getStringALen(),ms.getStringBLen());
        Cell c2 = new Cell(ms, 0, 1, ms.getStringALen(), ms.getStringBLen());
        c2.start(); /* upright triangle*/
        c1.start(); /* downward triangle*/
//        printMatrix( matrixStorage.getMatrix() );
        c2.join();
        c1.join();
    }

    public MatrixStorage getMs() {
        return ms;
    }

    public static class test1
    {
        public static void main(String[] args)
        {
            String stringA = "GGTTGACTA";
            String stringB = "TGTTACGG";
            Matrixer matrixer = new Matrixer(stringA, stringB);

            matrixer.runMatrixer2B();
        }
    }
}
