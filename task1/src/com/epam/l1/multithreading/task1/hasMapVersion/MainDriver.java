package com.epam.l1.multithreading.task1.hasMapVersion;

import java.util.HashMap;
import java.util.Map;

// works fine for a while until ConcurrentModificationException rises.
// After the exception occurs, one of the threads stops modifying the map
public class MainDriver {
    public static void main(String[] args) {
        Map<Integer,Integer> map = new HashMap<>();
        WriterToTheMap writerToTheMap = new WriterToTheMap(map);
        ValueSummerOfTheMap valueSummerOfTheMap = new ValueSummerOfTheMap(map);

        Thread writerThread = new Thread(writerToTheMap);
        writerThread.setName("WRITER_THREAD");
        writerThread.start();

        Thread summerThread = new Thread(valueSummerOfTheMap);
        summerThread.setName("SUMMER_THREAD");
        summerThread.start();
    }
}
