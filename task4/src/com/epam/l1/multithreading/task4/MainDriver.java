package com.epam.l1.multithreading.task4;

public class MainDriver {
    public static void main(String[] args) {
        BlockingObjectPool<String> blockingObjectPool = new BlockingObjectPool<>(10);

        StringProducer stringProducer = new StringProducer(blockingObjectPool);
        StringConsumer stringConsumer = new StringConsumer(blockingObjectPool);

        Thread stringProducerThread = new Thread(stringProducer);
        stringProducerThread.setName("STRING_PRODUCER_THREAD");
        stringProducerThread.start();

        Thread stringConsumerThread = new Thread(stringConsumer);
        stringConsumerThread.setName("STRING_CONSUMER_THREAD");
        stringConsumerThread.start();
    }
}
