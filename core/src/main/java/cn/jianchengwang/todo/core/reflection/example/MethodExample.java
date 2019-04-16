package cn.jianchengwang.todo.core.reflection.example;

import cn.jianchengwang.todo.core.reflection.model.Person;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodExample {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        Class c = Person.class;

        Method[] methods = c.getMethods();

        Method method1 = c.getMethod("setName", new Class[]{String.class});
        Method method2 = c.getMethod("getName", null);

        Class[] parameterTypes = method1.getParameterTypes();
        Class returnType = method2.getReturnType();

        Person p = new Person();
        Method method3 = c.getMethod("setName", String.class);
        method3.invoke(p, "wjc");

        Method staticMethod = c.getMethod("staticMethod", String.class);
        staticMethod.invoke(null, "wjc"); // if static method, null instead of obj instance

        Method privateMethod = c.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        System.out.println(privateMethod.invoke(p));

        printGettersSetters(c);
    }

    public static void printGettersSetters(Class aClass){
        Method[] methods = aClass.getMethods();

        for(Method method : methods){
            if(isGetter(method)) System.out.println("getter: " + method);
            if(isSetter(method)) System.out.println("setter: " + method);
        }
    }

    public static boolean isGetter(Method method){
        if(!method.getName().startsWith("get"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        if(void.class.equals(method.getReturnType())) return false;
        return true;
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        if(method.getParameterTypes().length != 1) return false;
        return true;
    }


}
