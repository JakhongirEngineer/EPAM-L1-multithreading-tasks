package com.epam.l1.multithreading.task1;

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class WriterToTheMap implements Runnable{
    private Map<Integer,Integer> map;

    public WriterToTheMap(Map<Integer,Integer> map){
        this.map = map;
    }

    @Override
    public void run() {

        while (true){
            int number = generateUniqueNumber(map);
            System.out.println(Thread.currentThread().getName() + " puts: " + number);
            map.put(number, number);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private int generateUniqueNumber(Map<Integer,Integer> map){
        int number = ThreadLocalRandom.current().nextInt();
        if (map.containsKey(number)){
            number = generateUniqueNumber(map);
        }
        return number;
    }
}
