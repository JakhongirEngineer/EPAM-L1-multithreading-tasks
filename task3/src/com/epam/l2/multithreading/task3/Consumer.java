package com.epam.l2.multithreading.task3;

import java.util.ArrayList;
import java.util.List;

public class Consumer implements Runnable{
    private final List<Topic> topics;

    public Consumer(){
        this.topics = new ArrayList<>();
    }

    public Consumer(List<Topic> topics){
        this.topics = topics;
    }

    public void addTopic(Topic topic){
        this.topics.add(topic);
    }

    public void removeTopic(Topic topic){
        this.topics.remove(topic);
    }

    @Override
    public void run() {
        while (true){
            topics.forEach(topic -> System.out.println(topic.consume()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
