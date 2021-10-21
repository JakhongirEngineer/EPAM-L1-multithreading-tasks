package com.epam.l2.multithreading.task3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainDriver {
    public static void main(String[] args) {
        Topic topic1 = new Topic();
        Topic topic2 = new Topic();
        Topic topic3 = new Topic();
        Topic topic4 = new Topic();


        List<Topic> topics1 = new ArrayList<>(Arrays.asList(topic1, topic2));
        List<Topic> topics2 = new ArrayList<>(Arrays.asList(topic3,topic4));

        Producer producer1 = new Producer(topics1);
        Consumer consumer1 = new Consumer(topics1);

        Thread producerThread = new Thread(producer1);
        producerThread.setName("PRODUCER1");
        producerThread.start();

        Thread consumerThread = new Thread(consumer1);
        consumerThread.setName("CONSUMER1");
        consumerThread.start();


        Producer producer2 = new Producer(topics2);
        Consumer consumer2 = new Consumer(topics2);

        Thread producerThread2 = new Thread(producer2);
        producerThread2.setName("PRODUCER2");
        producerThread2.start();

        Thread consumerThread2 = new Thread(consumer2);
        consumerThread2.setName("CONSUMER2");
        consumerThread2.start();

    }
}
