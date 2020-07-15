package com.petproject.concurrent_modifications;

import java.util.logging.Logger;

public class ConcurrentModificationExample {

    public void start(Logger log) throws Exception {
        Account account = new Account(100000);
        log.info("Begin balance = " + account.getBalance());

        Thread withdrawThread = new WithdrawThread(account);
        Thread depositThread = new DepositThread(account);
        withdrawThread.start();
        depositThread.start();

        withdrawThread.join();
        depositThread.join();

        log.info("End balance = " + account.getBalance());
    }

    private static class WithdrawThread extends Thread {

        private final Account account;

        private WithdrawThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20000; ++i) {
                account.withdraw(1);
            }
        }
    }


    private static class DepositThread extends Thread {

        private final Account account;

        private DepositThread(Account account) {
            this.account = account;
        }

        @Override
        public void run() {
            for (int i = 0; i < 20000; ++i) {
                account.deposit(1);
            }
        }
    }

}
