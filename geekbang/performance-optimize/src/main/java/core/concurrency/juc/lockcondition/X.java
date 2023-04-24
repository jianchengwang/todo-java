package core.concurrency.juc.lockcondition;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */

class X {
    private final Lock rtl =
            new ReentrantLock();
    int value;
    public int get() {
        // 获取锁
        rtl.lock();
        try {
            return value;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }

    public void addOne() {
        // 获取锁
        rtl.lock();
        try {
            value+=1;
        } finally {
            // 保证锁能释放
            rtl.unlock();
        }
    }
}
