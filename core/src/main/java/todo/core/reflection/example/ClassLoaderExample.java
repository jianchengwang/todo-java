package todo.core.reflection.example;

public class ClassLoaderExample {

    public static void main(String[] args) {

        ClassLoader classLoader = ClassLoaderExample.class.getClassLoader();

        try {
            Class aClass = classLoader.loadClass("todo.core.reflection.example.ClassLoaderExample");
            System.out.println("aClass.getName() = " + aClass.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
