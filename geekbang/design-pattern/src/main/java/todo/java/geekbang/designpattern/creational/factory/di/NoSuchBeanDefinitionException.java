package todo.java.geekbang.designpattern.creational.factory.di;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class NoSuchBeanDefinitionException extends RuntimeException {
    public NoSuchBeanDefinitionException(String s) {
        super(s);
    }
}
