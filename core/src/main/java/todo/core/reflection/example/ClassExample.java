package todo.core.reflection.example;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ClassExample {

    public static void main(String[] args) throws ClassNotFoundException {

        Class c1 = ClassExample.class;
        Class c2 = Class.forName("todo.core.reflection.example.ClassExample");

        String className = c1.getName();
        String simpleClassName = c1.getSimpleName();

        int modifiers = c1.getModifiers();
        Modifier.isAbstract(modifiers);
        Modifier.isPublic(modifiers);

        Package package1 = c1.getPackage();

        Class superclass = c1.getSuperclass();

        Class[] interfaces = c1.getInterfaces();

        Constructor[] constructors = c1.getConstructors();

        Method[] methods = c1.getMethods();

        Field[] fields = c1.getFields();

        Annotation[] annotations = c1.getAnnotations();


    }
}
