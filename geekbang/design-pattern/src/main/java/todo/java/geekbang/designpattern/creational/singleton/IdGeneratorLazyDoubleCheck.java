package todo.java.geekbang.designpattern.creational.singleton;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 需要给 instance 成员变量添加 volatile 关键字来禁止指令重排序
 * @author jianchengwang
 * @date 2023/6/14
 */
public class IdGeneratorLazyDoubleCheck {

    private AtomicLong id = new AtomicLong(0);

    private static volatile IdGeneratorLazyDoubleCheck instance;

    private IdGeneratorLazyDoubleCheck() {}

    public static IdGeneratorLazyDoubleCheck getInstance() {
        IdGeneratorLazyDoubleCheck localRef = instance;
        if (localRef == null) {
            synchronized (IdGeneratorLazyDoubleCheck.class) {
                localRef = instance;
                if (localRef == null) {
                    instance = localRef = new IdGeneratorLazyDoubleCheck();
                }
            }
        }
        return localRef;
    }

    public long getId() {
        return id.incrementAndGet();
    }
}
