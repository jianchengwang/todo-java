package todo.java.geekbang.designpattern.structural.facade;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class MemoryImpl implements Memory {
    @Override
    public void start() {
        System.out.println("Memory started.");
    }

    @Override
    public void shutdown() {
        System.out.println("Memory shutdown.");
    }
}
