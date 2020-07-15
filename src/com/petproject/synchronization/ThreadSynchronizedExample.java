package com.petproject.synchronization;

import com.petproject.LogService;
import com.petproject.thread_instance.WorkingThread;

import java.util.logging.Logger;
import java.util.stream.LongStream;

public class ThreadSynchronizedExample {

    /**
     * Every object is monitor
     */
    private Object monitor;

    private Logger log;
    private LogService logService;

    public ThreadSynchronizedExample(LogService logService) {
        log = logService.log;
        this.logService = logService;
    }

    public void synchronizedOnStaticClass() {
        Thread first = new Thread(() -> StaticClassMonitor.doSomeWork());
        Thread second = new Thread(() -> StaticClassMonitor.doAnotherWork());

        first.start();
        second.start();

    }

    public void synchronizedOnMethod() {
        ClassMonitor simpleClass = new ClassMonitor();
        ClassMonitor simpleClass2 = new ClassMonitor();
        Thread first = new Thread(() -> simpleClass.doSomeWork());
        Thread second = new Thread(() -> simpleClass2.doSomeWork());
//        Thread third = new Thread(() -> simpleClass.doSomeAnotherWork());

        first.start();
        second.start();
//        third.start();

    }

    private synchronized void synchronizedMethod() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notify();
        notifyAll();
    }

    private void synchronizedBlock() {
        /** Synchronized block */
        synchronized (monitor) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notify();
            notifyAll();
        }
    }

    private void delay() {
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class StaticClassMonitor {
        synchronized static void doSomeWork() {
            /** Do heavy calculation 5 times in row */
            for (int i = 0; i < 3; i++) {
                LongStream.range(-2_000_000_000L, 2_000_000_000L)
                        .reduce(Long::sum);
            }
        }

        synchronized static void doAnotherWork() {
            /** Do heavy calculation 5 times in row */
            for (int i = 0; i < 3; i++) {
                LongStream.range(-2_000_000_000L, 2_000_000_000L)
                        .reduce(Long::sum);
            }
        }
    }

    class ClassMonitor {

        Object monitor = new Object();

        synchronized void doSomeWork() {
            /** Do heavy calculation 5 times in row */
            for (int i = 0; i < 3; i++) {
                LongStream.range(-2_000_000_000L, 2_000_000_000L)
                        .reduce(Long::sum);
            }
        }

        synchronized void doAnotherWork() {
            /** Do heavy calculation 5 times in row */
            for (int i = 0; i < 3; i++) {
                LongStream.range(-2_000_000_000L, 2_000_000_000L)
                        .reduce(Long::sum);
            }
        }

        void doSomeAnotherWork() {
            synchronized (monitor) {
                /** Do heavy calculation 5 times in row */
                for (int i = 0; i < 3; i++) {
                    LongStream.range(-2_000_000_000L, 2_000_000_000L)
                            .reduce(Long::sum);
                }
            }
        }
    }

}
