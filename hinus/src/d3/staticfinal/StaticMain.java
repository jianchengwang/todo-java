package d3.staticfinal;

import static java.lang.Math.*;

public class StaticMain {
    public static void main(String[] args) {
        System.out.println(max(100, 1000));
    }

    public static void sayHello() {
        System.out.println("Hello, everybody~");
        // 这个当然不能用。static函数与其挂靠的那个类的对象没有任何关系。
        // static函数是全局唯一的。
        // this.sayBye();
    }

    public void sayBye() {
        System.out.println("Good Bye~");
    }

    public void saySomething() {
        // 唯一的一点好处，大概就是成员函数里这样三种写法都是OK的。
        // 但这个没卵用。我更喜欢Java只保留第三种写法，免得大家误会。
        this.sayHello();
        sayHello();
        StaticMain.sayHello();
        this.sayBye();
    }
}
