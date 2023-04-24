package core.concurrency.juc.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class Account1 {

    static int count;
    //初始化信号量
    static final Semaphore s
            = new Semaphore(1);
    //用信号量保证互斥
    static void addOne() throws InterruptedException {
        s.acquire();
        try {
            count+=1;
        } finally {
            s.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                try {
                    Account1.addOne();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            thread.join();
        }
        System.out.println(count);
    }
}
