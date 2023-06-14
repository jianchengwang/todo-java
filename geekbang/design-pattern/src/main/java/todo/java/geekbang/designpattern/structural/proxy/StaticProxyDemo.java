package todo.java.geekbang.designpattern.structural.proxy;

import java.lang.reflect.Proxy;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class StaticProxyDemo {
    public static void main(String[] args) {
        SubjectProxy subject = new SubjectProxy();
        subject.doAction();
        subject.byebye();
    }
}
