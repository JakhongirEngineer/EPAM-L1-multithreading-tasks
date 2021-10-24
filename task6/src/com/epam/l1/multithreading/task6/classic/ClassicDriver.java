package com.epam.l1.multithreading.task6.classic;

import java.util.ArrayList;

public class ClassicDriver {
    public static void main(String[] args) {

        Data data = new Data(new ArrayList<>());

        Producer producer = new Producer(data);
        Consumer consumer = new Consumer(data);

        Thread producerThread = new Thread(producer);
        producerThread.setName("PRODUCER");
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.setName("CONSUMER");
        consumerThread.start();

    }
}
