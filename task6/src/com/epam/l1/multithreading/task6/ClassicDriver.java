package com.epam.l1.multithreading.task6;

import java.util.ArrayList;

public class ClassicDriver {
    public static void main(String[] args) {

        DataClassic dataClassic = new DataClassic(new ArrayList<>());

        Producer producer = new Producer(dataClassic);
        Consumer consumer = new Consumer(dataClassic);

        Thread producerThread = new Thread(producer);
        producerThread.setName("PRODUCER");
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.setName("CONSUMER");
        consumerThread.start();

    }
}
