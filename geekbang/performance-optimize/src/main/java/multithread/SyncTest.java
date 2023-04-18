package multithread;

/**
 * @author jianchengwang
 * @date 2023/4/18
 */
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SyncTest {
    private static int count;

    private static int num = 1;
    private static int maxValue = 100000;
    private static Object lock = new Object();

    public static void main(String[] args) {
        Counter lockTest = new SyncTest().new Counter();
        long startTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(num);
        for (int i = 0; i < num; i++) {
            new Thread(() -> {
                for (int cur = 0; cur < maxValue; cur++) {
                    lockTest.add();
                }
                latch.countDown();
            }).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();

        System.out.println("SyncTest:" + (endTime - startTime) + ", count" + count);

    }


    class Counter {
        public void add() {
            synchronized(lock) {
                count++;
                //System.out.println("count：" + count);
            }
        }
    }

}
