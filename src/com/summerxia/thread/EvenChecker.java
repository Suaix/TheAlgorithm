package com.summerxia.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EvenChecker implements Runnable{
    private IntGenerator generator;
    private final int id;
    public EvenChecker(IntGenerator g, int ident){
        generator = g;
        id = ident;
    }

    @Override
    public void run() {
        while (!generator.isCanceled()){
            int var = generator.next();
            if (var % 2 != 0){
                System.out.println(var + " is not even in " + "#" + id);
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator, int count){
        System.out.println("Press Control-C to exit");
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < count; i++) {
            executor.execute(new EvenChecker(generator, i));
        }
        executor.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator, 10);
    }
}
