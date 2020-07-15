package com.petproject.thread_instance;

import com.petproject.LogService;

import java.util.logging.Logger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class WorkingThread extends Thread {

    private Logger log;

    public WorkingThread(LogService logService) {
        this.log = logService.log;

    }

    @Override
    public void run() {
        log.info("Start working thread with heavy calculation");

        while (!isInterrupted()) {
            long start = System.currentTimeMillis();

            LongStream.range(-2_000_000_000L, 2_000_000_000L)
                    .reduce(Long::sum);
            log.info( " Evaluation time: " + (System.currentTimeMillis() - start) + " millis");

            try {
                sleep(5000);
            } catch (InterruptedException e) {
                log.info("Thread interrupted");
                interrupt();
            }
        }
    }
}
