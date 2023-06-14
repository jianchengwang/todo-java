package todo.java.geekbang.designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicLong;

/** 提前初始化实例是一种浪费资源的行为
 * @author jianchengwang
 * @date 2023/6/14
 */
public class IdGeneratorHungry {
    private AtomicLong id = new AtomicLong(0);
    private static final IdGeneratorHungry instance = new IdGeneratorHungry();
    private IdGeneratorHungry() {}

    public static IdGeneratorHungry getInstance() {
        return instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
