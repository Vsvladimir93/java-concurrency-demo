package com.petproject.state.threads;

import com.petproject.LogService;

import java.util.logging.Logger;

public class BlockedThread extends Thread {

    private Object monitor;
    private Logger log;

    public BlockedThread(LogService logService, Object monitor) {
        this.monitor = monitor;
        log = logService.log;
    }

    @Override
    public void run() {
        log.info("Try to acquire object monitor");
        synchronized (monitor) {
            log.info("Acquire object monitor");
        }
    }
}
