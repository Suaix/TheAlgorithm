package com.summerxia.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadMain {
    public static void main(String[] args) {
//        MutexEvenGenerator evenGenerator = new MutexEvenGenerator();
//        EvenChecker.test(evenGenerator);
        ExecutorService threadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            threadPool.execute(new CountRunnable(i));
        }
        threadPool.shutdown();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        CountRunnable.cancel();
    }
}
