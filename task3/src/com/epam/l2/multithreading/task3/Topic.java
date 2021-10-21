package com.epam.l2.multithreading.task3;

import java.util.LinkedList;
import java.util.Queue;

public class Topic {
    private Queue<String> queue;
    private boolean writable;
    public Topic(){
        this.queue = new LinkedList<>();
        writable = true;
    }

    public synchronized void produce(String message){
        while (!writable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " produces: " + message);
        queue.add(message);
        writable = false;
        notifyAll();
    }

    public synchronized String consume(){
        while (writable){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
        try {
            System.out.println(Thread.currentThread().getName() + " consumes: " + queue.peek());
            return queue.poll();
        } finally {
            writable = true;
            notifyAll();
        }
    }
}
