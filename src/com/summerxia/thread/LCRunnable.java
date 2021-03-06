package com.summerxia.thread;

public class LCRunnable implements Runnable{
    private static int taskNum = 0;
    private final int num = taskNum++;
    private final static int TOTAL_COUNT = 3;
    public LCRunnable(){
        System.out.println("启动任务："+num);
    }
    @Override
    public void run() {
        for (int i = 0; i < TOTAL_COUNT; i++) {
            System.out.println("任务#"+num+",完成算法题："+i+"例");
            Thread.yield();
        }
        System.out.println("任务#"+num+"执行完任务，退出！");
    }
}
