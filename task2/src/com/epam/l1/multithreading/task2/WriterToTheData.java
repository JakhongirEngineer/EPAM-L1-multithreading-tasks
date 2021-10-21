package com.epam.l1.multithreading.task2;

import java.util.concurrent.ThreadLocalRandom;

public class WriterToTheData implements Runnable{
    private Data data;
    public WriterToTheData(Data data){
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            int number = ThreadLocalRandom.current().nextInt(0,101);
            data.write(number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
