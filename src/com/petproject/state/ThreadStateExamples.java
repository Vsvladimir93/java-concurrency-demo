package com.petproject.state;

import com.petproject.LogService;
import com.petproject.state.threads.BlockedThread;
import com.petproject.state.threads.BlockingThread;
import com.petproject.state.threads.SleepingThread;
import com.petproject.state.threads.WaitingThread;
import com.petproject.thread_instance.WorkingThread;

import java.util.logging.Logger;

public class ThreadStateExamples {

    private Logger log;
    private LogService logService;

    public ThreadStateExamples(LogService logService) {
        log = logService.log;
        this.logService = logService;
    }

    public void runHighLoadedThread() {
        new WorkingThread(logService).start();
    }

    public void printAllStates() {
        newThreadState();
        runnableThreadState();
        waitingThreadState();
        timeWaitingThreadState();
        blockedThreadState();
    }

    private void newThreadState() {
        /** Not started yet */
        log.info(new Thread().getState().name());
    }

    private void runnableThreadState() {
        /** Started thread */
        Thread runnableThread = new Thread();
        runnableThread.start();
        log.info(runnableThread.getState().name());
    }

    private void waitingThreadState() {
        /** Waiting thread */
        Thread waitingThread = new WaitingThread();
        waitingThread.start();
        delay();
        log.info(waitingThread.getState().name());
    }

    private void timeWaitingThreadState() {
        /** Time Waiting thread */
        Thread sleepingThread = new SleepingThread();
        sleepingThread.start();
        delay();
        log.info(sleepingThread.getState().name());
    }

    private void blockedThreadState() {

        Object anyObjectCanBeMonitor = new Object();

        /** This thread acquire lock on object monitor and do some work */
        Thread blockingThread = new BlockingThread(logService, anyObjectCanBeMonitor);
        blockingThread.start();

        delay();

        /**
         * Another thread try to acquire lock on object monitor,
         * but that object already locked by another thread
         */
        Thread blockedThread = new BlockedThread(logService, anyObjectCanBeMonitor);
        blockedThread.start();

        delay();

        log.info(blockedThread.getState().name());
    }

    private void delay() {
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
