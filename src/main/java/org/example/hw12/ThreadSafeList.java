package org.example.hw12;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeList<T> {
    private List<T> list = new ArrayList<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock readLock = lock.readLock();

    private Lock writeLock = lock.writeLock();

    public void add(T item) {
        writeLock.lock();
        try {
            list.add(item);
        } finally {
            writeLock.unlock();
        }
    }

    public T get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }
    }

    public T remove(int index) {
        writeLock.lock();
        try {
            return list.remove(index);
        } finally {
            writeLock.unlock();
        }
    }
}
