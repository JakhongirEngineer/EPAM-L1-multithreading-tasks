package com.epam.l1.multithreading.task2;

import java.util.List;

/**
 * reads list of integers, and computes square root of sum of integer squares.
 */
public class SquareRootOfSumOfSquares implements Runnable{
    private Data data;
    public SquareRootOfSumOfSquares(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            List<Integer> integers = data.read();
            Integer sumOfSquares = integers.stream()
                    .map(i -> i * i)
                    .reduce(0, (currSum, currNum) -> currSum + currNum);
            double squareRootOfSumOfSquares = Math.sqrt(sumOfSquares);
            System.out.println("Thread: " + Thread.currentThread().getName() + " squareRootOfSumOfSquares: " + squareRootOfSumOfSquares);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
