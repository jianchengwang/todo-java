package cn.jianchengwang.todo.java8.lambda;

// lambda 的作用域
public class LambdaScope {

    public static void main(String[] args) {

//        testLocalVariables();

        LambdaScope lambdaScope = new LambdaScope();
        lambdaScope.testFieldOrStaticVariables();


    }

    public static void  testLocalVariables() {
        final int num1 = 1;
        Converter<Integer, String> stringConverter1 =
                (from) -> String.valueOf(from + num1);
        assert stringConverter1.convert(2).equals("3");     // 3

        int num2 = 1;
        Converter<Integer, String> stringConverter2 =
                (from) -> String.valueOf(from + num2);
        stringConverter2.convert(2);     // 3

        // 不能在lambda表达式中改变变量的值，变量虽然没有声明为final类型，但是其实是隐final
//        int num3 = 1;
//        Converter<Integer, String> stringConverter3 =
//                (from) -> String.valueOf(from + num3);
//        num3 = 3;

    }

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    static int outerStaticNum;
    int outerNum;

    // 字段跟静态变量跟匿名内部类一致，都可以进行修改访问
    void testFieldOrStaticVariables() {

        Converter<Integer, String> stringConverter1 = (from) -> {
            outerNum = 23;
            return String.valueOf(from);
        };

        Converter<Integer, String> stringConverter2 = (from) -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        };
    }

    // lambda 表达式不能访问接口的默认方法
    public static void testDefaultInterfaceMethod() {
//        DefaultMethodForInterface.Formula formula = (a) -> sqrt( a * 100); // 不能编译 Cannot resolve method 'sqrt(int)
    }
}
