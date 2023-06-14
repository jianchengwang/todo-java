package todo.java.geekbang.designpattern.structural.proxy;

import org.springframework.aop.framework.ProxyFactory;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SpringAopProxyDemo {
    public static void main(String[] args) {
        ISubject proxy = ProxyFactory.getProxy(ISubject.class, new SubjectSpringAopInvoker(new RealSubject()));
        proxy.doAction();
    }
}
