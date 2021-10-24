package com.epam.l1.multithreading.task6;

import java.util.concurrent.BlockingQueue;

public class ConcurrentData {
    private BlockingQueue<Integer> integers;

    public ConcurrentData(BlockingQueue<Integer> integers) {
        this.integers = integers;
    }

    public void add(int number){
        integers.add(number);
    }

    public BlockingQueue<Integer> getIntegers() {
        return integers;
    }

    public void setIntegers(BlockingQueue<Integer> integers) {
        this.integers = integers;
    }
}
