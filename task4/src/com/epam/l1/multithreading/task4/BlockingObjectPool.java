package com.epam.l1.multithreading.task4;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingObjectPool<T> {
    private int size;
    private BlockingQueue<T> queue;

    public BlockingObjectPool(int size){
        this.size = size;
        queue = new LinkedBlockingQueue<>(size);
    }

    public T get() throws InterruptedException {
      return queue.take();
    }

    public void take(T t) throws InterruptedException {
        queue.put(t);
    }
}
