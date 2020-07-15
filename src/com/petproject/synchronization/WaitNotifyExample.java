package com.petproject.synchronization;

import com.petproject.LogService;

import java.util.logging.Logger;

public class WaitNotifyExample {

    private Logger log;

    public WaitNotifyExample(LogService logService) {
        log = logService.log;
    }

    public void work() {
        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }


    class Store {

        private int product=0;
        public synchronized void get() {
            while (product<1) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                }
            }
            product--;
            log.info("Покупатель купил 1 товар");
            log.info("Товаров на складе: " + product);
            notify();
        }
        public synchronized void put() {
            while (product>=3) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                }
            }
            product += 2;
            log.info("Производитель добавил 1 товар");
            log.info("Товаров на складе: " + product);
            notify();
        }

    }

    class Producer extends Thread {

        Store store;
        Producer(Store store){
            this.store=store;
        }

        @Override
        public void run() {
            while(!isInterrupted()) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    interrupt();
                } finally {
                    store.put();
                }
              }
        }
    }

    class Consumer implements Runnable{

        Store store;
        Consumer(Store store){
            this.store=store;
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.currentThread().sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    store.get();
                }
            }
        }
    }

}
