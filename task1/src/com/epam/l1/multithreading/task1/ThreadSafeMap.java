package com.epam.l1.multithreading.task1;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ThreadSafeMap<K,V> implements Map<K,V> {

    private final Map<K,V> map = new HashMap<>();
//    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
//    private final Lock readLock = readWriteLock.readLock();
//    private final Lock writeLock = readWriteLock.writeLock();

    @Override
    public  int size() {
//        try {
//            readLock.lock();
//            return map.size();
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.size();
        }
    }

    @Override
    public  boolean isEmpty() {
//        try {
//            readLock.lock();
//            return map.isEmpty();
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.isEmpty();
        }
    }

    @Override
    public  boolean containsKey(Object key) {
//        try {
//            readLock.lock();
//            return map.containsKey(key);
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.containsKey(key);
        }
    }

    @Override
    public  boolean containsValue(Object value) {
//        try {
//            readLock.lock();
//            return map.containsValue(value);
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.containsValue(value);
        }
    }

    @Override
    public  V get(Object key) {
//        try {
//            readLock.lock();
//            return map.get(key);
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.get(key);
        }
    }

    @Override
    public  Object put(Object key, Object value) {
//        try {
//            writeLock.lock();
//            return map.put((K)key,(V) value);
//        } finally {
//            writeLock.unlock();
//        }
        synchronized (map){
            return map.put((K)key,(V)value);
        }
    }

    @Override
    public  V remove(Object key) {
//        try {
//            writeLock.lock();
//            return map.remove(key);
//        } finally {
//            writeLock.unlock();
//        }
        synchronized (map){
            return map.remove(key);
        }
    }

    @Override
    public  void putAll(Map m) {
//        try {
//            writeLock.lock();
//            map.putAll(m);
//        } finally {
//            writeLock.unlock();
//        }
        synchronized (map){
            map.putAll(m);
        }
    }

    @Override
    public  void clear() {
//        try {
//            writeLock.lock();
//            map.clear();
//        } finally {
//            writeLock.unlock();
//        }
        synchronized (map){
            map.clear();
        }
    }

    @Override
    public  Set<K> keySet() {
//        try {
//            readLock.lock();
//            return map.keySet();
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.keySet();
        }
    }

    @Override
    public  Collection<V> values() {
//        try {
//            readLock.lock();
//            return map.values();
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.values();
        }
    }

    @Override
    public  Set<Entry<K,V>> entrySet() {
//        try {
//            readLock.lock();
//            return map.entrySet();
//        } finally {
//            readLock.unlock();
//        }
        synchronized (map){
            return map.entrySet();
        }
    }
}
