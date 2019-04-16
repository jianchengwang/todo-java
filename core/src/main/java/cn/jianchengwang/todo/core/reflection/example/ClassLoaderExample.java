package cn.jianchengwang.todo.core.reflection.example;

import cn.jianchengwang.todo.core.reflection.model.classloader.MyClassLoader;

public class ClassLoaderExample {

    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoaderExample.class.getClassLoader();

        try {
            Class aClass = classLoader.loadClass("cn.jianchengwang.todo.core.reflection.example.ClassLoaderExample");
            System.out.println("aClass.getName() = " + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
