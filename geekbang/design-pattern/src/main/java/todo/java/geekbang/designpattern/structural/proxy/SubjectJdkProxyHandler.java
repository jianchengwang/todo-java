package todo.java.geekbang.designpattern.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SubjectJdkProxyHandler implements InvocationHandler {

    private Object realSubject;
    public SubjectJdkProxyHandler(Object realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(">> doWhatever start");
        Object result = method.invoke(realSubject, args); // 执行目标对象方法
        System.out.println("doWhatever end <<");
        return result;
    }
}
