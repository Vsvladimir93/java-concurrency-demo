package com.petproject.thread_instance;

import com.petproject.LogService;

import java.util.logging.Logger;

public class ThreadInstance extends Thread {

    private Logger logger;

    public ThreadInstance(LogService logService) {
        logger = logService.log;
    }

    @Override
    public void run() {
        logger.info("Hello from Thread instance");
    }

}
