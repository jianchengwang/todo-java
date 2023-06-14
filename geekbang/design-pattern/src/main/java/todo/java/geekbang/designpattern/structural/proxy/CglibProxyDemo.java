package todo.java.geekbang.designpattern.structural.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class CglibProxyDemo {
    // 还可使用CGLib + JDK InvocationHandler接口实现动态代理
    public static Object createCglibProxyWithHandler(Class<?> targetClass) {
        MethodInterceptor interceptor = null;
        try {
            InvocationHandler invocationHandler = new SubjectJdkProxyHandler(targetClass.newInstance());
            interceptor = (Object o, Method method, Object[] objects,
                           MethodProxy methodProxy) -> invocationHandler.invoke(o, method, objects);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return Enhancer.create(targetClass, interceptor);
    }

    public static void main(String[] args) {
        RealSubject subject = (RealSubject) new SubjectMethodInterceptor().createCglibProxy(RealSubject.class);
        subject.doAction();

        RealSubject subject2 = (RealSubject) createCglibProxyWithHandler(RealSubject.class);
        subject2.doAction();
    }
}
