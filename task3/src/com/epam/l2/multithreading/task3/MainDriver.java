package com.epam.l2.multithreading.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDriver {
    public static void main(String[] args) {
        Topic topic1 = new Topic();
        Topic topic2 = new Topic();

        List<Topic> topics = new ArrayList<>(Arrays.asList(topic1, topic2));

        Producer producer = new Producer(topics);
        Consumer consumer = new Consumer(topics);

        Thread producerThread = new Thread(producer);
        producerThread.setName("PRODUCER");
        producerThread.start();

        Thread consumerThread = new Thread(consumer);
        consumerThread.setName("CONSUMER");
        consumerThread.start();

    }
}
