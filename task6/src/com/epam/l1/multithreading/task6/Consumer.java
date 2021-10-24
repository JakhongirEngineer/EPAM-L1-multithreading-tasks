package com.epam.l1.multithreading.task6;

public class Consumer implements Runnable{
    private DataClassic dataClassic;

    public Consumer(DataClassic dataClassic) {
        this.dataClassic = dataClassic;
    }

    @Override
    public void run() {
        while (true){
            try {
                Integer sum = dataClassic.getIntegers().stream().reduce(0, (currSum, currNum) -> currSum + currNum);
                System.out.println(Thread.currentThread().getName() + " calculates sum: " + sum);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public DataClassic getData() {
        return dataClassic;
    }

    public void setData(DataClassic dataClassic) {
        this.dataClassic = dataClassic;
    }
}
