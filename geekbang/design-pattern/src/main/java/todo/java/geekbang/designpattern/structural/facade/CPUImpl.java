package todo.java.geekbang.designpattern.structural.facade;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class CPUImpl implements CPU {
    @Override
    public void start() {
        System.out.println("CPU started.");
    }

    @Override
    public void shutdown() {
        System.out.println("CPU shutdown.");
    }
}

