package design.decorator;

/**
 * @author jianchengwang
 * @date 2023/4/20
 */

/**
 * 装修基本类
 * @author admin
 *
 */
public class Decorator implements IDecorator{

    /**
     * 基本实现方法
     */
    public void decorate() {
        System.out.println("decorate 1");
    }

}
