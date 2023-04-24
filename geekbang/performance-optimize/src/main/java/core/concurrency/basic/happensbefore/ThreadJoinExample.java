package core.concurrency.basic.happensbefore;

/**
 * 如果在线程 A 中，调用线程 B 的 join() 并成功返回，那么线程 B 中的任意操作 Happens-Before 于该 join() 操作的返回
 * @author jianchengwang
 * @date 2023/4/23
 */
public class ThreadJoinExample {

private static int a = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread B = new Thread(()->{
            // 此处对共享变量var修改
            a = 66;
        });
        // 例如此处对共享变量修改，
        // 则这个修改结果对线程B可见
        // 主线程启动子线程
        B.start();
        B.join();
        // 子线程所有对共享变量的修改
        // 在主线程调用B.join()之后皆可见
        // 此例中，a==66
        System.out.println("a=" + a);
    }
}
