package todo.java.geekbang.designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicLong;

/** 频繁加锁、释放锁及并发度低等问题，会导致性能瓶颈
 * @author jianchengwang
 * @date 2023/6/14
 */
public class IdGeneratorLazy {
    private AtomicLong id = new AtomicLong(0);

    private static IdGeneratorLazy instance;

    private IdGeneratorLazy() {}

    public static synchronized IdGeneratorLazy getInstance() {
        if (instance == null) {
            instance = new IdGeneratorLazy();
        }
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
