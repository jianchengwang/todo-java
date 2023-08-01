package todo.java.geekbang.designpattern.behavioral.template;

/**
 * @author jianchengwang
 * @date 2023/6/15
 */
public class BClass {
    public void process(ICallback callback) {
       callback.methodToCallback(); //...
    }
}
