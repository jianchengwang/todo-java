package todo.java.geekbang.designpattern.structural.facade;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class HardDriveImpl implements HardDrive {
    @Override
    public void start() {
        System.out.println("Hard drive started.");
    }

    @Override
    public void shutdown() {
        System.out.println("Hard drive shutdown.");
    }
}
