package cn.jianchengwang.todo.core.java8.lambda;

import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// java8 内置的函数接口
public class BuildInInterface {

    static class Person {
        private int age;
        private String name;

        public Person() {
        }

        public Person(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }

    public static void main(String[] args) {

        testPredicates();

        testFunctions();

        testSuppliers();

        testConsumer();

        testComparators();

    }

    // 参数布尔值函数，用于逻辑计算，经常用到
    public static void testPredicates() {
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

    }

    // 功能函数，接收一个参数产生一个结果，默认方法 andThen 可以将多个函数串联起来
    public static void testFunctions() {
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"
    }

    // 相当于生产者，生成给定泛型的结果，不接受参数
    public static void testSuppliers() {
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person
    }


    // 相当于消费者，单个输入参数上进行操作
    public static void testConsumer() {
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.name);
        greeter.accept(new Person(26, "Skywalker"));
    }

    // 比较器在早期版本的Java中是众所周知的。Java 8向接口添加了各种默认方法。
    public static void testComparators() {
        Comparator<Person> comparator = (p1, p2) -> p1.name.compareTo(p2.name);

        Person p1 = new Person(23, "Doe");
        Person p2 = new Person(16, "Wonderland");

        assert comparator.compare(p1, p2) > 0;             // > 0
        assert comparator.reversed().compare(p1, p2)<0;  // < 0
    }
}
