package cn.jianchengwang.todo.java8.lambda;

// 函数接口的默认方法
public class DefaultMethodForInterface {

    interface Formula {
        double calculate(int a);

        // 接口可以有 default 实现方法
        default double sqrt(int a) {
            return Math.sqrt(a);
        }
    }

    public static void main(String[] args) {

        // 调用的时候我们只要实现抽象方法就好了，default 方法可以直接调用
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        assert formula.calculate(100) == 100;     // 100.0
        assert formula.sqrt(16) == 4;           // 4.0
    }

}
