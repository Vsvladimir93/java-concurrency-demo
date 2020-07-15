package com.petproject.synchronization;

import java.util.logging.Logger;

public class DeadlockExample {

        public void start(Logger log) {
            final Object resource1 = new Object();
            final Object resource2 = new Object();

            Thread t1 = new Thread() {
                public void run() {
                    synchronized (resource1) {
                        log.info("Thread 1: locked resource 1");

                        try { Thread.sleep(100);} catch (Exception e) {}

                        synchronized (resource2) {
                            log.info("Thread 1: locked resource 2");
                        }
                    }
                }
            };

            Thread t2 = new Thread() {
                public void run() {
                    synchronized (resource2) {
                        log.info("Thread 2: locked resource 2");

                        try { Thread.sleep(100);} catch (Exception e) {}

                        synchronized (resource1) {
                            log.info("Thread 2: locked resource 1");
                        }
                    }
                }
            };


            t1.start();
            t2.start();
        }



}
