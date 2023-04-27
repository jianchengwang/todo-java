package core.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jianchengwang
 * @date 2023/4/26
 */
public class Test {

    static AtomicLong count = new AtomicLong(0);
    public static void main(String[] args) {
        add10k();
    }

    public static void add10k() {
        int idx = 0;
        while(idx++ < 10000) {
            count.getAndIncrement();
        }
        System.out.println(count);
    }
}
