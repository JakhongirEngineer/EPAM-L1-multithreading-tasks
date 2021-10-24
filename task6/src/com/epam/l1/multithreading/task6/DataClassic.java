package com.epam.l1.multithreading.task6;

import java.util.List;

public class DataClassic {
    private List<Integer> integers;

    private boolean writable = true;

    public DataClassic(List<Integer> integers) {
        this.integers = integers;
    }

    public synchronized List<Integer> getIntegers() throws InterruptedException {
        while (writable){
            wait();
        }
        try {
            return integers;
        } finally {
            writable = true;
            notifyAll();
        }
    }

    public synchronized void add(int number) throws InterruptedException {
        while (!writable){
            wait();
        }
        try {
           this.integers.add(number);
        } finally {
            writable = false;
            notifyAll();
        }
    }

    public synchronized void setIntegers(List<Integer> integers) throws InterruptedException {
        while (!writable){
            wait();
        }
        try {
            this.integers = integers;
        } finally {
            writable = false;
            notifyAll();
        }
    }
}
