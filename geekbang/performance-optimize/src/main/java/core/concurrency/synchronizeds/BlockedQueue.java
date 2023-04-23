package core.concurrency.synchronizeds;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */

public class BlockedQueue<T>{
    int maxLength = 10;
    final AtomicInteger length = new AtomicInteger(0);
    final Lock lock =
            new ReentrantLock();
    // 条件变量：队列不满
    final Condition notFull =
            lock.newCondition();
    // 条件变量：队列不空
    final Condition notEmpty =
            lock.newCondition();

    // 入队
    void enq(T x) throws InterruptedException {
        lock.lock();
        try {
            while (length.get() >= maxLength){
                // 等待队列不满
                System.out.println("full:" + length.get());
                notFull.await();
            }
            // 省略入队操作...
            System.out.println("enq:" + length.incrementAndGet());
            //入队后,通知可出队
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }
    // 出队
    void deq(){
        lock.lock();
        try {
            while (length.get() < maxLength){
                // 等待队列不空
                System.out.println("notEmpty:" + length.get());
                notEmpty.await();
            }
            // 省略出队操作...
            System.out.println("deq:" + length.decrementAndGet());
            //出队后，通知可入队
            notFull.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        BlockedQueue<String> blockedQueue = new BlockedQueue<>();
        try {
            ExecutorService producer = Executors.newFixedThreadPool(5);
            ExecutorService consumer = Executors.newFixedThreadPool(5);
            CountDownLatch countDownLatch = new CountDownLatch(10);
            for (int i = 0; i < 10; i++) {
                producer.execute(()->{
                    try {
                        blockedQueue.enq("1");
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
                consumer.execute(()->{
                    try {
                        blockedQueue.deq();
                        countDownLatch.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            countDownLatch.await();
            producer.shutdown();
            consumer.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
