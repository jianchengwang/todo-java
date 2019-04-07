package cn.jianchengwang.todo.core.java8.lambda;

// 函数接口 只能并且只有一个抽象方法，可以添加注解 @FunctionalInterface，那么当你创建其他抽象方法的时候会编译错误
public class FunctionInterfaceExample {

    @FunctionalInterface
    public interface MyFirstFunctionalInterface {
        void firstWork();

        //void sendWork(); // 编译错误

        // 因为默认方法有一个实现，所以它们不是抽象的。因为默认方法不是抽象的，所以您可以随意将默认方法添加到您的函数接口中。
        default void thirdWork() {
            System.out.println("hello world");
        }

        // 静态方法也是允许的，可以少写工具类
        static void fourWork() {
            System.out.println("today is a nice day");
        }

        // 如果接口声明一个抽象方法覆盖java.lang.Object 的一个公共方法，这也不计入接口的抽象方法计数，因为该接口的任何实现都有一个来自java.lang.Object 的实现。
        @Override
        public String toString();                //Overridden from Object class
        @Override
        public boolean equals(Object obj);        //Overridden from Object class
    }


}
