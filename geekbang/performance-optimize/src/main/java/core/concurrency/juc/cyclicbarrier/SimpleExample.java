package core.concurrency.juc.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class SimpleExample {
    public static void main(String[] args) {
        // 1 会议需要三个人
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
            // 2 这是三个人都到齐之后会执行的代码
            System.out.println("all people arrived");
        }
    });

      // 定义三个线程，相当于三个参会的人
      for (int i = 0; i < 3; i++) {
        final int finalI = i;
        new Thread(() -> {
            try {
                // 4 模拟每人到会议室所需时间
                Thread.sleep((long) (Math.random()*5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("the "+Thread.currentThread().getName()+" person arrived");
            try {
                // 5 等待其他人到会议室
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" start to work");
        }, String.valueOf(finalI)).start();
        }
    }
}
