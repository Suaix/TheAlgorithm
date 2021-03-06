package com.summerxia.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MutexEvenGenerator extends IntGenerator{
    private int currentValue = 0;
    private final Lock lock = new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try {
            ++currentValue;
            Thread.yield();
            ++currentValue;
            return currentValue;
        } finally {
            lock.unlock();
        }
    }
}
