package cn.jianchengwang.todo.java8.growing.jdk7;

import java.util.ArrayList;
import java.util.List;

// 类型推断
public class TypeInference {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("A");

        List<? extends String> list2 = new ArrayList<>();
        list.addAll(list2);
    }
}
