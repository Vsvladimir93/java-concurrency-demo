package com.petproject.thread_instance;

import com.petproject.LogService;

import java.util.logging.Logger;

public class RunnableImplementation implements Runnable {

    private Logger logger;

    public RunnableImplementation(LogService logService) {
        logger = logService.log;
    }

    @Override
    public void run() {
        logger.info("Hello from Runnable implementation");
    }

}
