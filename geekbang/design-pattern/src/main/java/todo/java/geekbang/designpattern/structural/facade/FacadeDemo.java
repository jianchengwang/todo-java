package todo.java.geekbang.designpattern.structural.facade;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class FacadeDemo {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.startComputer();
        computer.shutdownComputer();
    }
}
