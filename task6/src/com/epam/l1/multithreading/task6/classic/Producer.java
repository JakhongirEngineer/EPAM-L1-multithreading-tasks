package com.epam.l1.multithreading.task6.classic;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{
    private Data data;

    public Producer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int randomInteger = ThreadLocalRandom.current().nextInt(0, 1001);
                System.out.println(Thread.currentThread().getName() + " adds: " + randomInteger + " to the data");
                data.add(randomInteger);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
