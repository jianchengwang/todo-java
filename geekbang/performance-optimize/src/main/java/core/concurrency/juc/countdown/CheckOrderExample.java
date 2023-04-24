package core.concurrency.juc.countdown;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class CheckOrderExample {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CheckOrderExample example = new CheckOrderExample();
        boolean existsUndoOrder = true; // 存在未对账订单
        while(existsUndoOrder){
            final List<String> pos = new ArrayList<>();
            final List<String> dos = new ArrayList<>();
//            Thread t1 = new Thread(() -> {
//                // 查询未对账订单
//                pos.addAll(example.getPOrders());
//            });
//            t1.start();
//            Thread t2 = new Thread(() -> {
//                // 查询派送单
//                dos.addAll(example.getDOrders());
//            });
//            t2.start();
//            t1.join();
//            t2.join();

            // 计数器初始化为2
            CountDownLatch latch = new CountDownLatch(2);
            executor.execute(() -> {
                // 查询未对账订单
                pos.addAll(example.getPOrders());
                latch.countDown();
            });
            executor.execute(() -> {
                // 查询派送单
                dos.addAll(example.getDOrders());
                latch.countDown();
            });
            latch.await();
            executor.shutdown();
            // 执行对账操作
            List<String> diff = example.check(pos, dos);
            // 差异写入差异库
            example.save(diff);
            existsUndoOrder = false;
        }
    }

    public List<String> getPOrders() {
        return List.of("order1", "order2", "order3");
    }

    public List<String> getDOrders() {
        return List.of("order1", "order3");
    }

    public List<String> check(List<String> pos, List<String> dos) {
        return List.of("order2");
    }

    public void save(List<String> diff) {
        System.out.println(diff);
    }
}
