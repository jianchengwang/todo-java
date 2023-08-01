package todo.java.geekbang.designpattern.behavioral.observer.eventbus;

import java.util.concurrent.Executor;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class AsyncEventBus extends EventBus {
    public AsyncEventBus(Executor executor) { super(executor); }
}
