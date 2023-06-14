package todo.java.geekbang.designpattern.structural.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SubjectMethodInterceptor implements MethodInterceptor {
    public Object createCglibProxy(Class<?> targetClass) {
        Enhancer enhancer = new Enhancer();  // 创建增强器，用来创建动态代理类
        enhancer.setSuperclass(targetClass); // 为增强器指定要代理的业务类，即为生成的代理类指定父类
        enhancer.setCallback(this);          // 设置回调(对于代理类上所有方法的调用，都会调用CallBack)
        return enhancer.create(); // 创建动态代理类对象并返回
        // 以上语句可简化为：return Enhancer.create(targetClass, this); //
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println(">> doWhatever start"); // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        Object result = methodProxy.invokeSuper(proxy, args);
        System.out.println("doWhatever end <<");   // 扩展进行额外的功能操作(如鉴权、计时、日志等)
        return result;
    }
}
