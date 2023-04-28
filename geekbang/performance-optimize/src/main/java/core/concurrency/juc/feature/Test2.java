package core.concurrency.juc.feature;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 创建FutureTask
        FutureTask<Integer> futureTask
                        = new FutureTask<>(()-> 1+2);
        // 创建线程池
        ExecutorService es =
                        Executors.newCachedThreadPool();
        // 提交FutureTask
        es.submit(futureTask);
        // 获取计算结果
        Integer result = futureTask.get();
        System.out.println(result);
        es.shutdown();
    }
}
