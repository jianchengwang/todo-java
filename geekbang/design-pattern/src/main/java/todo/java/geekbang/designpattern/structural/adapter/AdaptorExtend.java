package todo.java.geekbang.designpattern.structural.adapter;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class AdaptorExtend extends Adaptee implements ITarget {
    @Override
    public void f1() {
        super.fa();
    }

    @Override
    public void f2() {
        // 重新实现f2
    }
}
