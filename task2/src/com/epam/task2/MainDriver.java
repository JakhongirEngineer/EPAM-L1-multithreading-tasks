package com.epam.task2;

/**
 * Main class where application starts
 */
public class MainDriver {
    public static void main(String[] args) throws InterruptedException {
        Data data = new Data();
        WriterToTheData writerToTheData = new WriterToTheData(data);
        SummerOfTheData summerOfTheData = new SummerOfTheData(data);
        SquareRootOfSumOfSquares squareRootOfSumOfSquares = new SquareRootOfSumOfSquares(data);

        Thread writerThread = new Thread(writerToTheData);
        writerThread.setName("WRITER");
        writerThread.start();

        Thread summerThread = new Thread(summerOfTheData);
        summerThread.start();
        summerThread.setName("SUMMER");

        Thread squareThread = new Thread(squareRootOfSumOfSquares);
        squareThread.start();
        squareThread.setName("SQUAREDER");

    }
}
