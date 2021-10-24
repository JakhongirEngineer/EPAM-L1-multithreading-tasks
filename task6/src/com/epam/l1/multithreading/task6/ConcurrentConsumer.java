package com.epam.l1.multithreading.task6;

public class ConcurrentConsumer implements Runnable{
    private ConcurrentData concurrentData;
    public ConcurrentConsumer(ConcurrentData concurrentData) {
        this.concurrentData = concurrentData;
    }

    @Override
    public void run() {
        while (true){
            int sum = concurrentData.getIntegers().stream().reduce(0, (s,n) -> s + n);
            System.out.println(Thread.currentThread().getName() + " calculates sum: " + sum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
