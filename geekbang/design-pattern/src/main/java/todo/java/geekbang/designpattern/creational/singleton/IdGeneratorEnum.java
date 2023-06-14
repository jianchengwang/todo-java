package todo.java.geekbang.designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public enum IdGeneratorEnum {
    INSTANCE;
    private AtomicLong id = new AtomicLong(0);

    public long getId() {
        return id.incrementAndGet();
    }
}
