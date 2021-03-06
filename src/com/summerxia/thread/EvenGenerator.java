package com.summerxia.thread;

public class EvenGenerator extends IntGenerator{
    private int currentValue = 0;
    @Override
    public synchronized int next() {
        ++currentValue;
        Thread.yield();
        ++currentValue;
        return currentValue;
    }
}
