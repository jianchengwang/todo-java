package core.concurrency.deadlock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */

class Account3 {
    private int balance;
    // 转账
    void transfer(Account3 target, int amt){
        // 锁定转出账户
        synchronized(this) {
            // 锁定转入账户
            synchronized(target) {
                if (this.balance > amt) {
                    this.balance -= amt;
                    target.balance += amt;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(200);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Account3 a = new Account3();
        Account3 b = new Account3();
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                a.transfer(b, 10);
                downLatch.countDown();
            });
            executorService.execute(() -> {
                b.transfer(a, 10);
                downLatch.countDown();
            });
        }
        downLatch.await();
        executorService.shutdown();
        System.out.println(a.balance);
        System.out.println(b.balance);
    }
}
