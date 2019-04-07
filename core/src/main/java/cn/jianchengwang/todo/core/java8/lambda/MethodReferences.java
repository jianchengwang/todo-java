package cn.jianchengwang.todo.core.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// 方法引用
public class MethodReferences {

    // 方法引用其实就是lambada 的 eta-conversion 就是 x->f(x) 可以简写为 f

    public static void main(String[] args) {

        // static method -> Math::max = Math.max(x,y)
        List<Integer> integers = Arrays.asList(1,12,433,5);
        Optional<Integer> max = integers.stream().reduce( Math::max );
        max.ifPresent(value -> System.out.println(value));

        // instance method from instance -> System.out::println = System.out.println(x)
        ArrayList<Integer> numberList = new ArrayList<>(Arrays.asList(1,2,3,4,5));
        Consumer<Integer> action = System.out::println;
        numberList.forEach(action);

        // instance method from class type -> String::compareTo = s1.compareTo(s2)
        List<String> strings = Arrays
                .asList("how", "to", "do", "in", "java", "dot", "com");

        List<String> sorted = strings
                .stream()
                .sorted((s1, s2) -> s1.compareTo(s2))
                .collect(Collectors.toList());

        System.out.println(sorted);

        List<String> sortedAlt = strings
                .stream()
                .sorted(String::compareTo)
                .collect(Collectors.toList());

        System.out.println(sortedAlt);

        // constructor -> ArrayList::new = new ArrayList
        List<Integer> integers1 = IntStream
                .range(1, 100)
                .boxed()
                .collect(Collectors.toCollection( ArrayList::new ));
        Optional<Integer> max1 = integers.stream().reduce(Math::max);
        max.ifPresent(System.out::println);
    }

}
