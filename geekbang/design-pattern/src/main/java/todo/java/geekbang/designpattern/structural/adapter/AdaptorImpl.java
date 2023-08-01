package todo.java.geekbang.designpattern.structural.adapter;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class AdaptorImpl implements ITarget {

    private Adaptee adaptee;
    public AdaptorImpl(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void f1() {
        adaptee.fa(); // 委托给Adaptee实现
    }

    @Override
    public void f2() {
        // 重新实现f2()
    }

    @Override
    public void fc() {
        adaptee.fc();
    }
}
