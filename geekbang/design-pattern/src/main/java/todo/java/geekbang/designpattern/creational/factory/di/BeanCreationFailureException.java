package todo.java.geekbang.designpattern.creational.factory.di;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class BeanCreationFailureException extends RuntimeException {
    public BeanCreationFailureException(String s, ReflectiveOperationException e) {
        super(s, e);
    }
}
