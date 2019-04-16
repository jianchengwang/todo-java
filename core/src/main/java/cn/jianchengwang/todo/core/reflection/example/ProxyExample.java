package cn.jianchengwang.todo.core.reflection.example;

import cn.jianchengwang.todo.core.reflection.model.proxy.MyInvocationHandler;

import java.lang.reflect.InvocationHandler;

public class ProxyExample {

    public static void main(String[] args) {

//        InvocationHandler handler = new MyInvocationHandler();
//        MyInterface proxy = (MyInterface) Proxy.newProxyInstance(
//                MyInterface.class.getClassLoader(),
//                new Class[] { MyInterface.class },
//                handler);


        /**
         * Database Connection and Transaction Management
         *
         * web controller --> proxy.execute(...);
         *   proxy --> connection.setAutoCommit(false);
         *   proxy --> realAction.execute();
         *     realAction does database work
         *   proxy --> connection.commit();
         */

        /**
         *
         */

    }
}
