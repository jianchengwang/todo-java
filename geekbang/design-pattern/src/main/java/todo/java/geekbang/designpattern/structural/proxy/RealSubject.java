package todo.java.geekbang.designpattern.structural.proxy;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class RealSubject implements ISubject {
    @Override
    public void doAction() { System.out.println("Real Action Here!"); }
    @Override
    public void byebye() { System.out.println("Wave goodbye!"); }
}
