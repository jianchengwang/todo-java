package cn.jianchengwang.todo.core.java8.streams;


import java.util.Optional;
import java.util.stream.Stream;

public class StreamReduce {

    public static void main(String[] args) {
        // 找出最长的单词
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        Optional<String> longest = stream.reduce((s1, s2) -> s1.length()>=s2.length() ? s1 : s2);
        //Optional<String> longest = stream.max((s1, s2) -> s1.length()-s2.length());
        System.out.println(longest.get());

        // 求单词长度之和
        Stream<String> stream1 = Stream.of("I", "love", "you", "too");
        Integer lengthSum = stream.reduce(0,// 初始值　// (1)
        (sum, str) -> sum+str.length(), // 累加器 // (2)
        (a, b) -> a+b);// 部分和拼接器，并行执行时才会用到 // (3)
        // int lengthSum = stream.mapToInt(str -> str.length()).sum();
        System.out.println(lengthSum);
    }
}
