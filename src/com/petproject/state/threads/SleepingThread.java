package com.petproject.state.threads;

public class SleepingThread extends Thread {

    @Override
    public void run() {
        try {
            sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
