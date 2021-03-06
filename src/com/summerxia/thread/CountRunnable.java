package com.summerxia.thread;

import java.util.concurrent.TimeUnit;

public class CountRunnable implements Runnable{

    private static Count count = new Count();
    private static volatile boolean isCancel = false;
    private final int id;
    private int num;

    public CountRunnable(int id){
        Thread.currentThread().interrupt();
        this.id = id;
    }

    @Override
    public void run() {
        while (!isCancel){
            synchronized (this){
                ++num;
            }
            int increment = count.increment();
            System.out.println("#"+id+", count="+increment);
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(this+" #"+id+", total num="+num);
    }

    public static void cancel(){
        isCancel = true;
    }
}
