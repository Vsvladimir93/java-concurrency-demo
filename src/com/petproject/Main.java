package com.petproject;

import com.petproject.concurrent_modifications.ConcurrentModificationExample;
import com.petproject.state.ThreadStateExamples;
import com.petproject.synchronization.DeadlockExample;
import com.petproject.synchronization.ThreadSynchronizedExample;
import com.petproject.synchronization.WaitNotifyExample;
import com.petproject.thread_instance.DaemonThread;
import com.petproject.thread_instance.RunnableImplementation;
import com.petproject.thread_instance.ThreadInstance;
import com.petproject.thread_instance.WorkingThread;

import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class Main {

    private static LogService logService = new LogService();

    public static void main(String[] args) throws Exception {

//        runnableLambda();
//        runAndStart();

//        threadInterrupt();

//        ThreadStateExamples stateExamples = new ThreadStateExamples(logService);
//        stateExamples.printAllStates();
//        stateExamples.runHighLoadedThread();

//        ThreadSynchronizedExample synchExamples = new ThreadSynchronizedExample(logService);
////        synchExamples.synchronizedOnStaticClass();
//        synchExamples.synchronizedOnMethod();

//        WaitNotifyExample example = new WaitNotifyExample(logService);
//        example.work();

//        DeadlockExample example = new DeadlockExample();
//        example.start(logService.log);

//        ConcurrentModificationExample example = new ConcurrentModificationExample();
//        example.start(logService.log);
    }

    private static void runnableLambda() {
        new Thread(() -> {
            logService.log.info("Hello from runnable lambda ");
        }).start();
        new Thread(new RunnableImplementation(logService)).start();
        new Thread(new ThreadInstance(logService)).start();
    }

    private static void runAndStart() {
        new Thread(() -> {
            logService.log.info("Hello from run method");
        }).run();
        new Thread(() -> {
            logService.log.info("Hello from start method");
        }).start();
    }

    private static void threadInterrupt() {
        new DaemonThread(logService).start();
        Thread th = new WorkingThread(logService);
        th.start();

        try {
            sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logService.log.info("Interrupt");
        th.interrupt();
    }





}
