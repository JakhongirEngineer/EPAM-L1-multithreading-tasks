package com.epam.l1.multithreading.task4;

public class StringConsumer implements Runnable{
    private BlockingObjectPool<String> blockingObjectPool;

    public StringConsumer(BlockingObjectPool<String> blockingObjectPool){
        this.blockingObjectPool = blockingObjectPool;
    }

    @Override
    public void run() {
        while (true){
            try {
                String consumedString = blockingObjectPool.get();
                System.out.println(Thread.currentThread().getName() + " consumed: " + consumedString);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
