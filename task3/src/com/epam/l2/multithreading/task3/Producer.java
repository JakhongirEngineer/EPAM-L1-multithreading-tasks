package com.epam.l2.multithreading.task3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{
    private  List<Topic> topics;
    private final String[] words = ("Kafka clusters can have one or more brokers " +
            "Brokers can host multiple replicas Topics can have one or more partitions " +
            "A broker can host zero or one replica per partition A partition has one leader " +
            "replica and zero or more follower replicas").split(" ");

    public Producer(){
        this.topics = new ArrayList<>();
    }

    public Producer(List<Topic> topics){
        this.topics = topics;
    }


    public void addTopic(Topic topic){
        topics.add(topic);
    }
    public void removeTopic(Topic topic){
        topics.remove(topic);
    }

    @Override
    public void run() {
        while (true){
            String message = generateRandomMessage();
            try {
                topics.forEach(topic -> topic.produce(message));
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private String generateRandomMessage(){
        return words[ThreadLocalRandom.current().nextInt(0, words.length)] +
                " " + words[ThreadLocalRandom.current().nextInt(0, words.length)] +
                " " + words[ThreadLocalRandom.current().nextInt(0, words.length)];
    }
}
