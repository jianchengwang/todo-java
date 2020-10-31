package todo.core.reflection.model.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //do something "dynamic"

        return null;
    }
}