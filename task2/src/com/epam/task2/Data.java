package com.epam.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock ensures that once a new number is added,
 * both reader threads read the value without skipping
 */
public class Data {
    private List<Integer> numbers = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    public  void write(int number){
        writeLock.lock();
        try {
            numbers.add(number);
            System.out.println(number + " is added into the list by " + Thread.currentThread().getName() );
        } finally {
            writeLock.unlock();
        }
    }

    public  List<Integer> read(){
        readLock.lock();
        System.out.println("list is read by the thread: " + Thread.currentThread().getName());
        try {
            return numbers;
        } finally {
            readLock.unlock();
        }
    }
}
