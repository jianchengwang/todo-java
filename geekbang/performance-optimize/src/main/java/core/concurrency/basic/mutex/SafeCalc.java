package core.concurrency.basic.mutex;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */
public class SafeCalc {
    long value = 0L;
    synchronized long get() {
        return value;
    }
    synchronized void addOne() {
        value += 1;
    }
}
