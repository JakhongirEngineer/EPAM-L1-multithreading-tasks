package com.epam.l1.multithreading.task1;

import java.util.Map;

public class ValueSummerOfTheMap implements Runnable{
    private Map<Integer,Integer> map;

    public ValueSummerOfTheMap(Map<Integer,Integer> map){
        this.map = map;
    }

    @Override
    public void run() {
        while (true){
            Integer sum = map.keySet()
                    .stream()
                    .reduce(0, Integer::sum);
            System.out.println(Thread.currentThread().getName() + " sum: " + sum);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
