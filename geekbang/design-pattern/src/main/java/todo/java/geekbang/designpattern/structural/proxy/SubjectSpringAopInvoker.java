package todo.java.geekbang.designpattern.structural.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SubjectSpringAopInvoker implements MethodInterceptor {

    private RealSubject target;
    public SubjectSpringAopInvoker(RealSubject target) {
        this.target = target;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println(">> doWhatever start");
        Object result = invocation.getMethod().invoke(this.target, invocation.getArguments());
        System.out.println("doWhatever end <<");
        return result;
    }
}
