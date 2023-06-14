package todo.java.geekbang.designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * instance 的唯一性、创建过程的线程安全性，都由 JVM 来保证。所以，这种实现方法既保证了线程安全，又能做到延迟加载。
 * @author jianchengwang
 * @date 2023/6/14
 */
public class IdGeneratorLazyStaticInnerClass {

    private AtomicLong id = new AtomicLong(0);

    private static class SingletonHolder {
        private static final IdGeneratorLazyStaticInnerClass instance = new IdGeneratorLazyStaticInnerClass();
    }

    private IdGeneratorLazyStaticInnerClass() {}

    public static IdGeneratorLazyStaticInnerClass getInstance() {
        return SingletonHolder.instance;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
