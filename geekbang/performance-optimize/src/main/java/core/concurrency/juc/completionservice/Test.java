package core.concurrency.juc.completionservice;

import java.util.concurrent.*;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class Test {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 创建线程池
        ExecutorService executor =
                Executors.newFixedThreadPool(3);
        // 创建CompletionService
        CompletionService<Integer> cs = new
                ExecutorCompletionService<>(executor);
        // 异步向电商S1询价
        cs.submit(()->getPriceByS1());
        // 异步向电商S2询价
        cs.submit(()->getPriceByS2());
        // 异步向电商S3询价
        cs.submit(()->getPriceByS3());
        // 将询价结果异步保存到数据库
        for (int i=0; i<3; i++) {
            Integer r = cs.take().get();
            executor.execute(()->save(r));
        }
        executor.shutdown();
    }

    public static Integer getPriceByS1() {
        return 1;
    }

    public static Integer getPriceByS2() {
        return 2;
    }

    public static Integer getPriceByS3() {
        return 3;
    }

    public static void save(Integer r) {
        System.out.println(r);
    }
}
