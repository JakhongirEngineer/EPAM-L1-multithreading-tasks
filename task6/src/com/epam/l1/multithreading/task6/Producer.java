package com.epam.l1.multithreading.task6;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{
    private DataClassic dataClassic;

    public Producer(DataClassic dataClassic) {
        this.dataClassic = dataClassic;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int randomInteger = ThreadLocalRandom.current().nextInt(0, 1001);
                System.out.println(Thread.currentThread().getName() + " adds: " + randomInteger + " to the data");
                dataClassic.add(randomInteger);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
