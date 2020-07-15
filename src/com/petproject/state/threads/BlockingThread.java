package com.petproject.state.threads;

import com.petproject.LogService;

import java.util.logging.Logger;

public class BlockingThread extends Thread {

    private Object monitor;
    private Logger log;

    public BlockingThread(LogService logService, Object monitor) {
        this.monitor = monitor;
        log = logService.log;
    }

    @Override
    public void run() {
        synchronized (monitor) {
            log.info("Acquire object monitor and sleep fo 20 seconds");
            try {
                sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
