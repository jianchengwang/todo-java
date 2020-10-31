package todo.core.java8.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamOperation {

    public static void main(String[] args) {

        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

//        testFilter(memberNames);
//        testMap(memberNames);
//        testSorted(memberNames);
//
//        testForEach(memberNames);
//        testCollect(memberNames);
//        testMatch(memberNames);
//        testCount(memberNames);
//        testReduce(memberNames);
//
//        testShortCircuit(memberNames);

        testParallelism();
    }

    // 中间流返回流本身

    // 过滤，Filter accepts a predicate to filter all elements of the stream.
    public static void testFilter(List<String> memberNames) {

        memberNames.stream().filter((s) -> s.startsWith("A"))
                .forEach(System.out::println);

    }

    // 函数原型为Stream<T> distinct()，作用是返回一个去除重复元素之后的Stream。
    public static void testDistinct() {
        Stream<String> stream= Stream.of("I", "love", "you", "too", "too");
        stream.distinct().forEach(str -> System.out.println(str));
    }

    // 映射，The intermediate operation map converts each element into another object via the given function.
    public static void testMap(List<String> memberNames) {
        memberNames.stream().filter((s) -> s.startsWith("A"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    // 将原stream的元素P摊平后组成新的stream
    public static void testFlatMap() {
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1,2), Arrays.asList(3, 4, 5));
        stream.flatMap(list -> list.stream())
                .forEach(i -> System.out.println(i));
    }


    // 排序，Sorted is an intermediate operation which returns a sorted view of the stream.
    // The elements are sorted in natural order unless you pass a custom Comparator.
    public static void testSorted(List<String> memberNames) {
        memberNames.stream().sorted()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
    // ------------------------

    // 终端流返回特定类型的结果

    // 遍历，This method helps in iterating over all elements of a stream and perform some operation on each of them.
    // The operation is passed as lambda expression parameter.
    public static void testForEach(List<String> memberNames) {
        memberNames.forEach(System.out::println);
    }


    // 收集产生结果集，collect() method used to recieve elements from a sream and store them in a collection and metioned in parameter funcion.
    public static void testCollect(List<String> memberNames) {
        List<String> memNamesInUppercase = memberNames.stream().sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.print(memNamesInUppercase);
    }

    // Various matching operations can be used to check whether a certain predicate matches the stream.
    // All of those operations are terminal and return a boolean result.
    public static void testMatch(List<String> memberNames) {
        boolean matchedResult = memberNames.stream()
                .anyMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult);

        matchedResult = memberNames.stream()
                .allMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult);

        matchedResult = memberNames.stream()
                .noneMatch((s) -> s.startsWith("A"));

        System.out.println(matchedResult);

    }

    // 统计数量，Count is a terminal operation returning the number of elements in the stream as a long.
    public static void testCount(List<String> memberNames) {
        long totalMatched = memberNames.stream()
                .filter((s) -> s.startsWith("A"))
                .count();

        System.out.println(totalMatched);
    }

    // 缩减，This terminal operation performs a reduction on the elements of the stream with the given function.
    // The result is an Optional holding the reduced value.
    public static void testReduce(List<String> memberNames) {
        Optional<String> reduced = memberNames.stream()
                .reduce((s1,s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);

        // Output: Amitabh#Shekhar#Aman#Rahul#Shahrukh#Salman#Yana#Lokesh
    }

    // 中断流操作
    public static void testShortCircuit(List<String> memberNames) {
        // anyMatch() -> 一旦条件 满足 **predicate** ，则返回true。它将不再处理任何元素。
        boolean matched = memberNames.stream()
                .anyMatch((s) -> s.startsWith("A"));

        System.out.println(matched);

        // findFirst() -> 它将从流返回第一个元素，然后不再处理任何元素。
        String firstMatchedName = memberNames.stream()
                .filter((s) -> s.startsWith("L"))
                .findFirst().get();

        System.out.println(firstMatchedName);

    }

    // 并行流
    public static void testParallelism() {
        List<Integer> list = new ArrayList<Integer>();
        for(int i = 1; i< 10; i++){
            list.add(i);
        }
        //Here creating a parallel stream
        Stream<Integer> stream = list.parallelStream();
        Integer[] evenNumbersArr = stream.filter(i -> i%2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }
}
