package com.epam.l1.multithreading.task1;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverForCollectionsSynchronizedMap {
    public static void main(String[] args) {
        Map<Integer,Integer> map = Collections.synchronizedMap(new ConcurrentHashMap<>());
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
