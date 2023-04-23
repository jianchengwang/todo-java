package core.concurrency.happensbefore;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */
public class ThreadStartExample {

    private static int a = 0;
    public static void main(String[] args) {
        Thread B = new Thread(()->{
            // 主线程调用B.start()之前
            // 所有对共享变量的修改，此处皆可见
            // 此例中，a==77
            System.out.println("a=" + a);
        });
        // 此处对共享变量var修改
        a = 77;
        // 主线程启动子线程
        B.start();
    }
}
