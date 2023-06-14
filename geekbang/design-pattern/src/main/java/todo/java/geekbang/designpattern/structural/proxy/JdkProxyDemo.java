package todo.java.geekbang.designpattern.structural.proxy;

import java.lang.reflect.Proxy;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class JdkProxyDemo {
    public static void main(String[] args) {
        ISubject subject = (ISubject) Proxy.newProxyInstance(
                ISubject.class.getClassLoader(),
                new Class[]{ISubject.class},
                new SubjectJdkProxyHandler(new RealSubject())
        );
        subject.doAction();
    }
}
