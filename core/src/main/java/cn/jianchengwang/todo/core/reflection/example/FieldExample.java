package cn.jianchengwang.todo.core.reflection.example;

import cn.jianchengwang.todo.core.reflection.model.Person;

import java.lang.reflect.Field;

public class FieldExample {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

        Class c = Person.class;

        Field[] publicFields = c.getFields();
        Field publicField = c.getField("name");
        Field publicSubField = c.getField("sex");

        Field[] privateFields = c.getDeclaredFields();
        Field privateField = c.getDeclaredField("age");
//        Field privateSubField = c.getDeclaredField("sex"); // throw java.lang.NoSuchFieldException

        String fieldName = publicField.getName();
        Object fieldType = publicField.getType();

        Person p = new Person(27, "haha");
        Object value1 = publicField.get(p);
        publicField.set(p, value1);

        privateField.setAccessible(true); // access even if it is private
        Object value2 = publicField.get(p);
        publicField.set(p, value2);

        System.out.println(value2);

    }
}
