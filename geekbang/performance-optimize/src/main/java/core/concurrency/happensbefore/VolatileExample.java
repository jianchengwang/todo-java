package core.concurrency.happensbefore;

/**
 * @author jianchengwang
 * @date 2023/4/23
 */

// 以下代码来源于【参考1】
public class VolatileExample {
    int x = 0;
    volatile boolean v = false;
    public void writer() {
        x = 42;
        v = true;
    }
    public void reader() {
        if (v == true) {
            // writer x before v, writer v before reader v -> writer x before reader v -> reader x == 42
            System.out.println(x);
        }
    }
}
