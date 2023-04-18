package multithread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
public class LockTest {
    private static int count;

    private static int num = 1;
    private static int maxValue = 100000;
    private final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Counter lockTest = new LockTest().new Counter();
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                for (int cur = 0; cur < maxValue; cur++) {
                    lockTest.add();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("LockTest:" + (endTime - startTime) + ", count" + count);

    }

    class Counter {
        public void add() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

}
