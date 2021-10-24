package com.epam.l1.multithreading.task6.classic;

public class Consumer implements Runnable{
    private Data data;

    public Consumer(Data data) {
        this.data = data;
    }

    @Override
    public void run() {
        while (true){
            try {
                Integer sum = data.getIntegers().stream().reduce(0, (currSum, currNum) -> currSum + currNum);
                System.out.println(Thread.currentThread().getName() + " calculates sum: " + sum);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
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
