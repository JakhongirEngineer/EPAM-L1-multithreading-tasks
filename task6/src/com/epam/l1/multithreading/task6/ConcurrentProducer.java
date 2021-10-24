package com.epam.l1.multithreading.task6;

import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentProducer implements Runnable{
    private ConcurrentData concurrentData;
    public ConcurrentProducer(ConcurrentData concurrentData) {
        this.concurrentData = concurrentData;
    }

    @Override
    public void run() {
        while (true){
            int randomInteger = ThreadLocalRandom.current().nextInt(0,1001);
            concurrentData.add(randomInteger);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
