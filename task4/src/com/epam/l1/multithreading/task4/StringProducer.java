package com.epam.l1.multithreading.task4;

import java.util.UUID;

public class StringProducer implements Runnable{
    private BlockingObjectPool<String> blockingObjectPool;


    public StringProducer(BlockingObjectPool<String> blockingObjectPool){
        this.blockingObjectPool = blockingObjectPool;
    }

    @Override
    public void run() {
        while (true){
            try {
                String producedString = produce();
                blockingObjectPool.take(producedString);
                System.out.println(Thread.currentThread().getName() + " produced: " + producedString);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private String produce() {
        return UUID.randomUUID().toString();
    }

}
