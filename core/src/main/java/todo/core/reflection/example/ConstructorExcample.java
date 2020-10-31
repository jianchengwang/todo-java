package todo.core.reflection.example;

import todo.core.reflection.model.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ConstructorExcample {

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class c1 = Person.class;

        Constructor[] constructors = c1.getConstructors();

        Constructor constructor = c1.getConstructor(new Class[] {Integer.class, String.class});

        Class[] parameterTypes = constructor.getParameterTypes();

        Person p1 = (Person) constructor.newInstance(27, "jianchengwang");

        System.out.println(p1.getName());
    }
}
