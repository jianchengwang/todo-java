package core.concurrency.basic.oop;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */

public class SafeWM {
    // 库存上限
    private final AtomicLong upper =
            new AtomicLong(0);
    // 库存下限
    private final AtomicLong lower =
            new AtomicLong(0);
    // 设置库存上限
    void setUpper(long v){
        // 竞态条件加锁保证原子性
        synchronized (this) {
            // 检查参数合法性
            if (v < lower.get()) {
                throw new IllegalArgumentException();
            }
        }
        upper.set(v);
    }
    // 设置库存下限
    void setLower(long v){
        synchronized (this) {
            // 检查参数合法性
            if (v > upper.get()) {
                throw new IllegalArgumentException();
            }
        }
        lower.set(v);
    }
    // 省略其他业务代码
}
