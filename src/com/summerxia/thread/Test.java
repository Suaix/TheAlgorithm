package com.summerxia.thread;

import com.summerxia.thread.piped.Receiver;
import com.summerxia.thread.piped.Sender;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    public static void main(String[] args) throws InterruptedException, IOException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(sender);
        service.execute(receiver);
        TimeUnit.SECONDS.sleep(5);
        service.shutdownNow();
    }
    static class WaxOn implements Runnable{
        private Car car;
        private int id;
        public WaxOn(Car car, int id){
            this.car = car;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("id#" + id + ",wax on!");
                    TimeUnit.MILLISECONDS.sleep(200);
                    car.waxed();
                    car.waitForBuffing();
                }
            } catch (InterruptedException e) {
                System.out.println("id#"+id+", waxOn interrupted");
//                e.printStackTrace();
            }
        }
    }

    static class Buffed implements Runnable{
        private Car car;
        private int id;

        public Buffed(Car car, int id){
            this.car = car;
            this.id = id;
        }

        @Override
        public void run() {
            try {
                while (!Thread.interrupted()) {
                    System.out.println("id#" + id + ", buffing!");
                    TimeUnit.MILLISECONDS.sleep(500);
                    car.buffed();
                    car.waitForWaxing();
                }
            } catch (InterruptedException e){
                System.out.println("id#"+id+", buffed interrupted");
//                e.printStackTrace();
            }
        }
    }
    static class Car{
        private boolean waxOn = false;
        public synchronized void waxed(){
            System.out.println("car is waxed");
            waxOn = true;
            notifyAll();
        }

        public synchronized void waitForWaxing() throws InterruptedException {
            while (!waxOn){
                wait();
                System.out.println("car wait buffing....");
            }
        }

        public synchronized void buffed(){
            System.out.println("car is buffed");
            waxOn = false;
            notifyAll();
        }

        public synchronized void waitForBuffing() throws InterruptedException {
            while (waxOn){
                wait();
                System.out.println("car wait waxing....");
            }
        }
    }

}
