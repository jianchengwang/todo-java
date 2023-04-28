package core.concurrency.juc.completionservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test2 {
    public static void main(String[] args) {

        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs =
                new ExecutorCompletionService<>(executor);
        // 用于保存Future对象
        List<Future<Integer>> futures =
                new ArrayList<>(3);
        //提交异步任务，并保存future到futures
        futures.add(
                cs.submit(()->geocoderByS1()));
        futures.add(
                cs.submit(()->geocoderByS2()));
        futures.add(
                cs.submit(()->geocoderByS3()));
        // 获取最快返回的任务执行结果
        Integer r = 0;
        try {
            // 只要有一个成功返回，则break
            for (int i = 0; i < 3; ++i) {
                r = cs.take().get();
                //简单地通过判空来检查是否成功返回
                if (r != null) {
                    break;
                }
            }
            System.out.println(r);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            //取消所有任务
            for(Future<Integer> f : futures)
                f.cancel(true);
        }
        executor.shutdown();
    }

    public static Integer geocoderByS1() {
        return 1;
    }

    public static Integer geocoderByS2() {
        return 2;
    }

    public static Integer geocoderByS3() {
        return 3;
    }
}
