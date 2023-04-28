package core.concurrency.juc.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author jianchengwang
 * @date 2023/4/28
 */
public class MyThreadPool {
    BlockingQueue<Runnable> workQueue;
    List<WorkThread> workThreadList;
    int coreSize = 4;

    public MyThreadPool(int coreSize, BlockingQueue<Runnable> workQueue) {
        this.coreSize = coreSize;
        this.workQueue = workQueue;
        this.workThreadList = new ArrayList<>(coreSize);
        for (int i = 0; i < coreSize; i++) {
            WorkThread worker = new WorkThread("worker-" + i);
            worker.start();
            workThreadList.add(worker);
        }
    }

    public void execute(Runnable command) throws InterruptedException {
        workQueue.put(command);
    }

    public class WorkThread extends Thread {
        public WorkThread(String name) {
            super(name);
        }

        public void run() {
            while (true) {
                try {
                    Runnable task = workQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        MyThreadPool myThreadPool = new MyThreadPool(4, workQueue);
        for (int i = 0; i < 10; i++) {
            myThreadPool.execute(() -> {
                System.out.println("hello:" + Thread.currentThread().getName());
            });
        }
    }

}
