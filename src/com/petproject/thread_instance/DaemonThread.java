package com.petproject.thread_instance;

import com.petproject.LogService;

import java.util.logging.Logger;

public class DaemonThread extends Thread {

    private Logger log;

    public DaemonThread(LogService logService) {
        this.log = logService.log;
        setDaemon(true);
    }

    @Override
    public void run() {
        log.info("Hello from Daemon thread");
        log.info("Start background task");
        int counter = 0;
        while(!isInterrupted()) {
            counter++;

            log.info("Do some background work");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                log.info("Thread interrupted");
                interrupt();
            }
        }
        log.info("End background task " + counter);
    }
}
