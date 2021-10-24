package com.epam.l1.multithreading.task6.classic;

import java.util.List;

public class Data {
    private List<Integer> integers;

    private boolean writable = true;

    public Data(List<Integer> integers) {
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
