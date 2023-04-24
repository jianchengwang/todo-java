package core.concurrency.juc.semaphore;

import java.util.Queue;

/**
 * @author jianchengwang
 * @date 2023/4/24
 */
public class Semaphore1 {
    // 计数器
    int count;
    // 等待队列
    Queue queue;
    public Semaphore1(int count) {
        this.count = count;
    }

    void down() {
        this.count--;
        if (this.count < 0) {
            // 讲当前线程加入等待队列
            // 阻塞当前线程
        }
    }

    void up() {
        this.count++;
        if (this.count <= 0) {
            // 移除等待队列的某个线程T
            // 唤醒线程T
        }
    }
}
