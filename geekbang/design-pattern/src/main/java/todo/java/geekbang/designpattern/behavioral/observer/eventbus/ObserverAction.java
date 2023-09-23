package todo.java.geekbang.designpattern.behavioral.observer.eventbus;

import org.assertj.core.util.Preconditions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class ObserverAction {
    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method; this.method.setAccessible(true);
    }

    public void execute(Object event) {
        // event is method parameter
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}