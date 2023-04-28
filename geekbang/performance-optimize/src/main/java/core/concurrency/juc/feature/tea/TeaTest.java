package core.concurrency.juc.feature.tea;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class TeaTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建任务T2的FutureTask
        FutureTask<String> ft2
                = new FutureTask<>(new T2Task());
        // 创建任务T1的FutureTask
        FutureTask<String> ft1
                = new FutureTask<>(new T1Task(ft2));
        // 线程T1执行任务ft1
        Thread T1 = new Thread(ft1);
        T1.start();
        // 线程T2执行任务ft2
        Thread T2 = new Thread(ft2);
        T2.start();
        // 等待线程T1执行结果
        System.out.println(ft1.get());
    }
}
