package com.summerxia.thread.piped;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sender implements Runnable{
    private Random random = new Random(47);
    private PipedWriter out = new PipedWriter();
    public PipedWriter getPipedWriter(){
        return out;
    }

    @Override
    public void run() {
        try {
            while (true){
                for (char c = 'A'; c < 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
