package todo.java.geekbang.designpattern.behavioral.template;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class CallBackDemo {
    public static void main(String[] args) {
        BClass b = new BClass();
        //回调对象
        b.process(() -> System.out.println("Call back me."));
    }
}
