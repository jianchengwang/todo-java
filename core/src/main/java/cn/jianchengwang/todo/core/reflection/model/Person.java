package cn.jianchengwang.todo.core.reflection.model;

@MyAnnotation(name="someName",  value = "Hello World")
public class Person extends SuperPerson {

    private Integer age;

    @MyAnnotation(name="someName",  value = "Hello World")
    public String name;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(@MyAnnotation(name="someName",  value = "Hello World") String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(Integer age, String name) {
        this.age = age;
        this.name = name;
    }

    public static void staticMethod(String msg) {
        System.out.println("this a static method, hello " + msg);
    }

    private String privateMethod() {
        return "i am private method";
    }

    @MyAnnotation(name="someName",  value = "Hello Method")
    public void annotationMethod() {
        System.out.println("this is annotation method");
    }
}
