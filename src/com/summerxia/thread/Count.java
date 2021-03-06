package com.summerxia.thread;

public class Count {
    private int count=0;

    public synchronized int increment(){
        return ++count;
    }

    public synchronized int value(){
        return count;
    }
}
