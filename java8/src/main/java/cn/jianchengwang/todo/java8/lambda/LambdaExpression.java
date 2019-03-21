package cn.jianchengwang.todo.java8.lambda;

import javax.swing.*;
import java.util.*;

public class LambdaExpression {

    public static void main(String[] args) {

        // 匿名内部类写法
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

        // lambada 表达式写法
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        // 因为只有一行返回语句，所以{} 跟 return 都可以省略
        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        // 参数类型可以从上下文推算得出，所以参数类型也可以省略
        Collections.sort(names, (a, b) -> b.compareTo(a));

        // 遍历集合
        List<String> pointList = new ArrayList();
        pointList.add("1");
        pointList.add("2");
        pointList.forEach(p ->  {
                    System.out.println(p);
                    //Do more work
                }
        );

        // 创建一个runnable 并赋给线程
        new Thread(
                () -> System.out.println("My Runnable")
        ).start();

        // 给gui组件添加监听事件，当然现在很少用swing了，所以了解即可
        JButton button =  new JButton("Submit");
        button.addActionListener((e) -> {
            System.out.println("Click event triggered !!");
        });
    }
}
