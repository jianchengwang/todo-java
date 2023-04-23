package core.concurrency.mutex;

import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */
public class Account2 {
    private int balance;

    public Account2(int balance) {
        this.balance = balance;
    }

    // 转账 not thread safe，问题就出在 this 这把锁上，this 这把锁可以保护自己的余额 this.balance，却保护不了别人的余额 target.balance
    synchronized void transferUnsafe(Account2 target, int amt) {
        if (this.balance > amt) {
            this.balance -= amt;
            target.balance += amt;
        }
    }

    void transferSafe(Account2 target, int amt) {
        synchronized (Account2.class) {
            if (this.balance > amt) {
                this.balance -= amt;
                target.balance += amt;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10000);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Account2 a = new Account2(10000);
        Account2 b = new Account2(0);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(() -> {
//                a.transferUnsafe(b, 1);
//                b.transferUnsafe(a, 1);
                a.transferSafe(b, 1);
                b.transferSafe(a, 1);
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println(a.balance);
        System.out.println(b.balance);
    }
}
