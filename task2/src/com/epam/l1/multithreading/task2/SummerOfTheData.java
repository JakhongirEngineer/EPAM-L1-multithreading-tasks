package com.epam.l1.multithreading.task2;

import java.util.List;

/**
 * reads list of integers, and computes their sum
 */
public class SummerOfTheData implements Runnable{
    private Data data;

    public SummerOfTheData(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            List<Integer> integers = data.read();
            Integer currentSum = integers.stream().reduce(0, (currSum, currNum) -> currSum + currNum);
            System.out.println("Thread: " + Thread.currentThread().getName() + " sum: " + currentSum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
