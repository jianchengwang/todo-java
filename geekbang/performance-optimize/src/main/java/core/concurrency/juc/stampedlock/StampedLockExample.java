package core.concurrency.juc.stampedlock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class StampedLockExample {

    public void readTemplate() {
        final StampedLock sl =
                new StampedLock();

        // 乐观读
        long stamp =
                sl.tryOptimisticRead();
        // 读入方法局部变量
        // 校验stamp
        if (!sl.validate(stamp)){
            // 升级为悲观读锁
            stamp = sl.readLock();
            try {
                // 读入方法局部变量
            } finally {
                //释放悲观读锁
                sl.unlockRead(stamp);
            }
        }
        //使用方法局部变量执行业务操作
    }

    public void writeTemplate() {
        final StampedLock sl =
                new StampedLock();
        long stamp = sl.writeLock();
        try {
            // 写共享变量
        } finally {
            sl.unlockWrite(stamp);
        }
    }
}
