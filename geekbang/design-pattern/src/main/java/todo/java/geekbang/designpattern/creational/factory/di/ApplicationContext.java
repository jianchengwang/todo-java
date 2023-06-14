package todo.java.geekbang.designpattern.creational.factory.di;

public interface ApplicationContext {
    Object getBean(String beanId);
}
