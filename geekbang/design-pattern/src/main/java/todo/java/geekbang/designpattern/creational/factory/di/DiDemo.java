package todo.java.geekbang.designpattern.creational.factory.di;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */

public class DiDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "beans.xml");
        RateLimiter rateLimiter = (RateLimiter) applicationContext.getBean("rateLimiter");
        rateLimiter.test();
        //...
    }
}
