package todo.java.geekbang.designpattern.structural.proxy;

/**
 * @author jianchengwang
 * @date 2023/6/14
 */
public class SubjectProxy implements ISubject {
    private ISubject subject;

    public SubjectProxy() {
        subject = new RealSubject(); // 此处仅简单地new实例
    }

    @Override
    public void doAction() {
        System.out.println(">> doWhatever start");
        subject.doAction();
        System.out.println("doWhatever end <<");
    }
    @Override
    public void byebye() {
        System.out.println("Say goodbye");
    }
}
