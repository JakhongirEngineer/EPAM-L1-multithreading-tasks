package com.epam.l1.multithreading.task6;

import java.util.concurrent.ArrayBlockingQueue;

public class ConcurrentDriver {
    public static void main(String[] args) {


        ConcurrentData concurrentData = new ConcurrentData(new ArrayBlockingQueue<>(100000));

        ConcurrentProducer producer = new ConcurrentProducer(concurrentData);
        ConcurrentConsumer consumer = new ConcurrentConsumer(concurrentData);

        Thread producerThread = new Thread(producer);
        producerThread.setName("CONCURRENT_PRODUCER");
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.setName("CONCURRENT_CONSUMER");
        consumerThread.start();
    }
}
