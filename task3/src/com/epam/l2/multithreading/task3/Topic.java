package com.epam.l2.multithreading.task3;

import java.util.LinkedList;
import java.util.Queue;

public class Topic {
    private Queue<String> queue;
    /**
     *  writable defines whether a topic is accessible for modification.
     */
    private boolean writable;
    public Topic(){
        this.queue = new LinkedList<>();
        writable = true;
    }

    /**
     * @param message needs to be added into the queue.
     * while writable is false, the thread that wants to
     * access this method waits.
     * Once writable becomes true, queue is allowed to be modified, and
     * produced message is added to it, set writable to false, and
     * wakes other threads up.
     */
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

    /**
     * @return message is returned
     */
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
            return queue.poll();
        } finally {
            writable = true;
            notifyAll();
        }
    }
}
